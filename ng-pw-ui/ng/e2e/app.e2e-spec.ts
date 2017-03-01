import { NextGenProductWizardPage } from './app.po';

describe('ng-product-wizard App', () => {
  let page: NextGenProductWizardPage;

  beforeEach(() => {
    page = new NextGenProductWizardPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
