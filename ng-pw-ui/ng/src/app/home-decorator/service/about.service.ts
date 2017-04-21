import { Injectable } from '@angular/core';

import { Http } from '@angular/http';
import { Response } from '@angular/http';
import { Headers } from '@angular/http';
import { RequestOptions } from '@angular/http';
import { RequestMethod } from '@angular/http';
import { Request } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { AboutApplication } from 'app/home-decorator/model/about-application';

@Injectable()
export class AboutService {
  constructor(private http: Http) {
  }

  getAboutApplication(authToken: string): Observable<AboutApplication> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'application/about';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <AboutApplication> response.json(); });
  }
}
