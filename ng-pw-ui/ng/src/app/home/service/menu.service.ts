import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { MenuItem } from 'primeng/primeng';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class MenuService {
  constructor(private http: Http) {
  }

  getMenu(): Promise<MenuItem[]> {
    return this.http.get('../../assets/data/menu.json')
                    .toPromise()
                    .then(res => <MenuItem[]> res.json().data)
                    .then(data => { return data; });
    }
}
