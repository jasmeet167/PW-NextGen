import { NgProductWizardPage } from './app.po';

describe('ng-product-wizard App', () => {
  let page: NgProductWizardPage;

  beforeEach(() => {
    page = new NgProductWizardPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
