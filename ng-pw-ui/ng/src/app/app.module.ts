import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { MessagesModule } from 'primeng/primeng';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { TabViewModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';

import { CanvasComponent } from './canvas/canvas.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { ConfigurationService } from './login/service/configuration.service';
import { LoginService } from './login/service/login.service';
import { MenuService } from './home/service/menu.service';
import { FilterService } from './home/service/filter.service';

import { appRoutes } from './app.routes';

@NgModule({
  declarations: [
    CanvasComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    MenubarModule,
    MessagesModule,
    PanelModule,
    TabViewModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    InputTextModule
  ],
  providers: [ConfigurationService, LoginService, MenuService, FilterService],
  bootstrap: [CanvasComponent]
})

export class AppModule {
}
