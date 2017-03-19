import { TestBed, async } from '@angular/core/testing';
import { ContinuityCheckComponent } from './continuity-check.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ContinuityCheckComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(ContinuityCheckComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
