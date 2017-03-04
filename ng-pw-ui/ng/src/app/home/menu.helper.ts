import { Injectable } from '@angular/core';
import { MenuItem } from 'primeng/primeng';

export class MenuHelper {
  /**
   * This method is used to inject a callback function, which is to be
   * used to trigger events fired by the menu item identified by
   * the argument 'itemLabel'.
   * This is necessary, because in menu component based on a model loaded
   * from a JSON file values of the attribute 'command' are created as
   * strings, rather than callback methods corresponding to these strings.
   *
   * @param menuModel Data model corresponding to the menu component
   * @param itemLabel Label used to identify the menu item, to which
   *        the callback is to be injected; must be unique
   * @param callback The callback function to be used for handling
   *        of events from the selected menu item
   */
  injectCallback(menuModel: MenuItem[], itemLabel: string, callback: (event: any) => void) {
    const aboutApplication: MenuItem = this.getMenuItem(menuModel, 'About');
    if (aboutApplication) {
      aboutApplication.command = callback;
    }
  }

  private getMenuItem(menuItems: MenuItem[], label: string): MenuItem {
    if (menuItems) {
      for (const menuItem of menuItems) {
        const foundItem: MenuItem = this.getMenuItemFromTree(menuItem, label);
        if (foundItem) {
          return foundItem;
        }
      }
    }
    return null;
  }

  private getMenuItemFromTree(root: MenuItem, label: string): MenuItem {
    if (root) {
      if (root.label === label) {
        return root;
      }
      if (!root.items) {
        return null;
      }
      for (const menuItem of root.items) {
        const item = this.getMenuItemFromTree(menuItem, label);
        if (item) {
          return item;
        }
      }
    }
    return null;
  }
}
