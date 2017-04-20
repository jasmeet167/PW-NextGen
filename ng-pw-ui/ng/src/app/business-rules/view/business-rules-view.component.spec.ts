import { TestBed, async } from '@angular/core/testing';
import { BusinessRulesViewComponent } from './business-rules-view.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BusinessRulesViewComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(BusinessRulesViewComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
