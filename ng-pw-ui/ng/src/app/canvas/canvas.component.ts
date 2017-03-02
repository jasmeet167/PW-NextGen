import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-canvas',
  template: `
    <router-outlet></router-outlet>
  `
})
export class CanvasComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit() {
     this.router.navigate(['/login']);
  }
}
