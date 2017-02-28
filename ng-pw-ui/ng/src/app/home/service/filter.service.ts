import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';

import 'rxjs/add/operator/map';

@Injectable()
export class FilterService {
  constructor(private http: Http) {
  }

  getEnvOptions(sessionToken: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/common/environment';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getCompanyOptions(sessionToken: string, viewChanges: boolean, envId: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/company';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getProductOptions(sessionToken: string, viewChanges: boolean, envId: string,
                    companyCode: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/product';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getPlanCodeOptions(sessionToken: string, viewChanges: boolean, envId: string,
                     companyCode: string, productCode: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/plan';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getIssueStateOptions(sessionToken: string, viewChanges: boolean, envId: string,
                     companyCode: string, productCode: string, planCode: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/state';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getLobOptions(sessionToken: string, viewChanges: boolean, envId: string,
                companyCode: string, productCode: string, planCode: string,
                issueState: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/lob';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getEffDateOptions(sessionToken: string, viewChanges: boolean, envId: string,
                    companyCode: string, productCode: string, planCode: string,
                    issueState: string, lob: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);
    filterHeaders.append('lob', lob);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/effdate';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }

  getProjects(sessionToken: string, envId: string): Observable<SelectItem[]> {
    let filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('sessionToken', sessionToken);
    filterHeaders.append('envId', envId);

    let options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/project';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => {return <SelectItem[]> response.json()});
  }
}
