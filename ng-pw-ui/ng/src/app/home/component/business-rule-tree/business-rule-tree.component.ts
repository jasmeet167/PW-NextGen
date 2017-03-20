import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

import { TreeNode, MenuItem } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { MenuService } from 'app/util/service/menu.service';
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
        .getBusinessRuleTree(this.authToken, this.viewChanges, this.envId,
                             this.companyCode, this.productCode, this.planCode,
                             this.issueState, this.lob, this.effDate, this.includeOrphans)
        .subscribe(
          res => this.businessRuleTree = res,
          err => {
              this.notificationService.handleError(err);
              this.notificationService.showWaitIndicator(false);
          },
          ()  => this.notificationService.showWaitIndicator(false)
        );
  }

  public onNodeContextMenuSelect(event: any) {
    const node: TreeNode = event.node;
    console.log('Node Type = ' + node.type);

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
