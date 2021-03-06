// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DropdownModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { ContinuityCheckComponent } from './continuity-check.component';

// Module services
import { SearchService } from 'app/util/service/search.service';

// Routing
import { routing } from './continuity-check-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DropdownModule,
    CheckboxModule,
    ButtonModule,
    routing
 ],
  declarations: [
    ContinuityCheckComponent
  ],
  providers: [
    SearchService
  ]
})
export default class ContinuityCheckModule {
}
