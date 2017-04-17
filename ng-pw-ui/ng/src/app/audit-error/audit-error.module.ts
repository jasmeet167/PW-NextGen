// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// PrimeNG

// Module components
import { AuditErrorComponent } from './audit-error.component';

// Module services

// Routing
import { routing } from './audit-error-routing';

@NgModule({
  imports: [
    CommonModule,
    routing
 ],
  declarations: [
    AuditErrorComponent
  ]
})
export default class AuditErrorModule {
}
