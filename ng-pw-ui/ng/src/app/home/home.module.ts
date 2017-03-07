import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import { TabViewModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components and services
import { HomeComponent } from './home.component';
import { MenuService } from './service/menu.service';
import { FilterService } from './service/filter.service';
import { AboutService } from './service/about.service';

// Routing module
import { HomeRoutingModule } from './home-routing.module';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DialogModule,
    PanelModule,
    MenubarModule,
    TabViewModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    HomeRoutingModule
 ],
  declarations: [
    HomeComponent
  ],
  providers: [
    MenuService,
    FilterService,
    AboutService
  ]
})
export class HomeModule {
}
