import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { TreeNode } from 'primeng/primeng';

import { TreeNodeData } from '../model/tree-node-data';
import { TreeNodePlanKey } from '../model/tree-node-plan-key';

@Injectable()
export class BusinessRuleTreeService {
  constructor(private http: Http) {
  }

  getBusinessRuleTreeCore(authToken: string, envId: string, companyCode: string,
                          productCode: string, includeOrphans: boolean): Observable<TreeNode[]> {
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
    if (includeOrphans) {
      filterHeaders.append('includeOrphans', includeOrphans.toString());
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

  getBusinessRuleTreePlanList(authToken: string, lazyType: TreeNodeData.LazyTypeEnum, envId: string,
                              companyCode: string, productCode: string, planCode: string,
                              issueState: string, lob: string, effDate: string,
                              viewChanges: boolean, includeOrphans: boolean): Observable<TreeNode[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Accept', 'application/json');
    filterHeaders.append('authToken', authToken);
    if (lazyType) {
      filterHeaders.append('lazyType', lazyType.toString());
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

  getBusinessRuleTreePlanDetails(authToken: string, envId: string, companyCode: string,
                                 planKey: TreeNodePlanKey, viewChanges: boolean): Observable<TreeNode[]> {
    const filterHeaders: Headers = new Headers();
    filterHeaders.append('Content-Type', 'application/json');
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
    options.body = planKey;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rule-tree/plan/detail';
    options.method = RequestMethod.Post;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRuleTreeOrphanSubsets(authToken: string, envId: string,
                                   companyCode: string, productCode: string, planCode: string,
                                   issueState: string, lob: string, effDate: string,
                                   viewChanges: boolean): Observable<TreeNode[]> {
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

    const options: RequestOptions = new RequestOptions();
    options.headers = filterHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rule-tree/orphans';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }
}
