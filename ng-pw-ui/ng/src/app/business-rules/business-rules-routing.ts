import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { BusinessRulesSearchComponent } from './search/business-rules-search.component';

const routes: Routes = [
  { path: '', component: BusinessRulesSearchComponent }
];

export const routing = RouterModule.forChild(routes);
