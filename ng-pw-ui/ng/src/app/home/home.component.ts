import { Component, OnInit } from '@angular/core';
import { Response, ResponseType } from '@angular/http';

import { MenuItem } from 'primeng/primeng';

import { AboutService } from './service/about.service';
import { MenuService } from './service/menu.service';

import { AboutApplication } from './model/about.application';
import { ErrorMessage } from './model/error.message';
import { MenuHelper } from './menu.helper';

@Component({
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  public isMsgDisplayed: boolean;
  public msgText: string;

  public isInfoAboutDisplayed: boolean;
  public userName: string;
  public buildDate: Date;
  public infoAbout: AboutApplication = new AboutApplication();

  public menuModel: MenuItem[];

  private authToken: string;

  constructor(private aboutService: AboutService, private menuService: MenuService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    this.menuService.getMenu()
        .subscribe(
          res => this.menuModel = res,
          err => this.handleError(err),
          ()  => this.buildMenuCallbacks()
        );
  }

  private buildMenuCallbacks() {
    if (this.menuModel) {
      const theAboutCallback = (event: any) => {
        this.onAboutClick();
      };
      new MenuHelper().injectCallback(this.menuModel, 'About', theAboutCallback);
    }
  }

  onAboutClick() {
    this.userName = (<string> sessionStorage['userName']).toUpperCase();
    this.aboutService.getAboutApplication(this.authToken)
        .subscribe(
          res => this.infoAbout = res,
          err => this.handleError(err),
          ()  => {
                  this.buildDate = new Date(Date.parse(this.infoAbout.buildTimestamp));
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
