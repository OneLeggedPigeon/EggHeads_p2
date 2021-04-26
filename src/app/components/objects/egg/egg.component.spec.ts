import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EggComponent } from './egg.component';

describe('EggComponent', () => {
  let component: EggComponent;
  let fixture: ComponentFixture<EggComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EggComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EggComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
