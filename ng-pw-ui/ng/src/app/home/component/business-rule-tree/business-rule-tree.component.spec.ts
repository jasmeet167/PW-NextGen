import { TestBed, async } from '@angular/core/testing';
import { BusinessRuleTreeComponent } from './business-rule-tree.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BusinessRuleTreeComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(BusinessRuleTreeComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
