import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { ContinuityCheckComponent } from './continuity-check.component';

const routes: Routes = [
  { path: '', component: ContinuityCheckComponent }
];

export const routing = RouterModule.forChild(routes);
