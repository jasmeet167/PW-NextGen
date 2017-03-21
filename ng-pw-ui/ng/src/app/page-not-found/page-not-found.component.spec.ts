import { TestBed, async } from '@angular/core/testing';
import { PageNotFoundComponent } from './page-not-found.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        PageNotFoundComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(PageNotFoundComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
