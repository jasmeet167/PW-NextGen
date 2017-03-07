import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';

// Module components and services
import { LoginComponent } from './login.component';
import { ConfigService } from './service/config.service';
import { LoginService } from './service/login.service';

// Routing module
import { LoginRoutingModule } from './login-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    DialogModule,
    PanelModule,
    ButtonModule,
    InputTextModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    ConfigService,
    LoginService
  ]
})
export class LoginModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: LoginModule,
      providers: [
        ConfigService,
        LoginService
      ]
    };
  }
}
