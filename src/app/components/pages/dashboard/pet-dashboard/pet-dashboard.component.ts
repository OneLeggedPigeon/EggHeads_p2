
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/Pet';
import { PetService } from 'src/app/services/pet.service';

@Component({
  selector: 'app-pet-dashboard',
  templateUrl: './pet-dashboard.component.html',
  styleUrls: ['./pet-dashboard.component.css']
})
export class PetDashboardComponent implements OnInit {
  pets: Pet[] = [];
  loaded: boolean = false;

  constructor(
    private petService:PetService
  ) { }

  ngOnInit(): void {
    this.petService.getPets().subscribe(pets => {
      this.pets = pets;
      this.loaded = true;
    });
  }
}
