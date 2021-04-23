import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Egg } from 'src/app/models/egg';
import { IncubatorService } from 'src/app/services/incubator.service';
import { MessageService } from 'src/app/services/message.service';
import { PetService } from 'src/app/services/pet.service';

@Component({
  selector: 'app-incubator-egg',
  templateUrl: './incubator-egg.component.html',
  styleUrls: ['./incubator-egg.component.css']
})
export class IncubatorEggComponent implements OnInit {
  @Input() egg!:Egg;
  hoursHatching!:number;
  name:string = '';
  clickedHatch:boolean = false;
  hatchError:boolean = false;

  constructor(
    private incubatorService: IncubatorService,
    private petService: PetService,
    private router: Router,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.hoursHatching = (new Date().getTime() - new Date(this.egg.timeCreated).getTime())/3600000;
  }

  hatch(): void {
    // incase they spam click while it's loading
    if(!this.clickedHatch && this.name){
      this.clickedHatch = true;
      this.messageService.add(`hatching Egg ${this.egg.id} with name ${this.name}`);
      // no need to call delete, that happens automatically in the backend when you add the pet successfully
      this.petService.addPetFromEgg(this.egg, this.name).subscribe(pet => {
        if(pet) {
          this.router.navigateByUrl('/pets');
        } else {
          this.clickedHatch = false;
          this.hatchError = true;
        }
      });
    }
  }
}