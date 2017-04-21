import { Injectable } from '@angular/core';

import { Http } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { MenuItem } from 'primeng/primeng';

@Injectable()
export class MenuService {
  constructor(private http: Http) {
  }

  getMenu(resourceUrl: string): Observable<MenuItem[]> {
    return this.http.get(resourceUrl)
               .map(response => { return <MenuItem[]> response.json(); });
    }
}
