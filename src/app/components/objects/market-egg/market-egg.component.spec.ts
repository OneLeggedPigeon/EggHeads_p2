import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketEggComponent } from './market-egg.component';

describe('MarketEggComponent', () => {
  let component: MarketEggComponent;
  let fixture: ComponentFixture<MarketEggComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarketEggComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketEggComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
