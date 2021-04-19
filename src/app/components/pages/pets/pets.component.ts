import { PetService } from './../../../services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/Pet';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {
  pets: Pet[] = [];

  constructor(private petService:PetService) { }

  ngOnInit(): void {
    this.petService.getPets().subscribe(pets => {
      this.pets = pets;
    });
  }


}
