import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';

import 'rxjs/add/operator/map';

@Injectable()
export class FilterService {
  constructor(private http: Http) {
  }

  getEnvOptions(authToken: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/common/environment';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getCompanyOptions(authToken: string, viewChanges: boolean, envId: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/company';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getProductOptions(authToken: string, viewChanges: boolean, envId: string,
                    companyCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/product';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getPlanCodeOptions(authToken: string, viewChanges: boolean, envId: string,
                     companyCode: string, productCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/plan';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getIssueStateOptions(authToken: string, viewChanges: boolean, envId: string,
                     companyCode: string, productCode: string, planCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/state';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getLobOptions(authToken: string, viewChanges: boolean, envId: string,
                companyCode: string, productCode: string, planCode: string,
                issueState: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/lob';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getEffDateOptions(authToken: string, viewChanges: boolean, envId: string,
                    companyCode: string, productCode: string, planCode: string,
                    issueState: string, lob: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);
    filterHeaders.append('lob', lob);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/effdate';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getProjects(authToken: string, envId: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/project';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }
}
