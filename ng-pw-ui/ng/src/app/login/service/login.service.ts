import { Injectable } from '@angular/core';

import { Http } from '@angular/http';
import { Headers } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { RequestMethod } from '@angular/http';
import { Request } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { Credentials } from 'app/login/model/credentials';
import { LoginResponse } from 'app/login/model/login-response';

import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {
  constructor(private http: Http) {
  }

  login(userName: string, password: string): Observable<LoginResponse> {
    const credentials = new Credentials();
    credentials.userName = userName;
    credentials.password = password;

    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Content-Type', 'application/json');
    httpHeaders.append('Accept', 'application/json');

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.body = credentials;
    options.url = sessionStorage['restServiceBaseUrl'] + 'security/authentication';
    options.method = RequestMethod.Post;

    return this.http.request(new Request(options))
               .map(response => { return <LoginResponse> response.json(); });
  }

  logout(authToken: string) {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Content-Type', 'application/json');
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'security/logout';
    options.method = RequestMethod.Post;

    return this.http.request(new Request(options))
               .map(response => { return; });
  }
}
