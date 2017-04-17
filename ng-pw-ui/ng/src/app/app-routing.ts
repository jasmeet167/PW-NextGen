import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

// Most of feature modules are lazy loaded
const routes: Routes = [
  { path: '',                  redirectTo:   'login', pathMatch: 'full' },
  { path: 'login',             loadChildren: './login/login.module' },
  { path: 'business-rules',    loadChildren: './business-rules/business-rules.module' },
  { path: 'entire-table-view', loadChildren: './entire-table-view/entire-table-view.module' },
  { path: 'changes-only',      loadChildren: './changes-only/changes-only.module' },
  { path: 'apply-changes',     loadChildren: './apply-changes/apply-changes.module' },
  { path: 'audit-error',       loadChildren: './audit-error/audit-error.module' },
  { path: 'promote',           loadChildren: './promote/promote.module' },
  { path: 'summary',           loadChildren: './summary/summary.module' },
  { path: 'continuity-check',  loadChildren: './continuity-check/continuity-check.module' },
  { path: '**',                component:    PageNotFoundComponent }
];

export const routing = RouterModule.forRoot(routes);
