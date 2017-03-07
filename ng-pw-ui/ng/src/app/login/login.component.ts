import { Component, OnInit } from '@angular/core';
import { ResponseType } from '@angular/http';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

import { ConfigService } from './service/config.service';
import { Configuration } from './model/configuration';

import { LoginService } from './service/login.service';
import { LoginResponse } from './model/login.response';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public isMsgDisplayed: boolean;
  public msgText: string;

  public loginForm: any;
  public userName: string;

  constructor(private fb: FormBuilder, private router: Router,
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
                  this.showError('Invalid User Name or Password');
                } else if (err.statusText) {
                  this.showError(err.statusText);
                } else {
                  this.showError('HTTP code ' + err.status + ' has been detected');
                }
              } else {
                this.showError('Network error - unable to access Application Services');
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
                    this.showError(err.statusText);
                  } else {
                    this.showError('HTTP code ' + err.status + ' has been detected');
                  }
                }
              } else {
                this.showError('Network error - unable to access Application Services');
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
                  this.showError(err.statusText);
                } else {
                  this.showError('HTTP code ' + err.status + ' has been detected');
                }
              } else {
                this.showError('Network error - unable to access Application Services');
              }
          },
          ()  => sessionStorage['restServiceBaseUrl'] = config.restServiceBaseUrl
        );
  }

  private showError(message) {
    this.msgText = message;
    this.isMsgDisplayed = true;
  }
}
