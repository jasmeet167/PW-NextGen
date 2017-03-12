// Core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

// PrimeNG
import { DialogModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import { MenubarModule, MenuItem } from 'primeng/primeng';
import { TabViewModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { ListboxModule } from 'primeng/primeng';
import { CheckboxModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

// Module components
import { HomeComponent } from './home.component';
import { BusinessRuleSearchComponent } from './component/business-rule-search/business-rule-search.component';
import { EntireTableViewComponent } from './component/entire-table-view/entire-table-view.component';
import { ChangesOnlyComponent } from './component/changes-only/changes-only.component';
import { ApplyChangesComponent } from './component/apply-changes/apply-changes.component';
import { AuditErrorComponent } from './component/audit-error/audit-error.component';
import { PromoteComponent } from './component/promote/promote.component';
import { SummaryComponent } from './component/summary/summary.component';
import { ContinuityCheckComponent } from './component/continuity-check/continuity-check.component';

// Module services
import { MenuService } from './service/menu.service';
import { FilterService } from './component/business-rule-search/service/filter.service';
import { AboutService } from './service/about.service';

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
    TabViewModule,
    DropdownModule,
    ListboxModule,
    CheckboxModule,
    ButtonModule,
    HomeRoutingModule
 ],
  declarations: [
    HomeComponent,
    BusinessRuleSearchComponent,
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
    AboutService
  ]
})
export default class HomeModule {
}