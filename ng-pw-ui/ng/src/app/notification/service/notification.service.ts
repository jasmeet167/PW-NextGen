import { Injectable } from '@angular/core';
import { Response, ResponseType } from '@angular/http';

import { ErrorMessage } from 'app/home/model/error.message';

@Injectable()
export class NotificationService {
  static readonly INFO = 0;
  static readonly WARNING = 1;
  static readonly ERROR = 2;

  private showWaitIndicatorCallback: (isWaitVisible: boolean) => void;
  private showMessageDialogCallback: (message: string, level: number) => void;

  initWaitControl(showWaitIndicator: (isWaitVisible: boolean) => void) {
    this.showWaitIndicatorCallback = showWaitIndicator;
  }

  initMessageControl(showMessageDialog: (message: string, level: number) => void) {
    this.showMessageDialogCallback = showMessageDialog;
  }

  showWaitIndicator(isVisible: boolean) {
    this.showWaitIndicatorCallback(isVisible);
  }

  showMessage(message: string, level: number) {
    this.showMessageDialogCallback(message, level);
  }

  showInfo(message: string) {
    this.showMessageDialogCallback(message, NotificationService.INFO);
  }

  showWarning(message: string) {
    this.showMessageDialogCallback(message, NotificationService.WARNING);
  }

  showError(message: string) {
    this.showMessageDialogCallback(message, NotificationService.ERROR);
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
