import { TestBed, async } from '@angular/core/testing';
import { NotificationComponent } from './notification.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        NotificationComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(NotificationComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
