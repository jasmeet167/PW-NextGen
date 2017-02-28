import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { Message } from 'primeng/primeng';
import { MenuItem } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';

import { MenuService } from './service/menu.service';
import { FilterService } from './service/filter.service';
import { ErrorMessage } from './model/error.message';

@Component({
  selector: 'pw-home-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private sessionToken: string;

  private msgs: Message[] = <Message[]> [];
  private menuModel: MenuItem[];

  private filterChangesOptions: SelectItem[];
  private filterChanges: boolean;

  private filterEnvOptions: SelectItem[];
  private filterEnv: string;

  private filterCompanyOptions: SelectItem[];
  private filterCompany: string;
  private filterCompanyDisabled: boolean;

  private filterProductOptions: SelectItem[];
  private filterProduct: string;
  private filterProductDisabled: boolean;

  private filterPlanCodeOptions: SelectItem[];
  private filterPlanCode: string;
  private filterPlanCodeDisabled: boolean;

  private filterIssueStatOptions: SelectItem[];
  private filterIssueState: string;
  private filterIssueStateDisabled: boolean;

  private filterLobOptions: SelectItem[];
  private filterLob: string;
  private filterLobDisabled: boolean;

  private filterPlanEffDateOptions: SelectItem[];
  private filterPlanEffDate: string;
  private filterPlanEffDateDisabled: boolean;

  private filterProjectRows: SelectItem[];
  private filterProjects: string[];

  private filterInclOrphans: boolean;
  private filterInclOrphansDisabled: boolean;

  private filterRememberSelections: boolean;

  constructor(private menuService: MenuService, private filterService: FilterService) {
    this.sessionToken = sessionStorage['sessionToken'];
  }

  ngOnInit() {
    this.menuService.getMenu().then(menuModel => this.menuModel = menuModel);

    this.filterChangesOptions = [];
    this.filterChangesOptions.push({label:'Rules with Changes', value:true});
    this.filterChangesOptions.push({label:'Rules', value:false});
    this.filterChanges = true;

    this.filterService.getEnvOptions(this.sessionToken)
        .subscribe(
            response => this.buildEnvDropdown(response),
            error => {
              if (error.status != 404)
                this.handleError(error);
              this.buildEnvDropdown(null);
            }
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
    }
    else {
      this.filterCompanyDisabled = false;

      this.filterService.getCompanyOptions(this.sessionToken, this.filterChanges, this.filterEnv)
          .subscribe(
            response => this.buildCompanyDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildCompanyDropdown(null);
            }
          );

      this.filterService.getProjects(this.sessionToken, this.filterEnv)
          .subscribe(
            response => this.buildProjects(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildProjects(null);
            }
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
    }
    else {
      this.filterProductDisabled = false;

      this.filterService.getProductOptions(this.sessionToken, this.filterChanges, this.filterEnv,
                                           this.filterCompany)
          .subscribe(
            response => this.buildProductDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildProductDropdown(null);
            }
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
    }
    else {
      this.filterPlanCodeDisabled = false;

      this.filterService.getPlanCodeOptions(this.sessionToken, this.filterChanges, this.filterEnv,
                                            this.filterCompany, this.filterProduct)
          .subscribe(
            response => this.buildPlanCodeDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildPlanCodeDropdown(null);
            }
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
    }
    else {
      this.filterIssueStateDisabled = false;

      this.filterService.getIssueStateOptions(this.sessionToken, this.filterChanges, this.filterEnv,
                                              this.filterCompany, this.filterProduct, this.filterPlanCode)
          .subscribe(
            response => this.buildIssueStateDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildIssueStateDropdown(null);
            }
          );
    }
  }

  onIssueStateChange() {
    this.filterLob = null;
    this.filterPlanEffDate = null;

    this.filterPlanEffDateDisabled = true;

    if (this.filterIssueState == null) {
      this.filterLobDisabled = true;
    }
    else {
      this.filterLobDisabled = false;

      this.filterService.getLobOptions(this.sessionToken, this.filterChanges, this.filterEnv,
                                       this.filterCompany, this.filterProduct, this.filterPlanCode,
                                       this.filterIssueState)
          .subscribe(
            response => this.buildLobDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildLobDropdown(null);
            }
          );
    }
  }

  onLobChange() {
    this.filterPlanEffDate = null;

    if (this.filterLob == null) {
      this.filterPlanEffDateDisabled = true;
    }
    else {
      this.filterPlanEffDateDisabled = false;

      this.filterService.getEffDateOptions(this.sessionToken, this.filterChanges, this.filterEnv,
                                           this.filterCompany, this.filterProduct, this.filterPlanCode,
                                           this.filterIssueState, this.filterLob)
          .subscribe(
            response => this.buildEffDateDropdown(response),
            error => {
                if (error.status != 404)
                  this.handleError(error);

                this.buildEffDateDropdown(null);
            }
          );
    }
  }

  private buildEnvDropdown (options: SelectItem[]) {
    this.filterEnvOptions = <SelectItem[]> [{label:'Environment', value:null}];
    if (options != null)
      for (let option of options)
        this.filterEnvOptions.push(option);
  }

  private buildCompanyDropdown(options: SelectItem[]) {
    this.filterCompanyOptions = <SelectItem[]> [{label:'Company', value:null}];
    if (options != null)
      for (let option of options)
        this.filterCompanyOptions.push(option);
  }

  private buildProductDropdown(options: SelectItem[]) {
    this.filterProductOptions = <SelectItem[]> [{label:'Product', value:null}];
    if (options != null)
      for (let option of options)
        this.filterProductOptions.push(option);
  }

  private buildPlanCodeDropdown(options: SelectItem[]) {
    this.filterPlanCodeOptions = <SelectItem[]> [{label:'Plan Code', value:null}];
    if (options != null)
      for (let option of options)
        this.filterPlanCodeOptions.push(option);
  }

  private buildIssueStateDropdown(options: SelectItem[]) {
    this.filterIssueStatOptions = <SelectItem[]> [{label:'Issue State', value:null}];
    if (options != null)
      for (let option of options)
        this.filterIssueStatOptions.push(option);
  }

  private buildLobDropdown(options: SelectItem[]) {
    this.filterLobOptions = <SelectItem[]> [{label:'Line of Business', value:null}];
    if (options != null)
      for (let option of options)
        this.filterLobOptions.push(option);
  }

  private buildEffDateDropdown(options: SelectItem[]) {
    this.filterPlanEffDateOptions = <SelectItem[]> [{label:'Plan Effective Date', value:null}];
    if (options != null)
      for (let option of options)
        this.filterPlanEffDateOptions.push(option);
  }

  private buildProjects(rows: SelectItem[]) {
    if (rows == null) {
      this.filterProjectRows = <SelectItem[]> [];
      this.filterProjects = <string[]> [];
    }
    else {
      this.filterProjectRows = <SelectItem[]> [{label:'All Projects', value:null}];
      for (let row of rows)
        this.filterProjectRows.push(row);

      this.filterProjects = <string[]> [null];
    }
  }

  onGoClick() {
    console.log('Click on the "Go" button has been detected');
  }


  // Support for display of messages
  handleError(error: Response) {
    let model: ErrorMessage.ErrorModel;
    try {
      model = <ErrorMessage.ErrorModel> error.json();
    }
    catch (e) {
      model = null;
    }

    if (model == null) {
      let logMessage: string = 'HTTP error ' + error.status + ': ' + error.statusText;
      console.error(logMessage);
      this.showError(null, error.statusText);
    }
    else {
      let logMessage: string = 'Error ' + model.errorCode + ': ' + model.message;

      switch (model.severity) {
        case ErrorMessage.SeverityEnum.ERROR: {
          console.error(logMessage);
          this.showError(null, model.message);
          break;
        }
        case ErrorMessage.SeverityEnum.WARNING: {
          console.warn(logMessage);
          this.showWarn(null, model.message);
          break;
        }
      }
    }
  }

  showSuccess(summary, detail) {
    this.showMessage('success', summary, detail);
  }

  showInfo(summary, detail) {
    this.showMessage('info', summary, detail);
  }

  showWarn(summary, detail) {
    this.showMessage('warn', summary, detail);
  }

  showError(summary, detail) {
    this.showMessage('error', summary, detail);
  }

  showMessage (severity, summary, detail) {
    this.msgs = [];
    this.msgs.push({ severity : severity, summary : summary, detail : detail });
  }
}
