import { TestBed, async } from '@angular/core/testing';
import { ApplyChangesComponent } from './apply-changes.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ApplyChangesComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(ApplyChangesComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
