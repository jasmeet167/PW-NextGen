import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { NotificationService } from 'app/notification/service/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html'
})
export class NotificationComponent implements OnInit {
  public isMessageRendered: boolean;
  public messageText: string;

  public isInfo: boolean;
  public isWarning: boolean;
  public isError: boolean;

  constructor(private router: Router, private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.notificationService.initMessageControl((message: string, level: number) => {
      this.showMessage(message, level);
    });

    this.notificationService.initLoginNavigation(() => {
      this.navigateToLogin();
    });
  }

  private showMessage(message, level) {
    this.messageText = message;
    this.isInfo = level === NotificationService.INFO;
    this.isWarning = level === NotificationService.WARNING;
    this.isError = level === NotificationService.ERROR;
    this.isMessageRendered = true;
  }

  private navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
