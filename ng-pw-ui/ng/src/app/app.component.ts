import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { NotificationService } from 'app/notification/service/notification.service';

@Component({
  selector: 'app-pw',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  public isWaitBoxVisible = true;

  constructor(private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.notificationService.initWaitControl((isWaitVisible: boolean) => {
      this.isWaitBoxVisible = isWaitVisible;
    });
  }
}
