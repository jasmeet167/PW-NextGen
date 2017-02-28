/* tslint:disable:no-unused-variable */

import { TestBed, async } from '@angular/core/testing';
import { HomeComponent } from './home.component';

describe('App: NgProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        HomeComponent
      ],
    });
  });

  it('should create the app', async(() => {
    let fixture = TestBed.createComponent(HomeComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
