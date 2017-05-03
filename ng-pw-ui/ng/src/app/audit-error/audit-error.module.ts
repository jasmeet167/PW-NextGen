// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { PickListModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import {RadioButtonModule} from 'primeng/primeng';
import {ToggleButtonModule} from 'primeng/primeng';
import {CalendarModule} from 'primeng/primeng';

// Module components
import { AuditErrorComponent } from './audit-error.component';

// Module services
import { SearchService } from 'app/util/service/search.service';

// Routing
import { routing } from './audit-error-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    PickListModule,
    ButtonModule,
    RadioButtonModule,
    ToggleButtonModule,
    CalendarModule,
    routing
 ],
  declarations: [
    AuditErrorComponent
  ],
    providers: [
    SearchService
  ]
})
export default class AuditErrorModule {
}
