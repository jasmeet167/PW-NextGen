import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { PromoteComponent } from './promote.component';

const routes: Routes = [
  { path: '', component: PromoteComponent }
];

export const routing = RouterModule.forChild(routes);
