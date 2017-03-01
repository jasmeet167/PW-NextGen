/* tslint:disable:no-unused-variable */

import { TestBed, async } from '@angular/core/testing';
import { HomeComponent } from './home.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        HomeComponent
      ],
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(HomeComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
