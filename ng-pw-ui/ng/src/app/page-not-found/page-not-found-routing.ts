import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

export const routing = RouterModule.forChild(routes);
