import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncubatorEggComponent } from './incubator-egg.component';

describe('IncubatorEggComponent', () => {
  let component: IncubatorEggComponent;
  let fixture: ComponentFixture<IncubatorEggComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IncubatorEggComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IncubatorEggComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
