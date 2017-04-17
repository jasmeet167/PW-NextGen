export interface ErrorModel {
  severity: ErrorModel.SeverityEnum;
  errorCode: string;
  message: string;
}

export namespace ErrorModel {
  export enum SeverityEnum {
    ERROR = <any> 'ERROR',
    WARNING = <any> 'WARNING'
  }
}
