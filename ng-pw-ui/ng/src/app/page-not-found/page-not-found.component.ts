import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  template: `
    <p-panel>
      <p-header>
        <span style="font-size: 1.5em; font-weight: bold;">
          <img src="../../assets/img/messagebox_critical.png" style="vertical-align: middle"> Error
        </span>
      </p-header>
      <div style="margin-top: 20px;"><h4>Page not found.</h4></div>
      <div>The provided URL was incorrect, or there is an application problem.</div>
      <div style="margin-bottom: 30px;">Either way, the system was unable to complete the navigation.</div>
      <div>
        <button pButton class="ui-button-info" type="button" (click)="onclick()"
                label="Go to Log in" icon="fa-arrow-right" iconPos="right"></button>
      </div>
    </p-panel>
  `
})
export class PageNotFoundComponent {
  constructor(private router: Router) {
  }

  onclick() {
    this.router.navigate(['/login']);
  }
}
