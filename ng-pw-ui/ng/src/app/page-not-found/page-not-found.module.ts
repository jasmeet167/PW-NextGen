// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// PrimeNG
import { PanelModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { PageNotFoundComponent } from './page-not-found.component';

// Routing module
import { PageNotFoundRoutingModule } from './page-not-found-routing.module';

@NgModule({
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    PageNotFoundRoutingModule
  ],
  declarations: [
    PageNotFoundComponent
  ]
})
export class PageNotFoundModule {
}
