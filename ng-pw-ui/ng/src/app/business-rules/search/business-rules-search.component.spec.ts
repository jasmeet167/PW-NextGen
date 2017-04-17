import { TestBed, async } from '@angular/core/testing';
import { BusinessRulesSearchComponent } from './business-rules-search.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BusinessRulesSearchComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(BusinessRulesSearchComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
