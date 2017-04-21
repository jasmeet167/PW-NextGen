import { Component } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { SearchService } from 'app/util/service/search.service';
import { ApplyFilterData } from 'app/util/model/apply-filter-data';

@Component({
  templateUrl: './apply-changes.component.html',
  styleUrls: ['./apply-changes.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ApplyChangesComponent implements OnInit {
  public filterEnvOptions: SelectItem[];
  public filterEnv: string;

  public enterPackageName: string;

  public filterPackageInputRows: SelectItem[];
  public filterPackageOutputRows: SelectItem[];
  public filterProjectInputRows: SelectItem[];
  public filterProjectOutputRows: SelectItem[];

  public isEnterPackageDisabled: boolean;
  public isGoDisabled: boolean;
  public isResetDisabled: boolean;

  private authToken: string;

  constructor(private notificationService: NotificationService, private searchService: SearchService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }
    let envOptions: SelectItem[];
    this.isEnterPackageDisabled = true;
    this.isResetDisabled = true;
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];

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
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.enterPackageName = null;

    let applyChangesTabMsg: ApplyFilterData;
    applyChangesTabMsg = null;

    if (this.filterEnv != null) {
      this.notificationService.showWaitIndicator(true);
      this.searchService.getApplyChangesDetails(this.authToken, this.filterEnv)
        .subscribe(
        res => applyChangesTabMsg = res,
        err => {
          if (err.status !== 404) {
            this.notificationService.handleError(err);
          }
          this.buildApplyChangesDetails(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildApplyChangesDetails(applyChangesTabMsg);
          this.notificationService.showWaitIndicator(false);
        }
        );
    } else {
      this.isEnterPackageDisabled = true;
      this.filterProjectInputRows = <SelectItem[]>[];
      this.filterPackageInputRows = <SelectItem[]>[];
      this.isResetDisabled = true;
    }
    this.evaluateStatusOfGo();
  }

  private buildApplyChangesDetails(applyChangesTabMsg: ApplyFilterData) {
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    if (applyChangesTabMsg != null) {
      this.isGoDisabled = false;
      this.isResetDisabled = false;
    } else {
      this.isGoDisabled = true;
      this.isResetDisabled = true;
    }
    if (applyChangesTabMsg.packages !== null) {
      for (const packagesTab of applyChangesTabMsg.packages) {
        this.filterPackageInputRows.push(packagesTab);
      }
    }
    if (applyChangesTabMsg.projects != null) {
      for (const project of applyChangesTabMsg.projects) {
        this.filterProjectInputRows.push({ label: project, value: project });
      }
    }
  }

  private evaluateStatusOfGo() {
    if (this.filterEnv && this.filterEnv.trim() !== '') {
      this.isGoDisabled = false;
      this.isEnterPackageDisabled = false;
    } else {
      this.isGoDisabled = true;
    }
  }

  onResetClick() {
    this.enterPackageName = null;
    this.filterEnv = null;
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.isEnterPackageDisabled = true;
    this.isGoDisabled = true;
    this.isResetDisabled = true;
  }
}
