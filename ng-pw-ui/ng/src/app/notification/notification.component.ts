import { Component, OnInit } from '@angular/core';

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

  constructor(private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.notificationService.initialize((message: string, level: number) => {
      this.showMessage(message, level);
    });
  }

  private showMessage(message, level) {
    this.messageText = message;
    this.isInfo = level === NotificationService.INFO;
    this.isWarning = level === NotificationService.WARNING;
    this.isError = level === NotificationService.ERROR;
    this.isMessageRendered = true;
  }
}
