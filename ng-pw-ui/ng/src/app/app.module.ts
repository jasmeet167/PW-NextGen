// Core
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Application root
import { AppComponent } from 'app/app.component';

// Eagerly loaded feature modules
import { NotificationModule } from 'app/notification/notification.module';
import { PageNotFoundModule } from 'app/page-not-found/page-not-found.module';

// Module components
import { NotificationComponent } from 'app/notification/notification.component';

// Services
import { NotificationService } from 'app/notification/service/notification.service';

// Routing module
import { AppRoutingModule } from 'app/app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    DialogModule,
    ButtonModule,
    NotificationModule,
    PageNotFoundModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    NotificationComponent
  ],
  providers: [
    NotificationService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
