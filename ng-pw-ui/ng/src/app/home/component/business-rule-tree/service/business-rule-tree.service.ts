import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { TreeNode } from 'primeng/primeng';

@Injectable()
export class BusinessRuleTreeService {
  constructor(private http: Http) {
  }

  getBusinessRuleTreeCore(authToken: string, envId: string, companyCode: string,
                          productCode: string): Observable<TreeNode[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    if (envId) {
      filterHeaders.append('envId', envId);
    }
    if (companyCode) {
      filterHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      filterHeaders.append('productCode', productCode);
    }

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/business-rule-tree/core';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRuleTreeCommonTablesList(authToken: string, envId: string,
                                      companyCode: string, viewChanges: boolean) {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    if (envId) {
      filterHeaders.append('envId', envId);
    }
    if (companyCode) {
      filterHeaders.append('companyCode', companyCode);
    }
    filterHeaders.append('viewChanges', viewChanges.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rule-tree/common/tables';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRuleTreePlanList(authToken: string, lazyType: string, envId: string,
                              companyCode: string, productCode: string, planCode: string,
                              issueState: string, lob: string, effDate: string,
                              viewChanges: boolean, includeOrphans: boolean): Observable<TreeNode[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    if (lazyType) {
      filterHeaders.append('lazyType', lazyType);
    }
    if (envId) {
      filterHeaders.append('envId', envId);
    }
    if (companyCode) {
      filterHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      filterHeaders.append('productCode', productCode);
    }
    if (planCode) {
      filterHeaders.append('planCode', planCode);
    }
    if (issueState) {
      filterHeaders.append('issueState', issueState);
    }
    if (lob) {
      filterHeaders.append('lob', lob);
    }
    if (effDate) {
      filterHeaders.append('effDate', effDate);
    }
    filterHeaders.append('viewChanges', viewChanges.toString());
    filterHeaders.append('includeOrphans', includeOrphans.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rule-tree/plan/list';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRuleTree(authToken: string, viewChanges: boolean, envId: string,
                      companyCode: string, productCode: string, planCode: string,
                      issueState: string, lob: string, effDate: string,
                      includeOrphans: boolean): Observable<TreeNode[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    filterHeaders.append('viewChanges', viewChanges.toString());
    if (envId) {
      filterHeaders.append('envId', envId);
    }
    if (companyCode) {
      filterHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      filterHeaders.append('productCode', productCode);
    }
    if (planCode) {
      filterHeaders.append('planCode', planCode);
    }
    if (issueState) {
      filterHeaders.append('issueState', issueState);
    }
    if (lob) {
      filterHeaders.append('lob', lob);
    }
    if (effDate) {
      filterHeaders.append('effDate', effDate);
    }
    filterHeaders.append('includeOrphans', includeOrphans.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rule-tree/search';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }
}
