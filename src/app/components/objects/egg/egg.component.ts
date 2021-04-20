import { Component, OnInit, Input } from '@angular/core';
import { Egg } from 'src/app/models/egg';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-egg',
  templateUrl: './egg.component.html',
  styleUrls: ['./egg.component.css']
})
export class EggComponent implements OnInit {
  @Input() egg!:Egg;
  imageUrl!:string;
  hoursHatching!:number;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.hoursHatching = (new Date().getTime() - new Date(this.egg.timeCreated).getTime())/3600000;
    this.imageUrl = this.userService.prod;;
    let size:string = this.egg.currentSize.toString();
    let red:string = this.egg.redValue.toString();
    let green:string = this.egg.greenValue.toString();
    let blue:string = this.egg.blueValue.toString();
    this.imageUrl = this.imageUrl.concat('/image/egg/','?size=',size,'&red=',red,'&green=',green,'&blue=',blue);
  }
}