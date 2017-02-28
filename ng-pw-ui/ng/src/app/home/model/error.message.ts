export namespace ErrorMessage {
  export class ErrorModel {
    severity: SeverityEnum;
    errorCode: string;
    message: string;
  }

  export enum SeverityEnum {
    ERROR = <any> 'ERROR',
    WARNING = <any> 'WARNING'
  }
}
