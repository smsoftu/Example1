import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CloseMatPopupComponent } from './CloseMatPopup.component';

describe('CloseMatPopupComponent', () => {
  let component: CloseMatPopupComponent;
  let fixture: ComponentFixture<CloseMatPopupComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CloseMatPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseMatPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
