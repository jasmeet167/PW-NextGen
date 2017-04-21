import { Component } from '@angular/core';

import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavigationEnd } from '@angular/router';

import { Subscription } from 'rxjs/Subscription';

import { MenuItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { MenuService } from 'app/util/service/menu.service';
import { AboutService } from 'app/home-decorator/service/about.service';
import { MenuHelper } from 'app/util/menu.helper';

import { AboutApplication } from './model/about-application';

@Component({
  selector: 'app-home-decorator',
  templateUrl: './home-decorator.component.html'
})
export class HomeDecoratorComponent implements OnInit {
  public isInfoAboutDisplayed: boolean;
  public userName: string;
  public buildDate: Date;
  public infoAbout: AboutApplication = new AboutApplication();

  public menuModel: MenuItem[];
  public tabsModel: MenuItem[];
  public activeTab: MenuItem;

  public isHomeView: boolean;

  private authToken: string;

  constructor(private router: Router, private notificationService: NotificationService,
              private aboutService: AboutService, private menuService: MenuService) {
    this.isHomeView = false;
  }

  ngOnInit() {
    this.notificationService.showWaitIndicator(true);
    this.menuService.getMenu('assets/data/home-decorator/home-decorator-menu.json')
        .subscribe(
          res => this.menuModel = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.buildMenuCallbacks();
              this.notificationService.showWaitIndicator(false);
          }
        );

    this.tabsModel = [
      { label: 'Business Rule Search', routerLink: ['/business-rules'] },
      { label: 'Entire Table', routerLink: ['/entire-table-view'] },
      { label: 'Changes Only', routerLink: ['/changes-only'] },
      { label: 'Apply Changes', routerLink: ['/apply-changes'] },
      { label: 'Audit/Error', routerLink: ['/audit-error'] },
      { label: 'Promote', routerLink: ['/promote'] },
      { label: 'Summary', routerLink: ['/summary'] },
      { label: 'Continuity Check', routerLink: ['/continuity-check'] }
    ];

    const subscription: Subscription = this.router.events.subscribe((target: NavigationEnd) => {
      if (target instanceof NavigationEnd) {
        this.authToken = sessionStorage['authToken'];
        let isTabFound = false;
        for (const item of this.tabsModel) {
          if (item.routerLink[0] === target.url) {
            this.activeTab = item;
            isTabFound = true;
            break;
          }
        }
        this.isHomeView = isTabFound;
      }
    });
  }

  private buildMenuCallbacks() {
    const theAboutCallback = (event: any) => {
      this.onAboutClick();
    };
    new MenuHelper().injectCallback(this.menuModel, 'About', theAboutCallback);
  }

  onAboutClick() {
    this.userName = (<string> sessionStorage['userName']).toUpperCase();

    this.notificationService.showWaitIndicator(true);
    this.aboutService.getAboutApplication(this.authToken)
        .subscribe(
          res => this.infoAbout = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.buildDate = new Date(Date.parse(this.infoAbout.buildTimestamp));
              this.isInfoAboutDisplayed = true;
              this.notificationService.showWaitIndicator(false);
          }
        );
  }
}
