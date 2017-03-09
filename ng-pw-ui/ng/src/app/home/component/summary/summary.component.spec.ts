/* tslint:disable:no-unused-variable */

import { TestBed, async } from '@angular/core/testing';
import { SummaryComponent } from './summary.component';

describe('App: NextGenProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        SummaryComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(SummaryComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
