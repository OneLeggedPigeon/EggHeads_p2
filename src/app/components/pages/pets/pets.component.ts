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
  selectedPet? : Pet;
  storage:Storage = localStorage;
  owner?: string;

  constructor(private petService:PetService) { }

  ngOnInit(): void {
    this.petService.getPets().subscribe(pets => {
      this.pets = pets;
    });
  }

  onSelect(pet: Pet): void {
    this.selectedPet = pet;
    let ownerName = this.storage.getItem("username");
    if (!(ownerName === null)){
      this.owner = ownerName;
    }
  }


}
