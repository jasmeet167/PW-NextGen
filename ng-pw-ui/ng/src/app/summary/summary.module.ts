// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { CalendarModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { SummaryComponent } from './summary.component';

// Module services
import { SearchService } from 'app/util/service/search.service';

// Routing
import { routing } from './summary-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    CheckboxModule,
    CalendarModule,
    ButtonModule,
    routing
 ],
  declarations: [
    SummaryComponent
  ],
  providers: [
    SearchService
  ]
})
export default class SummaryModule {
}
