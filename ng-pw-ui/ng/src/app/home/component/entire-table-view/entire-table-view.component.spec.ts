import { TestBed, async } from '@angular/core/testing';
import { EntireTableViewComponent } from './entire-table-view.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        EntireTableViewComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(EntireTableViewComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
