// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

@NgModule({
  imports: [
    CommonModule,
    DialogModule,
    ButtonModule
  ]
})
export class NotificationModule {
}
