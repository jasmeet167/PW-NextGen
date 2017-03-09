import { Component, OnInit } from '@angular/core';
import { ResponseType } from '@angular/http';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { ConfigService } from './service/config.service';
import { LoginService } from './service/login.service';

import { Configuration } from './model/configuration';
import { LoginResponse } from './model/login.response';

@Component({
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  public loginForm: any;
  public userName: string;

  constructor(private fb: FormBuilder, private router: Router,
              private notificationService: NotificationService,
              private configService: ConfigService,
              private loginService: LoginService) {
    const authToken: string = sessionStorage['authToken'];
    if (authToken) {
      this.logout(authToken);
    }
    sessionStorage.clear();
    this.getConfiguration();
  }

  ngOnInit() {
    this.loginForm = this.fb.group({
      'userName': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required)
    });
  }

  onSubmit(value: any) {
    let response: LoginResponse;
    this.userName = value.userName;
    this.loginService.login(this.userName, value.password)
        .subscribe(
          res => response = res,
          err => {
              if (err.type === ResponseType.Default) {
                if (err.status === 401) {
                  this.notificationService.showError('Invalid User Name or Password');
                } else if (err.statusText) {
                  this.notificationService.showError(err.statusText);
                } else {
                  this.notificationService.showError('HTTP code ' + err.status + ' has been detected');
                }
              } else {
                this.notificationService.showGenericCommError();
              }
          },
          ()  => this.processSuccessfulLogin(response)
        );
  }

  private processSuccessfulLogin(response: LoginResponse) {
    sessionStorage['userName'] = this.userName;
    sessionStorage['authToken'] = response.tokenId;
    this.router.navigate(['/home']);
  }

  private logout(authToken: string) {
    this.loginService.logout(authToken)
        .subscribe(
          res => {},
          err => {
              if (err.type === ResponseType.Default) {
                if (err.status !== 401) {
                  if (err.statusText) {
                    this.notificationService.showError(err.statusText);
                  } else {
                    this.notificationService.showError('HTTP code ' + err.status + ' has been detected');
                  }
                }
              } else {
                this.notificationService.showGenericCommError();
              }
          },
          ()  =>  { return; }
        );
  }

  private getConfiguration() {
    let config: Configuration;
    this.configService.getConfiguration()
        .subscribe(
          res => config = res,
          err => {
              if (err.type === ResponseType.Default) {
                if (err.statusText) {
                  this.notificationService.showError(err.statusText);
                } else {
                  this.notificationService.showError('HTTP code ' + err.status + ' has been detected');
                }
              } else {
                this.notificationService.showGenericCommError();
              }
          },
          ()  => sessionStorage['restServiceBaseUrl'] = config.restServiceBaseUrl
        );
  }
}
