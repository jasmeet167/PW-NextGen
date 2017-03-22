import { Component, OnInit, ViewChild } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { BusinessRuleTreeComponent } from '../business-rule-tree/business-rule-tree.component';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from './service/filter.service';

@Component({
  selector: 'app-business-rule-search',
  templateUrl: './business-rule-search.component.html',
  styleUrls: ['./business-rule-search.component.css']
})
export class BusinessRuleSearchComponent implements OnInit  {
  public viewChangesOptions: SelectItem[];
  public viewChanges: boolean;

  public envIdOptions: SelectItem[];
  public envId: string;

  public companyCodeOptions: SelectItem[];
  public companyCode: string;
  public isCompanyCodeDisabled: boolean;

  public productCodeOptions: SelectItem[];
  public productCode: string;
  public isProductCodeDisabled: boolean;

  public planCodeOptions: SelectItem[];
  public planCode: string;
  public isPlanCodeDisabled: boolean;

  public issueStateOptions: SelectItem[];
  public issueState: string;
  public isIssueStateDisabled: boolean;

  public lobOptions: SelectItem[];
  public lob: string;
  public isLobDisabled: boolean;

  public effDateOptions: SelectItem[];
  public effDate: string;
  public isEffDateDisabled: boolean;

  public projectRows: SelectItem[];
  public projects: string[];

  public includeOrphans: boolean;
  public isIncludeOrphansDisabled: boolean;

  public rememberSelections: boolean;
  public isGoDisabled: boolean;

  private authToken: string;

  @ViewChild('tree') tree: BusinessRuleTreeComponent;

  constructor(private notificationService: NotificationService, private filterService: FilterService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.viewChangesOptions = [];
    this.viewChangesOptions.push({label: 'Rules with Changes', value: true});
    this.viewChangesOptions.push({label: 'Rules', value: false});
    this.viewChanges = true;

    let envOptions: SelectItem[];
    this.notificationService.showWaitIndicator(true);
    this.filterService.getEnvOptions(this.authToken)
        .subscribe(
          res => envOptions = res,
          err => {
              if (err.status !== 404) {
                this.notificationService.handleError(err);
              }
              this.buildEnvDropdown(null);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.buildEnvDropdown(envOptions);
              this.notificationService.showWaitIndicator(false);
          }
        );

    this.isCompanyCodeDisabled = true;
    this.buildCompanyDropdown(null);

    this.isProductCodeDisabled = true;
    this.buildProductDropdown(null);

    this.isPlanCodeDisabled = true;
    this.buildPlanCodeDropdown(null);

    this.isIssueStateDisabled = true;
    this.buildIssueStateDropdown(null);

    this.isLobDisabled = true;
    this.buildLobDropdown(null);

    this.isEffDateDisabled = true;
    this.buildEffDateDropdown(null);

    this.projectRows = <SelectItem[]> [];

    this.includeOrphans = false;
    this.isIncludeOrphansDisabled = true;

    this.rememberSelections = false;

    this.evaluateStatusOfGo();
  }

