import { Component, ViewEncapsulation } from '@angular/core';
import { OnInit } from '@angular/core';
import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from 'app/util/service/filter.service';

import { ChangesFilterData } from 'app/util/model/changes-filter-data';

@Component({
  templateUrl: './changes-only.component.html',
  styleUrls: ['./changes-only.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ChangesOnlyComponent implements OnInit {
  private authToken: string;
  public filterEnvOptions: SelectItem[];
  public filterProjectInputRows: SelectItem[];
  public filterBussRulesInputRows: SelectItem[];
  public filterUserIdInputRows: SelectItem[];

  public filterEnv: string;

  public filterProjectOutputRows: SelectItem[];
  public filterBussRulesOutputRows: SelectItem[];
  public filterUserIdOutputRows: SelectItem[];
  public isGoDisabled: boolean;
  public isResetDisabled: boolean;

  constructor(private filterService: FilterService, private notificationService: NotificationService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }
    this.filterEnv = null;
    this.filterEnvOptions = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBussRulesOutputRows = <SelectItem[]>[];
    this.filterUserIdOutputRows = <SelectItem[]>[];
    this.isGoDisabled = true;
    this.isResetDisabled = true;
    let envOptions: SelectItem[];

    this.filterService.getChangesEnvOptions(this.authToken)
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
      for (const option of options) {
        this.filterEnvOptions.push(option);
      }
    }
  }

  onEnvChange() {
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBussRulesInputRows = <SelectItem[]>[];
    this.filterBussRulesOutputRows = <SelectItem[]>[];
    this.filterUserIdInputRows = <SelectItem[]>[];
    this.filterUserIdOutputRows = <SelectItem[]>[];

    let changeOnlyTabmsg: ChangesFilterData;
    changeOnlyTabmsg = null;

    if (this.filterEnv != null) {
      this.notificationService.showWaitIndicator(true);
      this.filterService.getChangesWipDetails(this.authToken, this.filterEnv)
        .subscribe(
        res => changeOnlyTabmsg = res,
        err => {
          if (err.status !== 404) {
            this.notificationService.handleError(err);
          }
          this.buildChangeWipDetails(null);
          this.notificationService.showWaitIndicator(false);
        },
        () => {
          this.buildChangeWipDetails(changeOnlyTabmsg);
          this.notificationService.showWaitIndicator(false);
        }
        );
    } else {
      this.filterBussRulesInputRows = <SelectItem[]>[];
      this.filterProjectInputRows = <SelectItem[]>[];
      this.filterUserIdInputRows = <SelectItem[]>[];
      this.isGoDisabled = true;
      this.isResetDisabled = true;
    }
    this.evaluateStatusOfGo();
  }

  private buildChangeWipDetails(changeOnlyTabmsg: ChangesFilterData) {
    this.filterBussRulesInputRows = <SelectItem[]>[];
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterUserIdInputRows = <SelectItem[]>[];
    if (changeOnlyTabmsg != null) {
      this.isGoDisabled = false;
      this.isResetDisabled = false;
    } else {
      this.isGoDisabled = true;
      this.isResetDisabled = true;
    }
    if (changeOnlyTabmsg.businessRuleTables !== null) {
      for (const bussRuleTab of changeOnlyTabmsg.businessRuleTables) {
        this.filterBussRulesInputRows.push(bussRuleTab);

      }
    }
    if (changeOnlyTabmsg.projects != null) {
      for (const project of changeOnlyTabmsg.projects) {
        this.filterProjectInputRows.push({ label: project, value: project });
      }
    }
    if (changeOnlyTabmsg.users != null) {
      for (const user of changeOnlyTabmsg.users) {
        this.filterUserIdInputRows.push({ label: user, value: user });
      }
    }
  }

  private evaluateStatusOfGo() {
    if (this.filterEnv && this.filterEnv.trim() !== '') {
      this.isGoDisabled = false;
    } else {
      this.isGoDisabled = true;
    }
  }

  onGoClick() {
    console.log('On Go click');
  }

  onResetClick() {
    this.filterEnv = null;
    this.filterProjectInputRows = <SelectItem[]>[];
    this.filterBussRulesInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBussRulesOutputRows = <SelectItem[]>[];
    this.filterUserIdOutputRows = <SelectItem[]>[];
    this.filterUserIdInputRows = <SelectItem[]>[];
    this.isGoDisabled = true;
    this.isResetDisabled = true;
  }
}
