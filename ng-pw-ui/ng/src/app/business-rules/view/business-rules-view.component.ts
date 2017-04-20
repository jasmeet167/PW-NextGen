import { Component, ViewEncapsulation, OnInit, ViewChild } from '@angular/core';

import { Message } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';

import { BusinessRulesSearchComponent } from '../search/business-rules-search.component';
import { BusinessRulesTreeComponent } from '../tree/business-rules-tree.component';
import { NotificationService } from 'app/notification/service/notification.service';

@Component({
  templateUrl: './business-rules-view.component.html',
  styleUrls: ['./business-rules-view.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class BusinessRulesViewComponent implements OnInit  {
  @ViewChild('leftSearchPanel') leftSearchPanel: BusinessRulesSearchComponent;
  public isLeftSearchPanelVisible: boolean;
  public isLeftPanelOpen: boolean;
  public isLeftProjectVisible: boolean;

  public leftProjectOptions: SelectItem[];
  public leftProject: string;
  public isRightProjectVisible: boolean;

  @ViewChild('rightSearchPanel') rightSearchPanel: BusinessRulesSearchComponent;
  public isRightSearchPanelVisible: boolean;
  public isRightPanelOpen: boolean;
  public rightProjectOptions: SelectItem[];
  public rightProject: string;

  @ViewChild('leftTree') leftTree: BusinessRulesTreeComponent;
  @ViewChild('rightTree') rightTree: BusinessRulesTreeComponent;

  private authToken: string;

  constructor(private notificationService: NotificationService) {
    this.isLeftSearchPanelVisible = true;
    this.isLeftProjectVisible = false;
    this.isLeftPanelOpen = true;

    this.isRightSearchPanelVisible = false;
    this.isRightProjectVisible = false;
    this.isRightPanelOpen = false;

    this.authToken = sessionStorage['authToken'];
  }

  ngOnInit() {
    if (!this.authToken || this.authToken.trim() === '') {
      this.notificationService.navigateToLogin();
      return;
    }

    this.leftSearchPanel.goCallback = () => {
      this.leftProjectOptions = this.adaptProjectRows(this.leftSearchPanel.projectRows);
      this.isLeftProjectVisible = true;
      this.isLeftSearchPanelVisible = false;
      this.leftTree.showTree();
    };

    this.rightSearchPanel.goCallback = () => {
      this.rightProjectOptions = this.adaptProjectRows(this.rightSearchPanel.projectRows);
      this.isRightProjectVisible = true;
      this.isRightSearchPanelVisible = false;
      this.rightTree.showTree();
    };

    const clearTreeNodeTooltips = () => {
      this.leftTree.tooltips = <Message[]> [];
      this.rightTree.tooltips = <Message[]> [];
    };

    this.leftTree.clearTreeNodeTooltips = clearTreeNodeTooltips;
    this.rightTree.clearTreeNodeTooltips = clearTreeNodeTooltips;
  }

  public onLeftPanelSearchClick() {
    this.isLeftPanelOpen = true;
    this.isLeftSearchPanelVisible = true;
    this.isRightSearchPanelVisible = false;
  }

  public onRightPanelSearchClick() {
    this.isRightPanelOpen = true;
    this.isRightSearchPanelVisible = true;
    this.isLeftSearchPanelVisible = false;
  }

  public onLeftPanelOpenClick() {
    this.isLeftPanelOpen = true;
  }

  public onLeftPanelCloseClick() {
    this.isLeftPanelOpen = false;
  }

  public onRightPanelOpenClick() {
    this.isRightPanelOpen = true;
  }

  public onRightPanelCloseClick() {
    this.isRightPanelOpen = false;
  }

  public isLeftPanelOpenDisabled(): boolean {
    return this.isLeftPanelOpen;
  }

  public isLeftPanelCloseDisabled(): boolean {
    return !this.isLeftPanelOpen
        || (this.isLeftPanelOpen && !this.isRightPanelOpen);
  }

  public isRightPanelOpenDisabled(): boolean {
    return this.isRightPanelOpen;
  }

  public isRightPanelCloseDisabled(): boolean {
    return !this.isRightPanelOpen
        || (this.isRightPanelOpen && !this.isLeftPanelOpen);
  }

  public getLeftSearchPanelDisplayStyle(): string {
    return this.isLeftPanelOpen ? 'table-cell' : 'none';
  }

  public getRightSearchPanelDisplayStyle(): string {
    return this.isRightPanelOpen ? 'table-cell' : 'none';
  }

  public getSearchPanelSplitBorderStyle(): string {
    return (this.isLeftPanelOpen && this.isRightPanelOpen) ? '1px dotted' : 'none';
  }

  private adaptProjectRows(projectRows: SelectItem[]): SelectItem[] {
    const projectOptions: SelectItem[] = [];
    projectRows.forEach((row) => {
      if (row.value === null) {
        const emptyOption: SelectItem = { 'value': null, 'label': '' };
        projectOptions.push(emptyOption);
      } else {
        projectOptions.push(row);
      }
    });
    return projectOptions;
  }
}
