import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { MenuItem } from 'primeng/primeng';

import { Observable } from 'rxjs/Observable';

@Injectable()
export class MenuService {
  constructor(private http: Http) {
  }

  getMenu(): Observable<MenuItem[]> {
    return this.http.get('../../assets/data/menu.json')
               .map(response => { return <MenuItem[]> response.json(); });
    }
}
