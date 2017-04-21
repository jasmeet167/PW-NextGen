import { Component } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from 'app/util/service/filter.service';
import { PromoteFilterData } from 'app/util/model/promote-filter-data';

@Component({
  templateUrl: './promote.component.html',
  styleUrls: ['./promote.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PromoteComponent implements OnInit {
  public enterPackage: string;

  public filterSourceEnvOptions: SelectItem[];
  public filterSourceEnv: string;
  public filterTargetEnvOptions: SelectItem[];
  public filterTargetEnv: string;

  public filterPackageInputRows: SelectItem[];
  public filterPackageOutputRows: SelectItem[];
  public filterProjectInputRows: SelectItem[];
  public filterProjectOutputRows: SelectItem[];

  public isTargetEnvDisabled: boolean;
  public isEnterPackageDisabled: boolean;

  public isGoDisabled: boolean;
  public isResetDisabled: boolean;

  private authToken: string;

  constructor(private notificationService: NotificationService, private filterService: FilterService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    let envOptions: SelectItem[];

    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];

    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];

    this.isTargetEnvDisabled = true;
    this.isEnterPackageDisabled = true;

     this.isResetDisabled = true;

    this.notificationService.showWaitIndicator(true);
    this.filterService.getEnvOptions(this.authToken)
      .subscribe(
      res => envOptions = res,
      err => {
        if (err.status !== 404) {
          this.notificationService.handleError(err);
        }
        this.buildSourceEnvDropdown(null);
        this.notificationService.showWaitIndicator(false);
      },
      () => {
        this.buildSourceEnvDropdown(envOptions);
        this.notificationService.showWaitIndicator(false);
      }
      );
    this.evaluateStatusOfGo();
  }

  private buildSourceEnvDropdown(options: SelectItem[]) {
    this.filterSourceEnvOptions = <SelectItem[]>[{ label: '', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.filterSourceEnvOptions.push(option);
      }
    }
  }

  private buildTargetEnvDropdown(options: SelectItem[]) {
    this.filterTargetEnvOptions = <SelectItem[]>[{ label: '', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.filterTargetEnvOptions.push(option);
      }
      this.filterTargetEnv = null;
    }
  }

  onSourceEnvChange() {
    this.filterTargetEnv = null;
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.isEnterPackageDisabled = true;
    if (this.filterSourceEnv == null) {
      this.isTargetEnvDisabled = true;
      this.isResetDisabled = true;
    } else {
      this.isTargetEnvDisabled = false;
      this.isResetDisabled = false;
      let envOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getEnvOptions(this.authToken)
        .subscribe(
        res => envOptions = res,
        err => {
          if (err.status !== 404) {
            this.notificationService.handleError(err);
          }
          this.buildTargetEnvDropdown(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildTargetEnvDropdown(envOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
  }

  onTargetEnvChange() {
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    let promoteTabMsg: PromoteFilterData;
    promoteTabMsg = null;

    if (this.filterSourceEnv != null && this.filterTargetEnv != null) {
      if (this.filterSourceEnv === this.filterTargetEnv) {
        this.notificationService.showError('Source and Target Environments cannot be same');
        this.onResetClick();
        return null;
      }
    }

    if (this.filterTargetEnv != null) {
      this.notificationService.showWaitIndicator(true);
      this.isEnterPackageDisabled = false;
      this.filterService.getPromoteDetails(this.authToken, this.filterSourceEnv, this.filterTargetEnv)
        .subscribe(
        res => promoteTabMsg = res,
        err => {
          if (err.status !== 404) {
            this.notificationService.handleError(err);
          }
          this.buildPromotePageDetails(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildPromotePageDetails(promoteTabMsg);
          this.notificationService.showWaitIndicator(false);
        }
        );
    } else {
      this.filterProjectInputRows = <SelectItem[]>[];
      this.filterPackageInputRows = <SelectItem[]>[];
      this.isEnterPackageDisabled = true;
    }
  }

  private buildPromotePageDetails(promoteTabMsg: PromoteFilterData) {
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    if (promoteTabMsg.packages !== null) {
      for (const packagesTab of promoteTabMsg.packages) {
        this.filterPackageInputRows.push(packagesTab);
      }
    }
    if (promoteTabMsg.projects != null) {
      for (const project of promoteTabMsg.projects) {
        this.filterProjectInputRows.push({ label: project, value: project });
      }
    }
  }

  private evaluateStatusOfGo() {
    if ((this.filterSourceEnv && this.filterSourceEnv.trim() !== '')
      && (this.filterTargetEnv && this.filterTargetEnv.trim() !== '')
    ) {
      this.isGoDisabled = false;
    } else {
      this.isGoDisabled = true;
      this.isEnterPackageDisabled = true;
    }
  }

  onResetClick() {
    this.enterPackage = null;
    this.filterSourceEnv = null;
    this.filterTargetEnvOptions = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterPackageInputRows = <SelectItem[]>[];
    this.filterPackageOutputRows = <SelectItem[]>[];
    this.isTargetEnvDisabled = true;
    this.isEnterPackageDisabled = true;
    this.isResetDisabled = true;
  }
}
