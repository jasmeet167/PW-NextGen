import { TreeNodePlanKey } from './tree-node-plan-key';
import { TreeNodeAttributes } from './tree-node-attributes';

export class TreeNodeData {
  lazyNode: boolean;
  lazyType: string;
  name: string;
  tableId: string;
  projectName: string;
  packageId: string;
  planKey: TreeNodePlanKey;
  attributes: TreeNodeAttributes;
}
