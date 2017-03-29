import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

import { TreeNode, MenuItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { MenuService } from 'app/util/service/menu.service';
import { TreeNodeData } from './model/tree-node-data';
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

  public businessRuleTree: TreeNode[];
  public selectedNode: TreeNode;
  public contextMenuModel: MenuItem[];

  private authToken: string;

  private companyMenuModel: MenuItem[];
  private planFolderMenuModel: MenuItem[];
  private generalMenuModel: MenuItem[];

  constructor(private notificationService: NotificationService, private menuService: MenuService,
              private businessRuleTreeService: BusinessRuleTreeService) {
    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.menuService.getMenu('assets/data/business-rule-tree/company-menu.json')
        .subscribe(
          res => this.companyMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => {}
        );
    this.menuService.getMenu('assets/data/business-rule-tree/plan-folder-menu.json')
        .subscribe(
          res => this.planFolderMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => {}
        );
    this.menuService.getMenu('assets/data/business-rule-tree/general-menu.json')
        .subscribe(
          res => this.generalMenuModel = res,
          err => this.notificationService.handleError(err),
          ()  => {}
        );
  }

  public showTree() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.businessRuleTree = null;
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
      case 'CTF':     // COMMON_TABLE_FOLDER
            this.buildCommonTablesList(node);
            break;
      case 'PDF':     // PDFPLAN_FOLDER
      case 'PF':      // PLAN_FOLDER
      case 'PPF':     // PAYOUTPLAN_FOLDER
      case 'RF':      // RIDER_FOLDER
            this.buildPlanCodeList(node);
            break;
      case 'P':       // PLAN
      case 'R':       // RIDER_FOLDER
      case 'PP':      // PAYOUT_PLAN
            this.buildPlanDetails(node);
            break;
      case 'OF':      // ORPHAN_FOLDER
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
          }
        );
  }

  private buildOrphanSubsetList(node: TreeNode) {
    const data: TreeNodeData = node.data;

    // this.notificationService.showWaitIndicator(true);
    // this.businessRuleTreeService
    //     .getBusinessRuleTreePlanDetails(this.authToken, this.envId, this.companyCode,
    //                                     data.planKey, this.viewChanges)
    //     .subscribe(
    //       res => node.children = res,
    //       err => {
    //           this.notificationService.handleError(err);
    //           this.notificationService.showWaitIndicator(false);
    //       },
    //       ()  => {
    //           this.notificationService.showWaitIndicator(false);
    //           if (!node.children || node.children.length === 0) {
    //             node.leaf = true;
    //           }
    //       }
    //     );
  }

  public onNodeCollapse(event: any) {
    const node: TreeNode = event.node;
    const data: TreeNodeData = node.data;
    if (data && data.lazyNode) {
      node.children = <TreeNode[]> [];
    }
  }

  public onNodeContextMenuSelect(event: any) {
    const node: TreeNode = event.node;
    console.log('Context Menu for Node Type = ' + node.type);

    switch (node.type) {
        case 'C':       // Company
                this.contextMenuModel = this.companyMenuModel;
                break;
        case 'CTF':     // Common Table Folder
        case 'PDF':     // PDF Plans Folder
        case 'PF':      // Plan Folder
        case 'PPF':     // Payout Plan Folder
        case 'RF':      // Rider Folder
                this.contextMenuModel = this.generalMenuModel;
                break;
        case 'AF':      // Annuity Folder
        case 'UF':      // UL Folder
        case 'TF':      // Traditional Folder
                this.contextMenuModel = this.planFolderMenuModel;
                break;
        default:
            this.contextMenuModel = null;
    }
  }
}
