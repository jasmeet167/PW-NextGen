import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { BusinessRulesViewComponent } from './view/business-rules-view.component';

const routes: Routes = [
  { path: '', component: BusinessRulesViewComponent }
];

export const routing = RouterModule.forChild(routes);
