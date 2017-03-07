import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BadPageComponent } from './bad-page.component';

const routes: Routes = [
  { path: '**', component: BadPageComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class BadPageRoutingModule {
}
