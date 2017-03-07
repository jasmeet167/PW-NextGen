import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// Application root
import { AppComponent } from './app.component';

// Feature modules
import { LoginModule } from './login/login.module';
import { HomeModule } from './home/home.module';
import { PageNotFoundModule } from './page-not-found/page-not-found.module';

// Routing module
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    LoginModule.forRoot(),
    HomeModule,
    PageNotFoundModule,
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
