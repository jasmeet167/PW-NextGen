import { Component, OnInit } from '@angular/core';
import { Response, ResponseType } from '@angular/http';
import { DateFormatter } from '@angular/common/src/pipes/intl';
import { Message } from 'primeng/primeng';
import { MenuItem } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';

import { AboutService } from './service/about.service';
import { AboutApplication } from './model/about.application';
import { MenuService } from './service/menu.service';
import { FilterService } from './service/filter.service';
import { ErrorMessage } from './model/error.message';

import { MenuHelper } from './menu.helper';

@Component({
  selector: 'app-home-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public isMsgDisplayed: boolean;
  public msgText: string;

  public isInfoAboutDisplayed: boolean;
  public userName: string;
  public formattedBuildDate: string;
  public infoAbout: AboutApplication = new AboutApplication();

  public menuModel: MenuItem[];

  public filterChangesOptions: SelectItem[];
  public filterChanges: boolean;

  public filterEnvOptions: SelectItem[];
  public filterEnv: string;

  public filterCompanyOptions: SelectItem[];
  public filterCompany: string;
  public filterCompanyDisabled: boolean;

  public filterProductOptions: SelectItem[];
  public filterProduct: string;
  public filterProductDisabled: boolean;

  public filterPlanCodeOptions: SelectItem[];
  public filterPlanCode: string;
  public filterPlanCodeDisabled: boolean;

  public filterIssueStatOptions: SelectItem[];
  public filterIssueState: string;
  public filterIssueStateDisabled: boolean;

  public filterLobOptions: SelectItem[];
  public filterLob: string;
  public filterLobDisabled: boolean;

  public filterPlanEffDateOptions: SelectItem[];
  public filterPlanEffDate: string;
  public filterPlanEffDateDisabled: boolean;

  public filterProjectRows: SelectItem[];
  public filterProjects: string[];

  public filterInclOrphans: boolean;
  public filterInclOrphansDisabled: boolean;

  public filterRememberSelections: boolean;

  private authToken: string;

  constructor(private aboutService: AboutService, private menuService: MenuService,
              private filterService: FilterService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    this.menuService.getMenu()
        .subscribe(
          res => this.menuModel = res,
          err => this.handleError(err),
          ()  => this.buildMenuCallbacks()
        );

    this.filterChangesOptions = [];
    this.filterChangesOptions.push({label: 'Rules with Changes', value: true});
    this.filterChangesOptions.push({label: 'Rules', value: false});
    this.filterChanges = true;

    let envOptions: SelectItem[];
    this.filterService.getEnvOptions(this.authToken)
        .subscribe(
          res => envOptions = res,
          err => {
            if (err.status !== 404) {
              this.handleError(err);
            }
            this.buildEnvDropdown(null);
          },
          ()  => this.buildEnvDropdown(envOptions)
        );

    this.filterCompanyDisabled = true;
    this.buildCompanyDropdown(null);

    this.filterProductDisabled = true;
    this.buildProductDropdown(null);

    this.filterPlanCodeDisabled = true;
    this.buildPlanCodeDropdown(null);

    this.filterIssueStateDisabled = true;
    this.buildIssueStateDropdown(null);

    this.filterLobDisabled = true;
    this.buildLobDropdown(null);

    this.filterPlanEffDateDisabled = true;
    this.buildEffDateDropdown(null);

    this.filterProjectRows = <SelectItem[]> [];

    this.filterInclOrphans = false;
    this.filterInclOrphansDisabled = true;

    this.filterRememberSelections = false;
  }

  private buildMenuCallbacks() {
    if (this.menuModel) {
      const theAboutCallback = (event: any) => {
        this.onAboutClick();
      };
      new MenuHelper().injectCallback(this.menuModel, 'About', theAboutCallback);
    }
  }

  onEnvChange() {
    this.filterCompany = null;
    this.filterProduct = null;
    this.filterPlanCode = null;
    this.filterIssueState = null;
    this.filterLob = null;
    this.filterPlanEffDate = null;
    this.filterProjects = <string[]> [];

    this.filterProductDisabled = true;
    this.filterPlanCodeDisabled = true;
    this.filterIssueStateDisabled = true;
    this.filterLobDisabled = true;
    this.filterPlanEffDateDisabled = true;

    if (this.filterEnv == null) {
      this.filterCompanyDisabled = true;
    } else {
      this.filterCompanyDisabled = false;

      let companyOptions: SelectItem[];
      this.filterService.getCompanyOptions(this.authToken, this.filterChanges, this.filterEnv)
          .subscribe(
            res => companyOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildCompanyDropdown(null);
            },
            ()  => this.buildCompanyDropdown(companyOptions)
          );

      let projectOptions: SelectItem[];
      this.filterService.getProjects(this.authToken, this.filterEnv)
          .subscribe(
            res => projectOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildProjects(null);
            },
            ()  => this.buildProjects(projectOptions)
          );
    }
  }

  onCompanyChange() {
    this.filterProduct = null;
    this.filterPlanCode = null;
    this.filterIssueState = null;
    this.filterLob = null;
    this.filterPlanEffDate = null;

    this.filterPlanCodeDisabled = true;
    this.filterIssueStateDisabled = true;
    this.filterLobDisabled = true;
    this.filterPlanEffDateDisabled = true;

    if (this.filterCompany == null) {
      this.filterProductDisabled = true;
    } else {
      this.filterProductDisabled = false;

      let productOptions: SelectItem[];
      this.filterService.getProductOptions(this.authToken, this.filterChanges, this.filterEnv,
                                           this.filterCompany)
          .subscribe(
            res => productOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildProductDropdown(null);
            },
            ()  => this.buildProductDropdown(productOptions)
          );
    }
  }

  onProductChange() {
    this.filterPlanCode = null;
    this.filterIssueState = null;
    this.filterLob = null;
    this.filterPlanEffDate = null;

    this.filterIssueStateDisabled = true;
    this.filterLobDisabled = true;
    this.filterPlanEffDateDisabled = true;

    if (this.filterProduct == null) {
      this.filterPlanCodeDisabled = true;
    } else {
      this.filterPlanCodeDisabled = false;

      let planCodeOptions: SelectItem[];
      this.filterService.getPlanCodeOptions(this.authToken, this.filterChanges, this.filterEnv,
                                            this.filterCompany, this.filterProduct)
          .subscribe(
            res => planCodeOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildPlanCodeDropdown(null);
            },
            ()  => this.buildPlanCodeDropdown(planCodeOptions)
          );
    }
  }

  onPlanCodeChange() {
    this.filterIssueState = null;
    this.filterLob = null;
    this.filterPlanEffDate = null;

    this.filterLobDisabled = true;
    this.filterPlanEffDateDisabled = true;

    if (this.filterPlanCode == null) {
      this.filterIssueStateDisabled = true;
    } else {
      this.filterIssueStateDisabled = false;

      let issueStateOptions: SelectItem[];
      this.filterService.getIssueStateOptions(this.authToken, this.filterChanges, this.filterEnv,
                                              this.filterCompany, this.filterProduct, this.filterPlanCode)
          .subscribe(
            res => issueStateOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildIssueStateDropdown(null);
            },
            ()  => this.buildIssueStateDropdown(issueStateOptions)
          );
    }
  }

  onIssueStateChange() {
    this.filterLob = null;
    this.filterPlanEffDate = null;

    this.filterPlanEffDateDisabled = true;

    if (this.filterIssueState == null) {
      this.filterLobDisabled = true;
    } else {
      this.filterLobDisabled = false;

      let lobOptions: SelectItem[];
      this.filterService.getLobOptions(this.authToken, this.filterChanges, this.filterEnv,
                                       this.filterCompany, this.filterProduct, this.filterPlanCode,
                                       this.filterIssueState)
          .subscribe(
            res => lobOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildLobDropdown(null);
            },
            ()  => this.buildLobDropdown(lobOptions)
          );
    }
  }

  onLobChange() {
    this.filterPlanEffDate = null;

    if (this.filterLob == null) {
      this.filterPlanEffDateDisabled = true;
    } else {
      this.filterPlanEffDateDisabled = false;

      let effDateOptions: SelectItem[];
      this.filterService.getEffDateOptions(this.authToken, this.filterChanges, this.filterEnv,
                                           this.filterCompany, this.filterProduct, this.filterPlanCode,
                                           this.filterIssueState, this.filterLob)
          .subscribe(
            res => effDateOptions = res,
            err => {
                if (err.status !== 404) {
                  this.handleError(err);
                }
                this.buildEffDateDropdown(null);
            },
            ()  => this.buildEffDateDropdown(effDateOptions)
          );
    }
  }

  private buildEnvDropdown (options: SelectItem[]) {
    this.filterEnvOptions = <SelectItem[]> [{label: 'Environment', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterEnvOptions.push(option);
      }
    }
  }

  private buildCompanyDropdown(options: SelectItem[]) {
    this.filterCompanyOptions = <SelectItem[]> [{label: 'Company', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterCompanyOptions.push(option);
      }
    }
  }

  private buildProductDropdown(options: SelectItem[]) {
    this.filterProductOptions = <SelectItem[]> [{label: 'Product', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterProductOptions.push(option);
      }
    }
  }

  private buildPlanCodeDropdown(options: SelectItem[]) {
    this.filterPlanCodeOptions = <SelectItem[]> [{label: 'Plan Code', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterPlanCodeOptions.push(option);
      }
    }
  }

  private buildIssueStateDropdown(options: SelectItem[]) {
    this.filterIssueStatOptions = <SelectItem[]> [{label: 'Issue State', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterIssueStatOptions.push(option);
      }
    }
  }

  private buildLobDropdown(options: SelectItem[]) {
    this.filterLobOptions = <SelectItem[]> [{label: 'Line of Business', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterLobOptions.push(option);
      }
    }
  }

  private buildEffDateDropdown(options: SelectItem[]) {
    this.filterPlanEffDateOptions = <SelectItem[]> [{label: 'Plan Effective Date', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterPlanEffDateOptions.push(option);
      }
    }
  }

  private buildProjects(rows: SelectItem[]) {
    if (rows == null) {
      this.filterProjectRows = <SelectItem[]> [];
      this.filterProjects = <string[]> [];
    } else {
      this.filterProjectRows = <SelectItem[]> [{ label: 'All Projects', value: null }];
      for (const row of rows) {
        this.filterProjectRows.push(row);
      }
      this.filterProjects = <string[]> [null];
    }
  }

  onGoClick() {
    console.log('Click on the "Go" button has been detected');
  }

  onAboutClick() {
    this.userName = (<string> sessionStorage['userName']).toUpperCase();
    this.aboutService.getAboutApplication(this.authToken)
        .subscribe(
          res => this.infoAbout = res,
          err => this.handleError(err),
          ()  => {
                  const date: Date = new Date(Date.parse(this.infoAbout.buildTimestamp));
                  this.formattedBuildDate = DateFormatter.format(date, 'en', 'MMMM dd yyyy @ H:mm:ss');
                  this.isInfoAboutDisplayed = true;
          }
        );
  }

  private handleError(err: Response) {
    if (err.type !== ResponseType.Default) {
      this.showError('Network error - unable to access Application Services');
      return;
    }

    let model: ErrorMessage.ErrorModel;
    try {
      model = <ErrorMessage.ErrorModel> err.json();
    } catch (e) {
      model = null;
    }

    if (model == null) {
      const logMessage: string = 'HTTP error ' + err.status + ': ' + err.statusText;
      console.error(logMessage);
      this.showError(err.statusText);
    } else {
      const logMessage: string = 'Error ' + model.errorCode + ': ' + model.message;
      console.error(logMessage);
      this.showError(model.message);
    }
  }

  private showError(message) {
    this.msgText = message;
    this.isMsgDisplayed = true;
  }
}
