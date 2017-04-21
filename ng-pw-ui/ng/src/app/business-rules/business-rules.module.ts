// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { ToolbarModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import { TreeModule } from 'primeng/primeng';
import { ContextMenuModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { BusinessRulesViewComponent } from './view/business-rules-view.component';
import { BusinessRulesSearchComponent } from './search/business-rules-search.component';
import { BusinessRulesTreeComponent } from './tree/business-rules-tree.component';

// Module services
import { SearchService } from 'app/util/service/search.service';
import { BusinessRulesTreeService } from './tree/service/business-rules-tree.service';

// Routing
import { routing } from './business-rules-routing';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    DialogModule,
    ToolbarModule,
    GrowlModule,
    TreeModule,
    ContextMenuModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    routing
 ],
  declarations: [
    BusinessRulesViewComponent,
    BusinessRulesSearchComponent,
    BusinessRulesTreeComponent
  ],
  providers: [
    SearchService,
    BusinessRulesTreeService
  ]
})
export default class BusinessRulesModule {
}
