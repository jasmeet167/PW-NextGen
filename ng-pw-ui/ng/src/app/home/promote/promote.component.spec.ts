/* tslint:disable:no-unused-variable */

import { TestBed, async } from '@angular/core/testing';
import { PromoteComponent } from './promote.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        PromoteComponent
      ],
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(PromoteComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
