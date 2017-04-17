// Core
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// PrimeNG
import { MenubarModule } from 'primeng/primeng';
import { TabMenuModule } from 'primeng/primeng';
import { DialogModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Application root
import { AppComponent } from 'app/app.component';

// Eagerly loaded feature modules
import { NotificationModule } from 'app/notification/notification.module';
import { HomeDecoratorModule } from 'app/home-decorator/home-decorator.module';
import { PageNotFoundModule } from 'app/page-not-found/page-not-found.module';

// Components
import { NotificationComponent } from 'app/notification/notification.component';

// Services
import { NotificationService } from 'app/notification/service/notification.service';

// Routing
import { routing } from 'app/app-routing';

@NgModule({
  imports: [
    BrowserModule,
    MenubarModule,
    TabMenuModule,
    DialogModule,
    ButtonModule,
    NotificationModule,
    HomeDecoratorModule,
    PageNotFoundModule,
    routing
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
