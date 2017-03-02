import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { Error404HandlerComponent } from './error-404-handler/error-404-handler.component';

export const appRoutes: Routes = [
  { path: '',       component: LoginComponent, pathMatch: 'full' },
  { path: 'login',  component: LoginComponent },
  { path: 'home',   component: HomeComponent },
  { path: '**',     component: Error404HandlerComponent }
];
