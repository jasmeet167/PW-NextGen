import { Component, OnInit } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { SelectItem } from 'primeng/primeng';
import { NotificationService } from 'app/notification/service/notification.service';
import { SearchService } from 'app/util/service/search.service';
import { AuditFilterData } from 'app/util/model/audit-filter-data';

@Component({
  selector: 'app-audit-error',
  templateUrl: './audit-error.component.html',
  styleUrls: ['./audit-error.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AuditErrorComponent implements OnInit {
  public filterEnvOptions: SelectItem[];
  public filterEnv: string;

  public radioAudit: string;
  public radioError: string;

  public filterPackageInputRows: SelectItem[];
  public filterPackageOutputRows: SelectItem[];
  public filterProjectInputRows: SelectItem[];
  public filterProjectOutputRows: SelectItem[];
  public filterBusinessRuleInputRows: SelectItem[];
  public filterBusinessRuleOutputRows: SelectItem[];
  public filterUserIDInputRows: SelectItem[];
  public filterUserIDOutputRows: SelectItem[];

  public packagesSortOrder: string;
  public projectsSortOrder: string;
  public businessSortOrder: string;
  public usersSortOrder: string;

  public packagesSortPriority: string;
  public projectsSortPriority: string;
  public businessSortPriority: string;
  public usersSortPriority: string;

  public isGoDisabled: boolean;
  public isAudit: boolean;

  public isStartDateDisabled: boolean;
  public isEndDateDisabled: boolean;

  public filterAspect: string;
  private authToken: string;
  constructor(private notificationService: NotificationService, private searchService: SearchService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    this.radioAudit = 'audit';
    this.isAudit = true;

    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }
    let envOptions: SelectItem[];

    this.isStartDateDisabled = true;
    this.isEndDateDisabled = true;
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterBusinessRuleOutputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];
    this.filterUserIDOutputRows = <SelectItem[]>[];

    this.notificationService.showWaitIndicator(true);
    this.searchService.getEnvOptions(this.authToken)
      .subscribe(
      res => envOptions = res,
      err => {
        if (err.status !== 404) {
          this.notificationService.handleError(err);
        }
        this.buildEnvDropdown(null);
        this.notificationService.showWaitIndicator(false);
      },
      () => {
        this.buildEnvDropdown(envOptions);
        this.notificationService.showWaitIndicator(false);
      }
      );
    this.evaluateStatusOfGo();
  }

  private buildEnvDropdown(options: SelectItem[]) {
    this.filterEnvOptions = <SelectItem[]>[{ label: '', value: null }];
    if (options !== null) {
      console.log(this.filterEnvOptions);
      for (const option of options) {
        this.filterEnvOptions.push(option);
      }
    }
  }

  onEnvChange() {
    this.filterAspect = this.isAudit ? 'A' : 'E';
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterBusinessRuleOutputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];
    this.filterUserIDOutputRows = <SelectItem[]>[];

    let auditFilterData: AuditFilterData;
    auditFilterData = null;

    if (this.filterEnv != null) {
      this.notificationService.showWaitIndicator(true);

      this.searchService.getAuditErrorDetails(this.authToken, this.filterAspect, this.filterEnv)
        .subscribe(
        res => auditFilterData = res,
        err => {
          if (err.status === 404) {
            this.notificationService.handleError(err);
          }
          this.buildAuditErrorDetails(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildAuditErrorDetails(auditFilterData);
          this.notificationService.showWaitIndicator(false);
        }
        );
    } else {
      this.filterProjectInputRows = <SelectItem[]>[];
      this.filterPackageInputRows = <SelectItem[]>[];
    }
    this.evaluateStatusOfGo();
  }

  private buildAuditErrorDetails(auditFilterData: AuditFilterData) {
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];

    if (auditFilterData !== null) {
      this.isStartDateDisabled = false;
      this.isEndDateDisabled = false;

      if (auditFilterData.packages != null) {
        for (const packages of auditFilterData.packages) {
          this.filterPackageInputRows.push(packages);
        }
      }
      if (auditFilterData.projects != null) {
        for (const projects of auditFilterData.projects) {
          this.filterProjectInputRows.push({ label: projects, value: projects });
        }
      }
      if (auditFilterData.businessRuleTables != null) {
        for (const businessRule of auditFilterData.businessRuleTables) {
          this.filterBusinessRuleInputRows.push(businessRule);
        }
      }
      if (auditFilterData.users != null) {
        for (const user of auditFilterData.users) {
          this.filterUserIDInputRows.push({ label: user, value: user });
        }
      }
    }
  }

  public sortPickList(filterRows: SelectItem[]) {
    filterRows.sort(function (a, b) { return (a.label > b.label) ? 1 : ((b.label > a.label) ? -1 : 0); });
  }

  private evaluateStatusOfGo() {
    if (this.filterEnv && this.filterEnv.trim() !== '') {
      this.isGoDisabled = false;
    } else {
      this.isGoDisabled = true;
    }
  }

  onRadioAuditClick() {
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterBusinessRuleOutputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];
    this.filterUserIDOutputRows = <SelectItem[]>[];

    this.radioError = null;
    this.radioAudit = 'audit';
    this.isAudit = true;
  }

  onRadioErrorClick() {
    this.filterEnv = null;
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterBusinessRuleOutputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];
    this.filterUserIDOutputRows = <SelectItem[]>[];

    this.radioAudit = null;
    this.radioError = 'error';
    this.isAudit = false;
  }

  onResetClick() {
    this.filterEnv = null;
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterBusinessRuleInputRows = <SelectItem[]>[];
    this.filterBusinessRuleOutputRows = <SelectItem[]>[];
    this.filterUserIDInputRows = <SelectItem[]>[];
    this.filterUserIDOutputRows = <SelectItem[]>[];
    this.isGoDisabled = true;
  }

  onGoClick() {
    if (this.packagesSortPriority != null && this.packagesSortPriority != null) {
      if (this.packagesSortPriority !== '1') {
        this.notificationService.showMessage('Invalid Sort Priority', 1);
        return null;
      }
    }
  }
}
