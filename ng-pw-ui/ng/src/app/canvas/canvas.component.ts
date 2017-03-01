import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/primeng';

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
