import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG

// Module components and services

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule
  ],
  declarations: [
  ],
  providers: [
  ]
})
export class ChangesOnlyModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ChangesOnlyModule,
      providers: [
      ]
    };
  }
}
