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
  public injectCallback(menuModel: MenuItem[], itemLabel: string, callback: (event: any) => void) {
    const menuItem: MenuItem = this.getMenuItem(menuModel, itemLabel);
    if (menuItem) {
      menuItem.command = callback;
    }
  }

  /**
   * This method is used to set the icon property to a menu item identified
   * by value of the argument 'itemLabel'.
   *
   * @param menuModel Data model corresponding to the menu component
   * @param itemLabel Label used to identify the menu item, to which
   *        the callback is to be injected; must be unique
   * @param icon Icon, which should be displayed in the menu item.
   *        If a null value is provided, the icon will be cleared.
   */
  public setIcon(menuModel: MenuItem[], itemLabel: string, icon: string) {
    const menuItem: MenuItem = this.getMenuItem(menuModel, itemLabel);
    if (menuItem) {
      menuItem.icon = icon;
    }
  }

  /**
   * This method is used to set value of the property 'disabled'
   * of each menu item with a label matching one of elements
   * in the argument 'labels', to value of the argument 'isDisabled'.
   *
   * @param menuModel Data model corresponding to the menu component
   * @param labels Labels used to identify menu items, to which
   *        the argument 'isDisabled' is to be applied.
   * @param isDisabled Value indicating whether the menu items are
   *        to be enabled or disabled.
   */
  public disable(menuModel: MenuItem[], labels: string[], isDisabled: boolean) {
    const foundItems: MenuItem[] = [];
    this.getMenuItemsFromModel(foundItems, menuModel, labels);
    for (const menuItem of foundItems) {
      menuItem.disabled = isDisabled;
    }
  }

  private getMenuItem(menuModel: MenuItem[], label: string): MenuItem {
    if (menuModel) {
      for (const menuItem of menuModel) {
        const foundItem: MenuItem = this.getMenuItemFromModel(menuItem, label);
        if (foundItem) {
          return foundItem;
        }
      }
    }
    return null;
  }

  private getMenuItemFromModel(root: MenuItem, label: string): MenuItem {
    if (root) {
      if (root.label === label) {
        return root;
      }
      if (!root.items) {
        return null;
      }
      for (const menuItem of root.items) {
        const item = this.getMenuItemFromModel(menuItem, label);
        if (item) {
          return item;
        }
      }
    }
    return null;
  }

  private getMenuItemsFromModel(foundItems: MenuItem[], menuModel: MenuItem[], labels: string[]) {
    if (menuModel) {
      for (const menuItem of menuModel) {
        if (this.isInArray(menuItem.label, labels)) {
          foundItems.push(menuItem);
        }
        const children: MenuItem[] = menuItem.items;
        if (children) {
            this.getMenuItemsFromModel(foundItems, children, labels);
        }
      }
    }
  }

  private isInArray(element: string, array: string[]): boolean {
    for (const arrayElement of array) {
      if (element === arrayElement) {
        return true;
      }
    }
    return false;
  }
}
