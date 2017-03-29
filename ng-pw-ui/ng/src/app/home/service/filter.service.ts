import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';

import { ChangeOnlyTabMsg } from '../component/changes-only/model/changeonly.message';

import 'rxjs/add/operator/map';

@Injectable()
export class FilterService {
  constructor(private http: Http) {
  }

  getCommonEnvOptions(authToken: string): Observable<SelectItem[]> {
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

  getPlanCompanyOptions(authToken: string, viewChanges: boolean, envId: string): Observable<SelectItem[]> {
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

  getPlanProductOptions(authToken: string, viewChanges: boolean, envId: string,
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

  getPlanPlanCodeOptions(authToken: string, viewChanges: boolean, envId: string,
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

  getPlanIssueStateOptions(authToken: string, viewChanges: boolean, envId: string,
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

  getPlanLobOptions(authToken: string, viewChanges: boolean, envId: string,
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

  getPlanEffDateOptions(authToken: string, viewChanges: boolean, envId: string,
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

  getPlanProjectOptions(authToken: string, envId: string): Observable<SelectItem[]> {
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

  getChangesEnvOptions(authToken: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/search/changes/environment';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
                    .map(response => { return <SelectItem[]> response.json(); });
  }

  getPlanTableOptions(authToken: string, envId: string, compCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', compCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/plan/table';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <SelectItem[]> response.json(); });
  }

  getChangesWipDetails(authToken: string, envId: string): Observable<ChangeOnlyTabMsg> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/search/changes/filter';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <ChangeOnlyTabMsg> response.json(); });
  }
}
