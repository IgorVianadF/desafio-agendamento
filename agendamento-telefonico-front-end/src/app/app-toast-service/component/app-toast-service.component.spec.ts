import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppToastServiceComponent } from './app-toast-service.component';

describe('AppToastServiceComponent', () => {
  let component: AppToastServiceComponent;
  let fixture: ComponentFixture<AppToastServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppToastServiceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppToastServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
