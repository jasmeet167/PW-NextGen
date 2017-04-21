import { Injectable } from '@angular/core';

import { Http } from '@angular/http';
import { Response } from '@angular/http';
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
import { RequestMethod } from '@angular/http';
import { Request } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { TreeNode } from 'primeng/primeng';

import { TreeNodeData } from '../model/tree-node-data';
import { TreeNodePlanKey } from '../model/tree-node-plan-key';

@Injectable()
export class BusinessRulesTreeService {
  constructor(private http: Http) {
  }

  getBusinessRulesTreeCore(authToken: string, envId: string, companyCode: string,
                          productCode: string, includeOrphans: boolean): Observable<TreeNode[]> {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);
    if (envId) {
      httpHeaders.append('envId', envId);
    }
    if (companyCode) {
      httpHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      httpHeaders.append('productCode', productCode);
    }
    if (includeOrphans) {
      httpHeaders.append('includeOrphans', includeOrphans.toString());
    }

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + '/business-rules-tree/core';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRulesTreeCommonTablesList(authToken: string, envId: string,
                                      companyCode: string, viewChanges: boolean) {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);
    if (envId) {
      httpHeaders.append('envId', envId);
    }
    if (companyCode) {
      httpHeaders.append('companyCode', companyCode);
    }
    httpHeaders.append('viewChanges', viewChanges.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rules-tree/common/tables';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRulesTreePlanList(authToken: string, lazyType: TreeNodeData.LazyTypeEnum, envId: string,
                              companyCode: string, productCode: string, planCode: string,
                              issueState: string, lob: string, effDate: string,
                              viewChanges: boolean, includeOrphans: boolean): Observable<TreeNode[]> {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);
    if (lazyType) {
      httpHeaders.append('lazyType', lazyType.toString());
    }
    if (envId) {
      httpHeaders.append('envId', envId);
    }
    if (companyCode) {
      httpHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      httpHeaders.append('productCode', productCode);
    }
    if (planCode) {
      httpHeaders.append('planCode', planCode);
    }
    if (issueState) {
      httpHeaders.append('issueState', issueState);
    }
    if (lob) {
      httpHeaders.append('lob', lob);
    }
    if (effDate) {
      httpHeaders.append('effDate', effDate);
    }
    httpHeaders.append('viewChanges', viewChanges.toString());
    httpHeaders.append('includeOrphans', includeOrphans.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rules-tree/plan/list';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRulesTreePlanDetails(authToken: string, envId: string, companyCode: string,
                                 planKey: TreeNodePlanKey, viewChanges: boolean): Observable<TreeNode[]> {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Content-Type', 'application/json');
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);
    if (envId) {
      httpHeaders.append('envId', envId);
    }
    if (companyCode) {
      httpHeaders.append('companyCode', companyCode);
    }
    httpHeaders.append('viewChanges', viewChanges.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.body = planKey;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rules-tree/plan/detail';
    options.method = RequestMethod.Post;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }

  getBusinessRulesTreeOrphanSubsets(authToken: string, envId: string,
                                   companyCode: string, productCode: string, planCode: string,
                                   issueState: string, lob: string, effDate: string,
                                   viewChanges: boolean): Observable<TreeNode[]> {
    const httpHeaders: Headers = new Headers();
    httpHeaders.append('Accept', 'application/json');
    httpHeaders.append('authToken', authToken);
    if (envId) {
      httpHeaders.append('envId', envId);
    }
    if (companyCode) {
      httpHeaders.append('companyCode', companyCode);
    }
    if (productCode) {
      httpHeaders.append('productCode', productCode);
    }
    if (planCode) {
      httpHeaders.append('planCode', planCode);
    }
    if (issueState) {
      httpHeaders.append('issueState', issueState);
    }
    if (lob) {
      httpHeaders.append('lob', lob);
    }
    if (effDate) {
      httpHeaders.append('effDate', effDate);
    }
    httpHeaders.append('viewChanges', viewChanges.toString());

    const options: RequestOptions = new RequestOptions();
    options.headers = httpHeaders;
    options.url = sessionStorage['restServiceBaseUrl'] + 'business-rules-tree/orphans';
    options.method = RequestMethod.Get;

    return this.http.request(new Request(options))
               .map(response => { return <TreeNode[]> response.json(); });
  }
}
