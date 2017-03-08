// Core
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// Application root
import { AppComponent } from './app.component';

// Feature modules - modules, which are lazy-loaded need not be imported
import { PageNotFoundModule } from './page-not-found/page-not-found.module';

// Routing module
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
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
