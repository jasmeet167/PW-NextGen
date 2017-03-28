import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';

import 'rxjs/add/operator/map';

@Injectable()
export class FilterServiceET
{
 constructor(private http: Http) {

  }



  getTables(authToken: string, envId: string,compCode:string): Observable<SelectItem[]> {
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

}