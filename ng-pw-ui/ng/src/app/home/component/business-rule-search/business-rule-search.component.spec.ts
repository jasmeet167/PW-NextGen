import { TestBed, async } from '@angular/core/testing';
import { BusinessRuleSearchComponent } from './business-rule-search.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BusinessRuleSearchComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(BusinessRuleSearchComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
