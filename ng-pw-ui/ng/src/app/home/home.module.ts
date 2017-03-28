// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { MenubarModule, ContextMenuModule, MenuItem } from 'primeng/primeng';
import { TabViewModule } from 'primeng/primeng';
import { TreeModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import  { PickListModule } from 'primeng/primeng';

// Module components
import { HomeComponent } from './home.component';
import { BusinessRuleSearchComponent } from './component/business-rule-search/business-rule-search.component';
import { BusinessRuleTreeComponent } from './component/business-rule-tree/business-rule-tree.component';
import { EntireTableViewComponent } from './component/entire-table-view/entire-table-view.component';
import { ChangesOnlyComponent } from './component/changes-only/changes-only.component';
import { ApplyChangesComponent } from './component/apply-changes/apply-changes.component';
import { AuditErrorComponent } from './component/audit-error/audit-error.component';
import { PromoteComponent } from './component/promote/promote.component';
import { SummaryComponent } from './component/summary/summary.component';
import { ContinuityCheckComponent } from './component/continuity-check/continuity-check.component';

// Module services
import { MenuService } from 'app/util/service/menu.service';
import { FilterService } from './component/business-rule-search/service/filter.service';
import { BusinessRuleTreeService } from './component/business-rule-tree/service/business-rule-tree.service';
import { AboutService } from './service/about.service';
import { FilterServiceCO } from './component/changes-only/service/filter.service_CO';
import {FilterServiceET} from './component/entire-table-view/service/filter.service_ET'
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
    ContextMenuModule,
    TabViewModule,
    TreeModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    HomeRoutingModule,
    PickListModule
    
 ],
  declarations: [
    HomeComponent,
    BusinessRuleSearchComponent,
    BusinessRuleTreeComponent,
    EntireTableViewComponent,
    ChangesOnlyComponent,
    ApplyChangesComponent,
    AuditErrorComponent,
    PromoteComponent,
    SummaryComponent,
    ContinuityCheckComponent
  ],
  providers: [
    MenuService,
    FilterService,
    BusinessRuleTreeService,
    AboutService,
    FilterServiceCO,
    FilterServiceET
  ]
})
export default class HomeModule {
}
