import { PetService } from 'src/app/services/pet.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetsComponent } from './pets.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PetsComponent', () => {
  let component: PetsComponent;
  let fixture: ComponentFixture<PetsComponent>;

  let PetServiceMock: Partial<PetService> = {

  }
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PetsComponent ],
      providers: [PetService],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