  onEnvIdChange() {
    this.companyCode = null;
    this.productCode = null;
    this.planCode = null;
    this.issueState = null;
    this.lob = null;
    this.effDate = null;
    this.projects = <string[]> [];

    this.isProductCodeDisabled = true;
    this.isPlanCodeDisabled = true;
    this.isIssueStateDisabled = true;
    this.isLobDisabled = true;
    this.isEffDateDisabled = true;

    this.isIncludeOrphansDisabled = true;
    this.includeOrphans = false;

    if (this.envId == null) {
      this.isCompanyCodeDisabled = true;
    } else {
      this.isCompanyCodeDisabled = false;

      let companyOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getCompanyOptions(this.authToken, this.viewChanges, this.envId)
          .subscribe(
            res => companyOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildCompanyDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => this.buildCompanyDropdown(companyOptions)
          );

      let projectOptions: SelectItem[];
      this.filterService.getProjects(this.authToken, this.envId)
          .subscribe(
            res => projectOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildProjects(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildProjects(projectOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }

    this.evaluateStatusOfGo();
  }

  onCompanyCodeChange() {
    this.productCode = null;
    this.planCode = null;
    this.issueState = null;
    this.lob = null;
    this.effDate = null;

    this.isPlanCodeDisabled = true;
    this.isIssueStateDisabled = true;
    this.isLobDisabled = true;
    this.isEffDateDisabled = true;

    this.isIncludeOrphansDisabled = true;
    this.includeOrphans = false;

    if (this.companyCode == null) {
      this.isProductCodeDisabled = true;
    } else {
      this.isProductCodeDisabled = false;

      let productOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getProductOptions(this.authToken, this.viewChanges, this.envId,
                                           this.companyCode)
          .subscribe(
            res => productOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildProductDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildProductDropdown(productOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }

    this.evaluateStatusOfGo();
  }

  onProductChange() {
    this.planCode = null;
    this.issueState = null;
    this.lob = null;
    this.effDate = null;

    this.isIssueStateDisabled = true;
    this.isLobDisabled = true;
    this.isEffDateDisabled = true;

    if (this.productCode == null) {
      this.isPlanCodeDisabled = true;
      this.isIncludeOrphansDisabled = true;
      this.includeOrphans = false;
    } else {
      this.isPlanCodeDisabled = false;
      this.isIncludeOrphansDisabled = false;

      let planCodeOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getPlanCodeOptions(this.authToken, this.viewChanges, this.envId,
                                            this.companyCode, this.productCode)
          .subscribe(
            res => planCodeOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildPlanCodeDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildPlanCodeDropdown(planCodeOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }

    this.evaluateStatusOfGo();
  }

  onPlanCodeChange() {
    this.issueState = null;
    this.lob = null;
    this.effDate = null;

    this.isLobDisabled = true;
    this.isEffDateDisabled = true;

    if (this.planCode == null) {
      this.isIssueStateDisabled = true;
    } else {
      this.isIssueStateDisabled = false;

      let issueStateOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getIssueStateOptions(this.authToken, this.viewChanges, this.envId,
                                              this.companyCode, this.productCode, this.planCode)
          .subscribe(
            res => issueStateOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildIssueStateDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildIssueStateDropdown(issueStateOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }
  }

  onIssueStateChange() {
    this.lob = null;
    this.effDate = null;

    this.isEffDateDisabled = true;

    if (this.issueState == null) {
      this.isLobDisabled = true;
    } else {
      this.isLobDisabled = false;

      let lobOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getLobOptions(this.authToken, this.viewChanges, this.envId,
                                       this.companyCode, this.productCode, this.planCode,
                                       this.issueState)
          .subscribe(
            res => lobOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildLobDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildLobDropdown(lobOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }
  }

  onLobChange() {
    this.effDate = null;

    if (this.lob == null) {
      this.isEffDateDisabled = true;
    } else {
      this.isEffDateDisabled = false;

      let effDateOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getEffDateOptions(this.authToken, this.viewChanges, this.envId,
                                           this.companyCode, this.productCode, this.planCode,
                                           this.issueState, this.lob)
          .subscribe(
            res => effDateOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildEffDateDropdown(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildEffDateDropdown(effDateOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );
    }
  }

  private buildEnvDropdown (options: SelectItem[]) {
    this.envIdOptions = <SelectItem[]> [{label: 'Environment', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.envIdOptions.push(option);
      }
    }
  }

  private buildCompanyDropdown(options: SelectItem[]) {
    this.companyCodeOptions = <SelectItem[]> [{label: 'Company', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.companyCodeOptions.push(option);
      }
    }
  }

  private buildProductDropdown(options: SelectItem[]) {
    this.productCodeOptions = <SelectItem[]> [{label: 'Product', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.productCodeOptions.push(option);
      }
    }
  }

  private buildPlanCodeDropdown(options: SelectItem[]) {
    this.planCodeOptions = <SelectItem[]> [{label: 'Plan Code', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.planCodeOptions.push(option);
      }
    }
  }

  private buildIssueStateDropdown(options: SelectItem[]) {
    this.issueStateOptions = <SelectItem[]> [{label: 'Issue State', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.issueStateOptions.push(option);
      }
    }
  }

  private buildLobDropdown(options: SelectItem[]) {
    this.lobOptions = <SelectItem[]> [{label: 'Line of Business', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.lobOptions.push(option);
      }
    }
  }

  private buildEffDateDropdown(options: SelectItem[]) {
    this.effDateOptions = <SelectItem[]> [{label: 'Plan Effective Date', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.effDateOptions.push(option);
      }
    }
  }

  private buildProjects(rows: SelectItem[]) {
    if (rows == null) {
      this.projectRows = <SelectItem[]> [];
      this.projects = <string[]> [];
    } else {
      this.projectRows = <SelectItem[]> [{ label: 'All Projects', value: null }];
      for (const row of rows) {
        this.projectRows.push(row);
      }
      this.projects = <string[]> [null];
    }
  }

  private evaluateStatusOfGo() {
    if ((this.envId && this.envId.trim() !== '')
    &&  (this.companyCode && this.companyCode.trim() !== '')
    &&  (this.productCode && this.productCode.trim() !== '')) {
      this.isGoDisabled = false;
    } else {
      this.isGoDisabled = true;
    }
  }

  onGoClick() {
    this.tree.showTree();
  }
}
