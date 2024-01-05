import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadFileViewComponent } from './UploadFileView.component';

describe('UploadFileViewComponent', () => {
  let component: UploadFileViewComponent;
  let fixture: ComponentFixture<UploadFileViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadFileViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadFileViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
