// Core
import { NgModule } from '@angular/core';
import { ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';

// Module components
import { LoginComponent } from './login.component';

// Module services
import { ConfigService } from './service/config.service';
import { LoginService } from './service/login.service';

// Routing
import { routing } from './login-routing';

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
    routing
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    ConfigService,
    LoginService
  ]
})
export default class LoginModule {
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
