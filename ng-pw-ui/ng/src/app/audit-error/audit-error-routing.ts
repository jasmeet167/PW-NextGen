import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { AuditErrorComponent } from './audit-error.component';

const routes: Routes = [
  { path: '', component: AuditErrorComponent }
];

export const routing = RouterModule.forChild(routes);
