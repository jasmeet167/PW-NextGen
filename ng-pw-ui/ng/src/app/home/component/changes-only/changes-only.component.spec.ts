import { TestBed, async } from '@angular/core/testing';
import { ChangesOnlyComponent } from './changes-only.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ChangesOnlyComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(ChangesOnlyComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
