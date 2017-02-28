import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

import { ConfigurationService } from './service/configuration.service';
import { Configuration } from './model/configuration';

import { LoginService } from './service/login.service';
import { LoginResponse } from './model/login.response';

@Component({
  selector: 'pw-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private msgs: Message[] = <Message[]> [];
  private userName: string;
  private loginForm: any;

  constructor(private fb: FormBuilder, private router: Router,
              private configurationService: ConfigurationService,
              private loginService: LoginService) {
    let sessionToken: string = sessionStorage['sessionToken'];
    if (sessionToken)
      this.logout(sessionToken);

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
    this.userName = value.userName;
    this.loginService.login(this.userName, value.password)
        .subscribe(
          response => this.processSuccessfulLogin(response),
          error => {
              if (error.status == 401)
                this.showError('User Name or Password or both are invalid');
              else if (error.statusText)
                this.showError(error.statusText);
              else
                this.showError('HTTP code ' + error.status + ' has been detected')
          }
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
          response => {},
          error => {
            if (error.status != 401) {
              if (error.statusText)
                this.showError(error.statusText);
              else
                this.showError('HTTP code ' + error.status + ' has been detected')
            }
          }
        );
  }

  showError(detail) {
    this.msgs = [];
    this.msgs.push({ severity : 'error', summary : null, detail : detail });
  }

  getConfiguration() {
    this.configurationService.getConfiguration()
        .subscribe(
          configuration => sessionStorage['restServiceBaseUrl'] = configuration.restServiceBaseUrl,
          error => {
              if (error.statusText)
                this.showError(error.statusText);
              else
                this.showError('HTTP code ' + error.status + ' has been detected')
          }
        );
    }
}
