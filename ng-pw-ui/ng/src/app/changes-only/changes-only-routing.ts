import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

import { ChangesOnlyComponent } from './changes-only.component';

const routes: Routes = [
  { path: '', component: ChangesOnlyComponent }
];

export const routing = RouterModule.forChild(routes);
