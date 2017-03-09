export namespace ErrorMessage {
  export class ErrorModel {
    severity: SeverityEnum;
    errorCode: string;
    message: string;
  }

  export enum SeverityEnum {
    INFO = <any> 'INFO',
    WARNING = <any> 'WARNING',
    ERROR = <any> 'ERROR'
  }
}
