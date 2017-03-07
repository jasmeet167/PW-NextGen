import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

export const routes: Routes = [
  { path: '',       redirectTo: 'login', pathMatch: 'full' },
  { path: 'login',  loadChildren: './login/login.module#LoginModule' },
  { path: 'home',   loadChildren: './home/home.module#HomeModule' },
  { path: '**',     loadChildren: './page-not-found/page-not-found.module#PageNotFoundModule' }
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
