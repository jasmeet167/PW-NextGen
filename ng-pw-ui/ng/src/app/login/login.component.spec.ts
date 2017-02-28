/* tslint:disable:no-unused-variable */

import { TestBed, async } from '@angular/core/testing';
import { LoginComponent } from './login.component';

describe('App: NgProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        LoginComponent
      ],
    });
  });

  it('should create the app', async(() => {
    let fixture = TestBed.createComponent(LoginComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
