import { ProductWizardPage } from './app.po';

describe('ng-product-wizard App', () => {
  let page: ProductWizardPage;

  beforeEach(() => {
    page = new ProductWizardPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
