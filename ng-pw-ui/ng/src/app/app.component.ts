import { Component } from '@angular/core';

@Component({
  selector: 'app-pw',
  template: `
    <app-notification></app-notification>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
}
