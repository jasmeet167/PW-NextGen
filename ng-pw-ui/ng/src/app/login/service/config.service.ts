import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { Configuration } from '../model/configuration';

@Injectable()
export class ConfigService {
  constructor(private http: Http) {
  }

  getConfiguration(): Observable<Configuration> {
    return this.http.get('../../assets/data/configuration.json')
               .map(response => { return <Configuration> response.json(); });
   }
}
