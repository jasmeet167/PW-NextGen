import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { EntireTableViewComponent } from './entire-table-view.component';

const routes: Routes = [
  { path: '', component: EntireTableViewComponent }
];

export const routing = RouterModule.forChild(routes);
