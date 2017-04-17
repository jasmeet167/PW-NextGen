import { TestBed, async } from '@angular/core/testing';
import { BusinessRulesTreeComponent } from './business-rules-tree.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BusinessRulesTreeComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(BusinessRulesTreeComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
