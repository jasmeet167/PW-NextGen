import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { SelectItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { FilterService } from 'app/home/service/filter.service';

import { ChangeOnlyTabMsg } from './model/changeonly.message';

@Component({
  selector: 'app-changes-only',
  templateUrl: './changes-only.component.html',
  styleUrls: ['./changes-only.component.css']
})
export class ChangesOnlyComponent implements OnInit {

  private authToken: string;
  public filterEnvOptions: SelectItem[];
  public filterProjectInputRows: SelectItem[];
  public filterBussRulesInputRows: SelectItem[];
  public filterUserIdInputRows:SelectItem[];



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
    this.filterEnv = "";
    this.filterEnvOptions = <SelectItem[]>[];
    this.filterProjectInputRows =  <SelectItem[]>[];

    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBussRulesOutputRows = <SelectItem[]>[];
    this.filterUserIdOutputRows = <SelectItem[]>[];
    this.isGoDisabled=true;
    this.isResetDisabled=true;

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
          ()  => {
              this.buildEnvDropdown(envOptions);
              this.notificationService.showWaitIndicator(false);
          }
        );

  }

  private buildEnvDropdown (options: SelectItem[]) {
    this.filterEnvOptions = <SelectItem[]> [{label: 'Environment', value: null}];
    if (options !== null) {
      for (const option of options) {
        this.filterEnvOptions.push(option);
      }
    }
  }


 onEnvChange()
 {
   let changeonlyTabmsg: ChangeOnlyTabMsg;
   changeonlyTabmsg=null;

   if(this.filterEnv!=null)
   {
     this.notificationService.showWaitIndicator(true);
  this.filterService.getChangesWipDetails(this.authToken,  this.filterEnv)
       .subscribe(
          res => changeonlyTabmsg = res,
          err => {
              if (err.status !== 404) {
                this.notificationService.handleError(err);
              }
              this.buildChangeWIPDetails(null);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.buildChangeWIPDetails(changeonlyTabmsg);
              this.notificationService.showWaitIndicator(false);
          }
        );
   }
   else{
      this.filterBussRulesInputRows=<SelectItem[]> [];
      this.filterProjectInputRows=<SelectItem[]> [];
      this.filterUserIdInputRows=<SelectItem[]> [];
       this.isGoDisabled=true;
      this.isResetDisabled=true;
   }

}

  private buildChangeWIPDetails (changeonlyTabmsg: ChangeOnlyTabMsg) {
    //this.filterEnvOptions = <SelectItem[]> [{label: 'Environment', value: null}];
    this.filterBussRulesInputRows=<SelectItem[]> [];
    this.filterProjectInputRows=<SelectItem[]> [];
    this.filterUserIdInputRows=<SelectItem[]> [];
    if(changeonlyTabmsg!=null)
    {
      this.isGoDisabled=false;
      this.isResetDisabled=false;
    }
    else{
      this.isGoDisabled=true;
      this.isResetDisabled=true;
    }
    if (changeonlyTabmsg.businessRuleTables !== null) {
      for(const bussRuleTab of changeonlyTabmsg.businessRuleTables)
      {
        this.filterBussRulesInputRows.push(bussRuleTab);

      }
    }
  if(changeonlyTabmsg.projects!=null)
  {
    for(const project of changeonlyTabmsg.projects)
    {
      console.log(project);
      this.filterProjectInputRows.push({label:project , value: project});
    }
  }
if(changeonlyTabmsg.users!=null)
{
  for(const user of changeonlyTabmsg.users)
  {
    console.log(user);
    this.filterUserIdInputRows.push({label:user , value: user});
    //this.pickListInputRows.push({label:user , value: user});
  }
}


  this.filterBussRulesInputRows.sort(function(a,b) {return (a.label > b.label) ? 1 : ((b.label > a.label) ? -1 : 0);} );
  this.filterProjectInputRows.sort(function(a,b) {return (a.label > b.label) ? 1 : ((b.label > a.label) ? -1 : 0);} );
  this.filterUserIdInputRows.sort(function(a,b) {return (a.label > b.label) ? 1 : ((b.label > a.label) ? -1 : 0);} );
  }








onGoClick()
{
  console.log("On click ");
  for(const option of this.filterProjectOutputRows)
  {
    console.log("value are"+option.value +" label are"+option.label);
  }

  for(const option of this.filterBussRulesOutputRows)
  {
    console.log("value are "+option.value +" label are"+option.label);
  }

}

onResetClick()
{
  console.log("on onResetClick ");

    this.filterProjectInputRows =  <SelectItem[]>[];
    this.filterBussRulesInputRows = <SelectItem[]>[];
    this.filterProjectOutputRows = <SelectItem[]>[];
    this.filterBussRulesOutputRows = <SelectItem[]>[];
    this.filterUserIdOutputRows = <SelectItem[]>[];

    this.filterUserIdInputRows= <SelectItem[]>[];
    let envOptions: SelectItem[];


}

}
