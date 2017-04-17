import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { SummaryComponent } from './summary.component';

const routes: Routes = [
  { path: '', component: SummaryComponent }
];

export const routing = RouterModule.forChild(routes);
