import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';

import { AboutApplication } from 'app/home/model/about-application';

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
