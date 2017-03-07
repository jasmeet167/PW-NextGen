import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

export const routes: Routes = [
  { path: '',       redirectTo: 'login', pathMatch: 'full' },
  { path: 'login',  loadChildren: './login/login.module#LoginModule' },
  { path: 'home',   loadChildren: './home/home.module#HomeModule' },
  { path: '**',     loadChildren: './bad-page/bad-page.module#BadPageModule' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
