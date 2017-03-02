import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-404-handler',
  template: `
    <p-panel header="Page not Found">
      <span style="font-size: 1.5em; font-weight: bold;">
        <img src="../../assets/img/messagebox_critical.png" style="vertical-align: middle"> Error
      </span>
      <div style="margin-top: 20px;">The provided URL was incorrect, or there is an application problem.</div>
      <div style="margin-bottom: 30px;">Either way, the system is unable to complete the navigation.</div>
      <div>
        <button pButton class="ui-button-info" type="button" (click)="onclick()" label="Go to Log in" icon="fa-arrow-left"></button>
      </div>
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
