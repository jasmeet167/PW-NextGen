import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';

import { ChangesFilterData } from '../component/changes-only/model/changes-filter-data';
import { ApplyFilterData } from 'app/home/component/apply-changes/model/apply-filter-data';
import { PromoteFilterData } from 'app/home/component/promote/model/promote-filter-data';

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

  getChangesWipDetails(authToken: string, envId: string): Observable<ChangesFilterData> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/search/changes/filter';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <ChangesFilterData> response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
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
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getApplyChangesDetails(authToken: string, envId: string): Observable<ApplyFilterData> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/search/apply/filter';
    options.method = RequestMethod.Get;


    return this.http.request(new Request(options))
      .map(response => { return <ApplyFilterData>response.json(); });

  }

  getPromoteDetails(authToken: string, sourceEnvId: string, targetEnvId: string): Observable<PromoteFilterData> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('sourceEnvId', sourceEnvId);
    filterHeaders.append('targetEnvId', targetEnvId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/search/promote/filter';
    options.method = RequestMethod.Get;


    return this.http.request(new Request(options))
      .map(response => { return <PromoteFilterData>response.json(); });

  }

  getCompanyForSummary(authToken: string, filterAspect: string, envId: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/company';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getProductOptionsForSummary(authToken: string, filterAspect: string, envId: string,
    companyCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/product';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getPlanCodeOptionsForSummary(authToken: string, filterAspect: string, envId: string,
    companyCode: string, productCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/plan';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getIssueStateOptionsForSummary(authToken: string, filterAspect: string, envId: string,
    companyCode: string, productCode: string, planCode: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/state';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getLobOptionsForSummary(authToken: string, filterAspect: string, envId: string,
    companyCode: string, productCode: string, planCode: string,
    issueState: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/lob';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

  getEffDateOptionsForSummary(authToken: string, filterAspect: string, envId: string,
    companyCode: string, productCode: string, planCode: string,
    issueState: string, lob: string): Observable<SelectItem[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('filterAspect', filterAspect);
    filterHeaders.append('envId', envId);
    filterHeaders.append('companyCode', companyCode);
    filterHeaders.append('productCode', productCode);
    filterHeaders.append('planCode', planCode);
    filterHeaders.append('issueState', issueState);
    filterHeaders.append('lob', lob);

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'search/summary/effdate';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
      .map(response => { return <SelectItem[]>response.json(); });
  }

}
