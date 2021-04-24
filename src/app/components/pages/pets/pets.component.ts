import { PetService } from './../../../services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/Pet';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {
  pets: Pet[] = [];
  pageSlice : Pet[] = [];
  selectedPet? : Pet;
  abandonedPet? : Pet;
  petRemoved : boolean = false;
  storage:Storage = localStorage;
  owner?: string;

  constructor(
    private petService:PetService
  ) { }

  ngOnInit(): void {
 
    this.petService.getPets().subscribe(pets => {
      this.pets = pets;
      this.pageSlice = this.pets.slice(0, 5);
    });
  }

  onSelect(pet: Pet): void {
    this.selectedPet = pet;
    let ownerName = this.storage.getItem("username");
    if (!(ownerName === null)){
      this.owner = ownerName;
    }
  }

  abandon(pet: Pet): void {
    this.abandonedPet = pet;
    this.petService.removePet(this.abandonedPet.id).subscribe(pet => {
      if (pet) this.petRemoved = true;
    });
  }

  reload(): void {
  }

  toConfirm(pet: Pet){
    var name = pet.name;
    var answer = confirm("ARE YOU SURE???");
    if (answer == true){
        this.abandon(pet);
        this.refreshPage();
        this.yeeted(name);
    }
  }

  refreshPage(){
    window.location.reload();
  }

  yeeted(name: String) {
    alert(
    `${name} has been yeeted, hope you said goodbye :(`);
  }

  onPageChange(event: PageEvent){
    const startIndex = event.pageIndex * event.pageSize;
    let endIndex = startIndex + event.pageSize;
    if (endIndex > this.pets.length) {
      endIndex = this.pets.length;
    }
    this.pageSlice = this.pets.slice(startIndex, endIndex);
  }
}
