import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EggTemplateComponent } from './egg_template.component';

describe('EggTemplateComponent', () => {
  let component: EggTemplateComponent;
  let fixture: ComponentFixture<EggTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EggTemplateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EggTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
