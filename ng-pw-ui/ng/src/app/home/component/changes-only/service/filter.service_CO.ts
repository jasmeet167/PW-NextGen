import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SelectItem } from 'primeng/primeng';
import {ChangeOnlyTabMsg} from 'app/home/component/changes-only/model/changeonly.message'

import 'rxjs/add/operator/map';




@Injectable()
export class FilterServiceCO 
{

constructor( private http:Http)
{

}

getChangedEnvOptions(authToken: string): Observable<SelectItem[]>
{
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

getChangedWIPDetails(authToken: string,envId:string):Observable<ChangeOnlyTabMsg>
{
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