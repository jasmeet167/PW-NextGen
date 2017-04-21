import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from 'app/util/service/filter.service';

@Component({
  templateUrl: './continuity-check.component.html',
  styleUrls: ['./continuity-check.component.css']
})
export class ContinuityCheckComponent implements OnInit {
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

  public asOfDateOptions: SelectItem[];
  public asOfDate: string;
  public isAsOfDateDisabled: boolean;

  public rememberSelections: boolean;
  public isNextDisabled: boolean;

  public surrenders: boolean;

  public filterAspect: string;
  private authToken: string;

  public isDivRendered: boolean;
  constructor(private notificationService: NotificationService, private filterService: FilterService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    this.filterAspect = 'C';

    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

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
      () => {
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

    this.rememberSelections = true;

    this.evaluateStatusOfNext();
  }

  onEnvIdChange() {
    this.companyCode = null;
    this.productCode = null;
    this.planCode = null;
    this.issueState = null;
    this.lob = null;
    this.effDate = null;

    this.isProductCodeDisabled = true;
    this.isPlanCodeDisabled = true;
    this.isIssueStateDisabled = true;
    this.isLobDisabled = true;
    this.isEffDateDisabled = true;

    if (this.envId == null) {
      this.isCompanyCodeDisabled = true;
    } else {
      this.isCompanyCodeDisabled = false;

      let companyOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getCompanyForSummary(this.authToken, this.filterAspect, this.envId)
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
    this.evaluateStatusOfNext();
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

    if (this.companyCode == null) {
      this.isProductCodeDisabled = true;
    } else {
      this.isProductCodeDisabled = false;

      let productOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getProductOptionsForSummary(this.authToken, this.filterAspect, this.envId,
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
        () => {
          this.buildProductDropdown(productOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfNext();
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

    } else {
      this.isPlanCodeDisabled = false;

      let planCodeOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getPlanCodeOptionsForSummary(this.authToken, this.filterAspect, this.envId,
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
        () => {
          this.buildPlanCodeDropdown(planCodeOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfNext();
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
      this.filterService.getIssueStateOptionsForSummary(this.authToken, this.filterAspect, this.envId,
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
        () => {
          this.buildIssueStateDropdown(issueStateOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfNext();
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
      this.filterService.getLobOptionsForSummary(this.authToken, this.filterAspect, this.envId,
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
        () => {
          this.buildLobDropdown(lobOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfNext();
  }

  onLobChange() {
    this.effDate = null;

    if (this.lob == null) {
      this.isEffDateDisabled = true;
    } else {
      this.isEffDateDisabled = false;

      let effDateOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getEffDateOptionsForSummary(this.authToken, this.filterAspect, this.envId,
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
        () => {
          this.buildEffDateDropdown(effDateOptions);
          this.notificationService.showWaitIndicator(false);
        }
        );
    }
    this.evaluateStatusOfNext();
  }

  onEffDateChange() {
    this.evaluateStatusOfNext();
  }

  private buildEnvDropdown(options: SelectItem[]) {
    this.envIdOptions = <SelectItem[]>[{ label: 'Environment', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.envIdOptions.push(option);
      }
    }
  }

  private buildCompanyDropdown(options: SelectItem[]) {
    this.companyCodeOptions = <SelectItem[]>[{ label: 'Company', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.companyCodeOptions.push(option);
      }
    }
  }

  private buildProductDropdown(options: SelectItem[]) {
    this.productCodeOptions = <SelectItem[]>[{ label: 'Product', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.productCodeOptions.push(option);
      }
    }
  }

  private buildPlanCodeDropdown(options: SelectItem[]) {
    this.planCodeOptions = <SelectItem[]>[{ label: 'Plan Code', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.planCodeOptions.push(option);
      }
    }
  }

  private buildIssueStateDropdown(options: SelectItem[]) {
    this.issueStateOptions = <SelectItem[]>[{ label: 'Issue State', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.issueStateOptions.push(option);
      }
    }
  }

  private buildLobDropdown(options: SelectItem[]) {
    this.lobOptions = <SelectItem[]>[{ label: 'Line of Business', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.lobOptions.push(option);
      }
    }
  }

  private buildEffDateDropdown(options: SelectItem[]) {
    this.effDateOptions = <SelectItem[]>[{ label: 'Plan Effective Date', value: null }];
    if (options !== null) {
      for (const option of options) {
        this.effDateOptions.push(option);
      }
    }
  }

  private evaluateStatusOfNext() {
    if ((this.effDate && this.effDate.trim() !== '')) {
      this.isNextDisabled = false;
      this.isDivRendered = true;
    } else {
      this.isNextDisabled = true;
      this.isDivRendered = false;
    }
  }
}
