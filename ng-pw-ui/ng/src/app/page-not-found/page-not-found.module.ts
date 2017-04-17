// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// PrimeNG
import { PanelModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { PageNotFoundComponent } from './page-not-found.component';

// Routing
import { routing } from './page-not-found-routing';

@NgModule({
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    routing
  ],
  declarations: [
    PageNotFoundComponent
  ]
})
export class PageNotFoundModule {
}
