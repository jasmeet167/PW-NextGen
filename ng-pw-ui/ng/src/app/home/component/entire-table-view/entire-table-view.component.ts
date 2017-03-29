import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from 'app/home/service/filter.service';

@Component({
  selector: 'app-entire-table-view',
  templateUrl: './entire-table-view.component.html' ,
  styleUrls: ['./entire-table-view.component.css']

})
export class EntireTableViewComponent implements  OnInit{

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

  constructor(private filterService: FilterService, private notificationService: NotificationService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.filterChangesOptions = [];
    this.filterChangesOptions.push({label: 'Rules with Changes', value: true});
    this.filterChangesOptions.push({label: 'Rules', value: false});
    this.filterChanges = true;

    let envOptions: SelectItem[];
    this.notificationService.showWaitIndicator(true);

     this.filterService.getCommonEnvOptions(this.authToken)
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

          this.filterCompanyDisabled = true;
          this.buildCompanyDropdown(null);
          this.filterRememberSelections = false;
           this.filterTableRows = <SelectItem[]> [];
            this.filterTables = <string[]> [];

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

 onEnvChange() {
    this.filterCompany = null;


    if (this.filterEnv == null) {
      this.filterCompanyDisabled = true;
    } else {
      this.filterCompanyDisabled = false;

      let companyOptions: SelectItem[];
      this.notificationService.showWaitIndicator(true);
      this.filterService.getPlanCompanyOptions(this.authToken, this.filterChanges, this.filterEnv)
          .subscribe(
            res => companyOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
                this.buildCompanyDropdown(null);
                 this.notificationService.showWaitIndicator(false);
            },
            ()  =>
            {
              this.buildCompanyDropdown(companyOptions);
               this.notificationService.showWaitIndicator(false);
            }
          );


    }
  }

onCompanyChange(){
let tableRowsOptions: SelectItem[];
this.notificationService.showWaitIndicator(true);

     this.filterService.getPlanTableOptions(this.authToken, this.filterEnv,this.filterCompany)
          .subscribe(
            res => tableRowsOptions = res,
            err => {
                if (err.status !== 404) {
                  this.notificationService.handleError(err);
                }
               // this.buildTableRows(null);
                this.notificationService.showWaitIndicator(false);
            },
            ()  => {
                this.buildTableRows(tableRowsOptions);
                this.notificationService.showWaitIndicator(false);
            }
          );



}

buildTableRows(tableRowsOptions:SelectItem[])
{   if (tableRowsOptions == null) {
      this.filterTableRows = <SelectItem[]> [];
      this.filterTables = <string[]> [];
    }
    if(tableRowsOptions!=null)
     {

      for (const row of tableRowsOptions) {
        this.filterTableRows.push(row);
      }
      this.filterTables = <string[]> [null];
    }

}
onGoClick()
{
  console.log("Go functionality currently not available ");
}


}
