import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-404-handler',
  template: `
    <p-messages [value]="msgs"></p-messages>
    <p-panel header="Navigation Error">
      <div><h3>Page not Found</h3></div>
      <div>The provided URL was incorrect, or there is an application problem.</div>
      <div style="margin-bottom: 30px;">Either way, the system is unable to complete the navigation.</div>
      <button pButton class="ui-button-info" type="button" (click)="onclick()" label="Return" icon="fa-arrow-left"></button>
    </p-panel>
  `
})
export class Error404HandlerComponent {
  constructor(private router: Router) {
  }

  onclick() {
    this.router.navigate(['/login']);
  }
}
