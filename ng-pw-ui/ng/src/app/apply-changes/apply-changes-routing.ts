import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { ApplyChangesComponent } from './apply-changes.component';

const routes: Routes = [
  { path: '', component: ApplyChangesComponent }
];

export const routing = RouterModule.forChild(routes);
