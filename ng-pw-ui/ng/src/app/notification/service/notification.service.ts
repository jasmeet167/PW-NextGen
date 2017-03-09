import { Injectable } from '@angular/core';
import { Response, ResponseType } from '@angular/http';

import { ErrorMessage } from 'app/home/model/error.message';

@Injectable()
export class NotificationService {
  static readonly INFO = 0;
  static readonly WARNING = 1;
  static readonly ERROR = 2;

  private messageCallback: (message: string, level: number) => void;

  initialize(messageCallback: (message: string, level: number) => void) {
    this.messageCallback = messageCallback;
  }

  showMessage(message: string, level: number) {
    this.messageCallback(message, level);
  }

  showInfo(message: string) {
    this.messageCallback(message, NotificationService.INFO);
  }

  showWarning(message: string) {
    this.messageCallback(message, NotificationService.WARNING);
  }

  showError(message: string) {
    this.messageCallback(message, NotificationService.ERROR);
  }

  showGenericCommError() {
    this.showError('Network error - unable to access Application Services');
  }

  handleError(err: Response) {
    if (err.type !== ResponseType.Default) {
      this.showGenericCommError();
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
      const message: string = model.message;

      if (model.severity === ErrorMessage.SeverityEnum.INFO) {
        console.log(message);
        this.showInfo(message);
      } else if (model.severity === ErrorMessage.SeverityEnum.WARNING) {
        console.warn('Warning ' + model.errorCode + ': ' + message);
        this.showWarning(message);
      } else {
        console.error('Error ' + model.errorCode + ': ' + message);
        this.showError(message);
      }
    }
  }
}
