import { TestBed, async } from '@angular/core/testing';
import { AuditErrorComponent } from './audit-error.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AuditErrorComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AuditErrorComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
