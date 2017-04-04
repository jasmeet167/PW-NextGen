import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

import { TreeNode } from 'primeng/primeng';
import { MenuItem } from 'primeng/primeng';
import { Message } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { MenuService } from 'app/util/service/menu.service';
import { MenuHelper } from 'app/util/menu.helper';

import { TreeNodeType } from './model/tree-node-type';
import { TreeNodeData } from './model/tree-node-data';
import { TreeNodePlanKey } from './model/tree-node-plan-key';
import { BusinessRuleTreeService } from './service/business-rule-tree.service';

// The property encapsulation: ViewEncapsulation.None is required to load resources,
// such as those pointed to by the property styleUrls.
@Component({
  selector: 'app-business-rule-tree',
  templateUrl: './business-rule-tree.component.html',
  styleUrls: ['./business-rule-tree.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class BusinessRuleTreeComponent implements OnInit {
  @Input() public viewChanges: boolean;
  @Input() public envId: string;
  @Input() public companyCode: string;
  @Input() public productCode: string;
  @Input() public planCode: string;
  @Input() public issueState: string;
  @Input() public lob: string;
  @Input() public effDate: string;
  @Input() public includeOrphans: boolean;

  private readonly NODE_LABEL_DETAILED = 0;
  private readonly NODE_LABEL_STANDARD = 1;
  private readonly NODE_DETAILED_LABEL = 'Show Table ID and Subset';

  public businessRuleTree: TreeNode[];
  public selectedNode: TreeNode;
  public tooltips: Message[];
  public contextMenuModel: MenuItem[];
  public nodeLabelType = this.NODE_LABEL_STANDARD;

  private authToken: string;

  private generalMenuModel: MenuItem[];
  private companyMenuModel: MenuItem[];
  private planFolderMenuModel: MenuItem[];

  private displayToggleMenuItems: MenuItem[];

  constructor(private notificationService: NotificationService, private menuService: MenuService,
              private businessRuleTreeService: BusinessRuleTreeService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    const menuHelper: MenuHelper = new MenuHelper();
    const theDisplaySwitchCallback = (event: any) => {
      this.toggleNodeDisplayType();
    };

    this.menuService.getMenu('assets/data/business-rule-tree/company-menu.json')
        .subscribe(
          res => this.companyMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => menuHelper.injectCallback(this.companyMenuModel, this.NODE_DETAILED_LABEL, theDisplaySwitchCallback)
        );
    this.menuService.getMenu('assets/data/business-rule-tree/plan-folder-menu.json')
        .subscribe(
          res => this.planFolderMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => menuHelper.injectCallback(this.planFolderMenuModel, this.NODE_DETAILED_LABEL, theDisplaySwitchCallback)
        );
    this.menuService.getMenu('assets/data/business-rule-tree/general-menu.json')
        .subscribe(
          res => this.generalMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => menuHelper.injectCallback(this.generalMenuModel, this.NODE_DETAILED_LABEL, theDisplaySwitchCallback)
        );
  }

  public showTree() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.clearTooltips();
    this.businessRuleTree = null;
    this.nodeLabelType = this.NODE_LABEL_STANDARD;

    this.notificationService.showWaitIndicator(true);
    this.businessRuleTreeService
        .getBusinessRuleTreeCore(this.authToken, this.envId, this.companyCode,
                                 this.productCode, this.includeOrphans)
        .subscribe(
          res => this.businessRuleTree = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => this.notificationService.showWaitIndicator(false)
        );
  }

  public onNodeExpand(event: any) {
    this.clearTooltips();

    const node: TreeNode = event.node;
    const data: TreeNodeData = node.data;

    // Ignore the event if node already has children
    if (node.children && node.children.length > 0) {
      return;
    }
    // Ignore the event if node's children are not lazy
    if (!data || !data.lazyNode) {
      return;
    }

    node.children = <TreeNode[]> [];
    switch (node.type) {
      case TreeNodeType.TypeEnum.CTF.toString():  // COMMON_TABLE_FOLDER
           this.buildCommonTablesList(node);
           break;
      case TreeNodeType.TypeEnum.PDF.toString():  // PDFPLAN_FOLDER
      case TreeNodeType.TypeEnum.PF.toString():   // PLAN_FOLDER
      case TreeNodeType.TypeEnum.PPF.toString():  // PAYOUTPLAN_FOLDER
      case TreeNodeType.TypeEnum.RF.toString():   // RIDER_FOLDER
           this.buildPlanCodeList(node);
           break;
      case TreeNodeType.TypeEnum.P.toString():    // PLAN
      case TreeNodeType.TypeEnum.R.toString():    // RIDER_FOLDER
      case TreeNodeType.TypeEnum.PP.toString():   // PAYOUT_PLAN
           this.buildPlanDetails(node);
           break;
      case TreeNodeType.TypeEnum.OF.toString():   // ORPHAN_FOLDER
           this.buildOrphanSubsetList(node);
           break;
      default:
           break;
    }
  }

  private buildCommonTablesList(node: TreeNode) {
    this.notificationService.showWaitIndicator(true);
    this.businessRuleTreeService
        .getBusinessRuleTreeCommonTablesList(this.authToken, this.envId,
                                             this.companyCode, this.viewChanges)
        .subscribe(
          res => node.children = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.notificationService.showWaitIndicator(false);
              if (!node.children || node.children.length === 0) {
                node.leaf = true;
              }
              if (this.nodeLabelType === this.NODE_LABEL_DETAILED) {
                this.changeNodeLabelType(node.children, this.NODE_LABEL_DETAILED);
              }
            }
        );
  }

  private buildPlanCodeList(node: TreeNode) {
    const data: TreeNodeData = node.data;

    this.notificationService.showWaitIndicator(true);
    this.businessRuleTreeService
        .getBusinessRuleTreePlanList(this.authToken, data.lazyType, this.envId,
                                     this.companyCode, this.productCode, this.planCode,
                                     this.issueState, this.lob, this.effDate,
                                     this.viewChanges, this.includeOrphans)
        .subscribe(
          res => node.children = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.notificationService.showWaitIndicator(false);
              if (!node.children || node.children.length === 0) {
                node.leaf = true;
              }
              if (this.nodeLabelType === this.NODE_LABEL_DETAILED) {
                this.changeNodeLabelType(node.children, this.NODE_LABEL_DETAILED);
              }
          }
        );
  }

  private buildPlanDetails(node: TreeNode) {
    const data: TreeNodeData = node.data;

    this.notificationService.showWaitIndicator(true);
    this.businessRuleTreeService
        .getBusinessRuleTreePlanDetails(this.authToken, this.envId, this.companyCode,
                                        data.planKey, this.viewChanges)
        .subscribe(
          res => node.children = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.notificationService.showWaitIndicator(false);
              if (!node.children || node.children.length === 0) {
                node.leaf = true;
              }
              if (this.nodeLabelType === this.NODE_LABEL_DETAILED) {
                this.changeNodeLabelType(node.children, this.NODE_LABEL_DETAILED);
              }
          }
        );
  }

  private buildOrphanSubsetList(node: TreeNode) {
    const data: TreeNodeData = node.data;

    this.notificationService.showWaitIndicator(true);
    this.businessRuleTreeService
        .getBusinessRuleTreeOrphanSubsets(this.authToken, this.envId,
                                          this.companyCode, this.productCode, this.planCode,
                                          this.issueState, this.lob, this.effDate,
                                          this.viewChanges)
        .subscribe(
          res => node.children = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => {
              this.notificationService.showWaitIndicator(false);
              if (!node.children || node.children.length === 0) {
                node.leaf = true;
              }
              if (this.nodeLabelType === this.NODE_LABEL_DETAILED) {
                this.changeNodeLabelType(node.children, this.NODE_LABEL_DETAILED);
              }
          }
        );
  }

  public onNodeCollapse(event: any) {
    this.clearTooltips();
    const node: TreeNode = event.node;
    const data: TreeNodeData = node.data;
    if (data && data.lazyNode) {
      node.children = <TreeNode[]> [];
    }
  }

  public onNodeSelect(event: any) {
    const node: TreeNode = event.node;
    this.showTooltip(node);
  }

  public onNodeContextMenuSelect(event: any) {
    const node: TreeNode = event.node;
    this.showTooltip(node);

    switch (node.type) {
        case TreeNodeType.TypeEnum.C.toString():    // COMPANY
             this.contextMenuModel = this.companyMenuModel;
             break;
        case TreeNodeType.TypeEnum.CTF.toString():  // COMMON_TABLE_FOLDER
        case TreeNodeType.TypeEnum.PDF.toString():  // PDFPLAN_FOLDER
        case TreeNodeType.TypeEnum.PF.toString():   // PLAN_FOLDER
        case TreeNodeType.TypeEnum.PPF.toString():  // PAYOUTPLAN_FOLDER
        case TreeNodeType.TypeEnum.RF.toString():   // RIDER_FOLDER
             this.contextMenuModel = this.generalMenuModel;
             break;
        case TreeNodeType.TypeEnum.AF.toString():   // ANNUITIY_FOLDER
        case TreeNodeType.TypeEnum.UF.toString():   // UNIV_LIFE_FOLDER
        case TreeNodeType.TypeEnum.TF.toString():   // TRADITIONAL_FOLDER
             this.contextMenuModel = this.planFolderMenuModel;
             break;
        default:
             this.contextMenuModel = null;
    }
  }

  private showTooltip(node: TreeNode) {
    this.clearTooltips();

    const data: TreeNodeData = node.data;
    if (data && data.tableName
     && node.type === TreeNodeType.TypeEnum.TS.toString()) {          // TABLE_SUBSET
      this.tooltips.push({severity: 'info', detail: 'Table Name: ' + data.tableName});
    } else {
      if (node.type === TreeNodeType.TypeEnum.PDF.toString()          // PDFPLAN_FOLDER
       || node.type === TreeNodeType.TypeEnum.AF.toString()           // ANNUITIY_FOLDER
       || node.type === TreeNodeType.TypeEnum.TF.toString()           // TRADITIONAL_FOLDER
       || node.type === TreeNodeType.TypeEnum.UF.toString()) {        // UNIV_LIFE_FOLDER
        this.showProductTooltip(node);
      } else if (node.type === TreeNodeType.TypeEnum.P.toString()     // PLAN
              || node.type === TreeNodeType.TypeEnum.PP.toString()    // PAYOUT_PLAN
              || node.type === TreeNodeType.TypeEnum.R.toString()) {  // RIDER
        this.showPlanTooltip(node);
      } else {
        this.showStandardTooltip(node);
      }
    }
  }

  private showProductTooltip(node: TreeNode) {
    let toolTip = 'Product: ';

    if (node.type === TreeNodeType.TypeEnum.PDF.toString()) {         // PDFPLAN_FOLDER
      const data: TreeNodeData = node.data;
      if (data) {
        if (data.lazyType === TreeNodeData.LazyTypeEnum.PDF) {        // PDF_PLANS
          toolTip += 'P';
        } else if (data.lazyType === TreeNodeData.LazyTypeEnum.H) {   // COMMON_COVERAGE
          toolTip += 'C';
        }
      }
    } else if (node.type === TreeNodeType.TypeEnum.AF.toString()) {   // ANNUITIY_FOLDER
      toolTip += 'A';
    } else if (node.type === TreeNodeType.TypeEnum.TF.toString()) {   // TRADITIONAL_FOLDER
      toolTip += 'T';
    } else if (node.type === TreeNodeType.TypeEnum.UF.toString()) {   // UNIV_LIFE_FOLDER
      toolTip += 'U';
    }

    this.tooltips.push({severity: 'info', detail: toolTip});
  }

  private showPlanTooltip(node: TreeNode) {
    const data: TreeNodeData = node.data;
    if (data) {
      let tipSummary: string;
      if (data.name) {
        tipSummary = 'Table: ' + data.name;
      }

      let tipDetail = 'Company: ' + this.companyCode;
      const planKey: TreeNodePlanKey = data.planKey;
      if (planKey.productPrefix) {
        tipDetail += ', Product: ' + planKey.productPrefix + planKey.productSuffix;
      }
      if (planKey.planCode) {
        tipDetail += ', Plan: ' + planKey.planCode;
      }
      if (planKey.issueState) {
        tipDetail += ',<br>Issue State: ' + planKey.issueState;
      }
      if (planKey.lob) {
        tipDetail += ', LOB: ' + planKey.lob;
      }
      if (planKey.effDate) {
        tipDetail += ', Eff. Date: ' + planKey.effDate;
      }

      this.tooltips.push({severity: 'info', summary: tipSummary, detail: tipDetail});
    }
  }

  private showStandardTooltip(node: TreeNode) {
    const data: TreeNodeData = node.data;
    if (data) {
      let toolTip = '';
      if (data.name) {
        toolTip += 'Table: ' + data.name + ' ';
      }

      if (data.planKey) {
        const planKey: TreeNodePlanKey = data.planKey;
        if (planKey.tablePtrSubset) {
          toolTip += '\xa0\xa0\xa0Subset: ' + planKey.tablePtrSubset;
        }
      }

      if (toolTip.trim().length > 0) {
        this.tooltips.push({severity: 'info', detail: toolTip});
      }
    }
  }

  private clearTooltips() {
    this.tooltips = <Message[]> [];
  }

  private toggleNodeDisplayType() {
    if (this.nodeLabelType === this.NODE_LABEL_STANDARD) {
      this.nodeLabelType = this.NODE_LABEL_DETAILED;
      this.changeMenuIcon('fa-check');
      this.changeNodeLabelType(this.businessRuleTree, this.NODE_LABEL_DETAILED);
    } else {
      this.nodeLabelType = this.NODE_LABEL_STANDARD;
      this.changeMenuIcon('fa-fw');
      this.changeNodeLabelType(this.businessRuleTree, this.NODE_LABEL_STANDARD);
    }
  }

  private changeMenuIcon(icon: string) {
    const helper: MenuHelper = new MenuHelper();
    helper.setIcon(this.generalMenuModel, this.NODE_DETAILED_LABEL, icon);
    helper.setIcon(this.companyMenuModel, this.NODE_DETAILED_LABEL, icon);
    helper.setIcon(this.planFolderMenuModel, this.NODE_DETAILED_LABEL, icon);
  }

  private changeNodeLabelType(treeNodes: TreeNode[], newType: number) {
    for (const treeNode of treeNodes) {
      if (newType === this.NODE_LABEL_DETAILED) {
        this.buildDetailedLabel(treeNode);
      } else {
        this.buildStandardLabel(treeNode);
      }

      if (treeNode.children) {
        this.changeNodeLabelType(treeNode.children, newType);
      }
    }
  }

  // For reference see method toString() in class CscTreeNode in the old Product Wizard
  private buildDetailedLabel(node: TreeNode) {
    switch (node.type) {
      case TreeNodeType.TypeEnum.AF.toString():   // ANNUITIY_FOLDER
      case TreeNodeType.TypeEnum.UF.toString():   // UNIV_LIFE_FOLDER
      case TreeNodeType.TypeEnum.TF.toString():   // TRADITIONAL_FOLDER
      case TreeNodeType.TypeEnum.CTF.toString():  // COMMON_TABLE_FOLDER
      case TreeNodeType.TypeEnum.PF.toString():   // PLAN_FOLDER
      case TreeNodeType.TypeEnum.RF.toString():   // RIDER_FOLDER
      case TreeNodeType.TypeEnum.PDF.toString():  // PDFPLAN_FOLDER
      case TreeNodeType.TypeEnum.PPF.toString():  // PAYOUTPLAN_FOLDER
      case TreeNodeType.TypeEnum.OF.toString():   // ORPHAN_FOLDER
           break;
      default:
           const data: TreeNodeData = node.data;
           let planKey: TreeNodePlanKey;
           if (data) {
             planKey = data.planKey;
           }

           let displayString = '';
           if (data && data.name) {
             displayString = data.name.trim();
           }

           let tableSubsetString = '';
           if (planKey && planKey.tablePtrSubset) {
             tableSubsetString = planKey.tablePtrSubset.trim();
           }

           let variationString = '';
           if (planKey && planKey.tablePtrVar) {
             variationString = planKey.tablePtrVar.trim();
           }

           if (tableSubsetString !== '') {
             displayString += '~' + tableSubsetString;
             if (variationString !== '') {
               displayString += '~' + variationString;
             } else {
               // TODO: implement this condition
               // if (showBlankVariation()) {
               //  displayString += '~Blank';
               // }
             }
           }

           if (displayString !== '') {
             data.tableName = node.label;
             node.label = displayString;
           }

           break;
    }
  }

  private buildStandardLabel(node: TreeNode) {
    const data: TreeNodeData = node.data;
    if (data && data.tableName) {
      node.label = data.tableName;
      data.tableName = null;
    }
  }
}
