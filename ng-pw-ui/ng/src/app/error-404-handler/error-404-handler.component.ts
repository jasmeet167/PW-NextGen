import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-404-handler',
  template: `
    <p-panel header="Navigation Error">
      <div class="ui-g">
        <div class="ui-g-1" style="display:flex; align-items: center;">
          <img src="../../assets/img/messagebox_critical.png">
        </div>
        <div class="ui-g-11">
          <div><h2>Page not Found</h2></div>
          <div>The provided URL was incorrect, or there is an application problem.</div>
          <div style="margin-bottom: 30px;">Either way, the system is unable to complete the navigation.</div>
          <div>
            <button pButton class="ui-button-info" type="button" (click)="onclick()" label="Return" icon="fa-arrow-left"></button>
          </div>
        </div>
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
