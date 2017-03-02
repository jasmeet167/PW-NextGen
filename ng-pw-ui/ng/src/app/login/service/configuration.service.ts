import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Configuration } from '../model/configuration';

import { Observable } from 'rxjs/Observable';

@Injectable()
export class ConfigurationService {
  constructor(private http: Http) {
  }

  getConfiguration(): Observable<Configuration> {
    return this.http.get('../../assets/data/configuration.json')
               .map(response => { return <Configuration> response.json(); });
   }
}
