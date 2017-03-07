import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// PrimeNG
import { PanelModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components and services
import { BadPageComponent } from './bad-page.component';

// Routing module
import { BadPageRoutingModule } from './bad-page-routing.module';

@NgModule({
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    BadPageRoutingModule
  ],
  declarations: [
    BadPageComponent
  ]
})
export class BadPageModule {
}
