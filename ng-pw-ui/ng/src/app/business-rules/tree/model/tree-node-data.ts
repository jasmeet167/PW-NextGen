import { TreeNodePlanKey } from './tree-node-plan-key';
import { TreeNodeAttributes } from './tree-node-attributes';

export interface TreeNodeData {
  lazyNode?: boolean;
  lazyType?: TreeNodeData.LazyTypeEnum;
  name?: string;
  tableId?: string;
  tableName?: string;
  projectName?: string;
  packageId?: string;
  planKey?: TreeNodePlanKey;
  attributes?: TreeNodeAttributes;
}

export namespace TreeNodeData {
  export enum LazyTypeEnum {
    C = <any> 'C',
    PDF = <any> 'PDF',
    H = <any> 'H',
    B = <any> 'B',
    R = <any> 'R',
    P = <any> 'P',
    O = <any> 'O'
  }
}
