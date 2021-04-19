import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncubatorPreviewComponent } from './incubator-preview.component';

describe('IncubatorPreviewComponent', () => {
  let component: IncubatorPreviewComponent;
  let fixture: ComponentFixture<IncubatorPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IncubatorPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IncubatorPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
