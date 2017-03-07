import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// Application root
import { AppComponent } from './app.component';

// Feature modules
import { LoginModule } from './login/login.module';
import { HomeModule } from './home/home.module';
import { BadPageModule } from './bad-page/bad-page.module';

// Routing module
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    LoginModule.forRoot(),
    HomeModule,
    BadPageModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
