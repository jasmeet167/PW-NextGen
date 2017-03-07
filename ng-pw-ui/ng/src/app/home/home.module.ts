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

// Module components and services
import { HomeComponent } from './home.component';
import { BusinessRuleSearchComponent } from './business-rule-search/business-rule-search.component';
import { EntireTableViewComponent } from './entire-table-view/entire-table-view.component';
import { ChangesOnlyComponent } from './changes-only/changes-only.component';
import { ApplyChangesComponent } from './apply-changes/apply-changes.component';
import { AuditErrorComponent } from './audit-error/audit-error.component';
import { PromoteComponent } from './promote/promote.component';
import { SummaryComponent } from './summary/summary.component';
import { ContinuityCheckComponent } from './continuity-check/continuity-check.component';

import { MenuService } from './service/menu.service';
import { FilterService } from './business-rule-search/service/filter.service';
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
export class HomeModule {
}
