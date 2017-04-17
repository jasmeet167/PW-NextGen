// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { PickListModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { ChangesOnlyComponent } from './changes-only.component';

// Module services
import { FilterService } from 'app/util/service/filter.service';

// Routing
import { routing } from './changes-only-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    PickListModule,
    ButtonModule,
    routing
 ],
  declarations: [
    ChangesOnlyComponent
  ],
  providers: [
    FilterService
  ]
})
export default class ChangesOnlyModule {
}
