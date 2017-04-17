import { TestBed, async } from '@angular/core/testing';
import { HomeDecoratorComponent } from './home-decorator.component';

describe('App: ProductWizard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        HomeDecoratorComponent
      ]
    });
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(HomeDecoratorComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
