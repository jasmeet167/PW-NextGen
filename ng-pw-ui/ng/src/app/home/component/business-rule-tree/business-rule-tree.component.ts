import { Component, ViewEncapsulation, Input } from '@angular/core';

import { TreeNode } from 'primeng/primeng';

import { NotificationService } from 'app/notification/service/notification.service';
import { BusinessRuleTreeService } from './service/business-rule-tree.service';

// The property encapsulation: ViewEncapsulation.None is required to load resources,
// such as those pointed to by the property styleUrls.
@Component({
  selector: 'app-business-rule-tree',
  templateUrl: './business-rule-tree.component.html',
  styleUrls: ['./business-rule-tree.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class BusinessRuleTreeComponent {
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

  private authToken: string;

  constructor(private notificationService: NotificationService,
              private businessRuleTreeService: BusinessRuleTreeService) {
    this.authToken = sessionStorage['authToken'];
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

  public onNodeSelect(event: any) {
    console.log('Node Selected');
  }
}
