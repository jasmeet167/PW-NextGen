// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';

// PrimeNG
import { MenubarModule } from 'primeng/primeng';
import { TabMenuModule } from 'primeng/primeng';
import { DialogModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { HomeDecoratorComponent } from './home-decorator.component';

// Module services
import { MenuService } from 'app/util/service/menu.service';
import { AboutService } from './service/about.service';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    MenubarModule,
    TabMenuModule,
    DialogModule,
    ButtonModule
  ],
  exports: [
    HomeDecoratorComponent
  ],
  declarations: [
    HomeDecoratorComponent
  ],
  providers: [
    MenuService,
    AboutService
  ]
})
export class HomeDecoratorModule {
}
