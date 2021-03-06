import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { SearchService } from 'app/util/service/search.service';

@Component({
  templateUrl: './entire-table-view.component.html',
  styleUrls: ['./entire-table-view.component.css']
})
export class EntireTableViewComponent implements OnInit {
  public filterChangesOptions: SelectItem[];
  public filterChanges: boolean;

  public filterEnvOptions: SelectItem[];
  public filterEnv: string;

  public filterCompanyOptions: SelectItem[];
  public filterCompany: string;
  public filterCompanyDisabled: boolean;

  private authToken: string;

  public filterTableRows: SelectItem[];
  public filterTables: string[];

  public filterRememberSelections: boolean;
  public isGoDisabled: boolean;

  constructor(private searchService: SearchService, private notificationService: NotificationService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.filterChangesOptions = [];
    this.filterChangesOptions.push({ label: 'Rules with Changes', value: true });
    this.filterChangesOptions.push({ label: 'Rules', value: false });
    this.filterChanges = true;

    let envOptions: SelectItem[];
    this.notificationService.showWaitIndicator(true);

    this.searchService.getCommonEnvOptions(this.authToken)
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
    this.filterCompanyDisabled = true;
    this.buildCompanyDropdown(null);
    this.filterRememberSelections = true;
    this.filterTableRows = <SelectItem[]>[];

    this.evaluateStatusOfGo();
  }

  private buildEnvDropdown(options: SelectItem[]) {
    this.filterEnvOptions = <SelectItem[]>[{ label: 'Environment', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.filterEnvOptions.push(option);
      }
    }
  }

  private buildCompanyDropdown(options: SelectItem[]) {
    this.filterCompanyOptions = <SelectItem[]>[{ label: 'Company', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.filterCompanyOptions.push(option);
      }
    }
  }

  onChangesChange() {
    this.filterEnv = null;
    this.filterCompany = null;
    this.filterTableRows = <SelectItem[]>[];
  }

  onEnvChange() {
    this.filterCompany = null;
    this.filterTableRows = <SelectItem[]>[];
    if (this.filterEnv == null) {
      this.filterCompanyDisabled = true;
    } else {
      this.filterCompanyDisabled = false;

      let companyOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.searchService.getPlanCompanyOptions(this.authToken, this.filterChanges, this.filterEnv)
        .subscribe(
        res => companyOptions = res,
        err => {
          if (err.status !== 404) {
            this.notificationService.handleError(err);
          }
          this.buildCompanyDropdown(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildCompanyDropdown(companyOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfGo();
  }

  onCompanyChange() {
    this.filterTableRows = <SelectItem[]>[];
    let tableRowsOptions: SelectItem[];
    this.notificationService.showWaitIndicator(true);
    this.searchService.getPlanTableOptions(this.authToken, this.filterEnv, this.filterCompany)
      .subscribe(
      res => tableRowsOptions = res,
      err => {
        if (err.status !== 404) {
          this.notificationService.handleError(err);
        }
        this.notificationService.showWaitIndicator(false);
      },
      () => {
        this.buildTableRows(tableRowsOptions);
        this.notificationService.showWaitIndicator(false);
      }
      );
    this.evaluateStatusOfGo();
  }

  buildTableRows(tableRowsOptions: SelectItem[]) {
    if (tableRowsOptions == null) {
      this.filterTableRows = <SelectItem[]>[];
      this.filterTables = <string[]>[];
    }
    if (tableRowsOptions != null) {
      for (const row of tableRowsOptions) {
        this.filterTableRows.push(row);
      }
      this.filterTables = <string[]>[null];
    }
  }

  private evaluateStatusOfGo() {
    if (this.filterCompany && this.filterCompany.trim() !== '') {
      this.isGoDisabled = false;
    } else {
      this.isGoDisabled = true;
    }
  }

  onGoClick() {
    console.log('Go functionality currently not available');
  }

}
