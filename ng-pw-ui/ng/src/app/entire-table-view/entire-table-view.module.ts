// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { EntireTableViewComponent } from './entire-table-view.component';

// Module services
import { SearchService } from 'app/util/service/search.service';

// Routing
import { routing } from './entire-table-view-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    routing
 ],
  declarations: [
    EntireTableViewComponent
  ],
  providers: [
    SearchService
  ]
})
export default class EntireTableViewModule {
}
