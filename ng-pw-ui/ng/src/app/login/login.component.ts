import { Component, OnInit } from '@angular/core';
import { ResponseType } from '@angular/http';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

import { ConfigurationService } from './service/configuration.service';
import { Configuration } from './model/configuration';

import { LoginService } from './service/login.service';
import { LoginResponse } from './model/login.response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public msgs: Message[] = <Message[]> [];
  public loginForm: any;
  public userName: string;

  constructor(private fb: FormBuilder, private router: Router,
              private configurationService: ConfigurationService,
              private loginService: LoginService) {
    const sessionToken: string = sessionStorage['sessionToken'];
    if (sessionToken) {
      this.logout(sessionToken);
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
          () => this.processSuccessfulLogin(response)
        );
  }

  private processSuccessfulLogin(response: LoginResponse) {
    sessionStorage['userName'] = this.userName;
    sessionStorage['sessionToken'] = response.tokenId;
    this.router.navigate(['/home']);
  }

  private logout(sessionToken: string) {
    this.loginService.logout(sessionToken)
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
          () => { return; }
        );
  }

  showError(detail) {
    this.msgs = [];
    this.msgs.push({ severity : 'error', summary : null, detail : detail });
  }

  getConfiguration() {
    let config: Configuration;
    this.configurationService.getConfiguration()
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
          () => sessionStorage['restServiceBaseUrl'] = config.restServiceBaseUrl
        );
  }
}
