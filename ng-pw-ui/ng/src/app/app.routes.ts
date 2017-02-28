import { Routes } from '@angular/router';

import { CanvasComponent } from './canvas/canvas.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

export const appRoutes: Routes = [
  { path: '',       component: LoginComponent, pathMatch: 'full' },
  { path: 'login',  component: LoginComponent },
  { path: 'home',   component: HomeComponent } //,
  // { path: '**',     redirectTo: '/',  pathMatch: 'full' }
];
