import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { TreeNode } from 'primeng/primeng';

@Injectable()
export class BusinessRuleTreeService {
  constructor(private http: Http) {
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
    options.url = sessionStorage['restServiceBaseUrl'] + 'tree/search';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }
}
