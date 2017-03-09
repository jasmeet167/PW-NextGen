import { Component, OnInit } from '@angular/core';

import { MenuItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { AboutService } from './service/about.service';
import { MenuService } from './service/menu.service';

import { AboutApplication } from './model/about.application';
import { MenuHelper } from './menu.helper';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  public isInfoAboutDisplayed: boolean;
  public userName: string;
  public buildDate: Date;
  public infoAbout: AboutApplication = new AboutApplication();

  public menuModel: MenuItem[];

  private authToken: string;

  constructor(private notificationService: NotificationService, private aboutService: AboutService,
              private menuService: MenuService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    this.menuService.getMenu()
        .subscribe(
          res => this.menuModel = res,
          err => this.notificationService.handleError(err),
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
          err => this.notificationService.handleError(err),
          ()  => {
                  this.buildDate = new Date(Date.parse(this.infoAbout.buildTimestamp));
                  this.isInfoAboutDisplayed = true;
          }
        );
  }
}
