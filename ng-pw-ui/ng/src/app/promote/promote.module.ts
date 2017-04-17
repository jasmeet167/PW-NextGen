// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';
import { PickListModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { PromoteComponent } from './promote.component';

// Module services
import { FilterService } from 'app/util/service/filter.service';

// Routing
import { routing } from './promote-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    InputTextModule,
    PickListModule,
    ButtonModule,
    routing
 ],
  declarations: [
    PromoteComponent
  ],
  providers: [
    FilterService
  ]
})
export default class PromoteModule {
}
