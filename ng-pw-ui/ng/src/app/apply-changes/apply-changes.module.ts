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
import { ApplyChangesComponent } from './apply-changes.component';

// Module services
import { SearchService } from 'app/util/service/search.service';

// Routing
import { routing } from './apply-changes-routing';

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
    ApplyChangesComponent
  ],
  providers: [
    SearchService
  ]
})
export default class ApplyChangesModule {
}
