import { Component, OnInit, Input } from '@angular/core';
import { Egg } from 'src/app/models/egg';

@Component({
  selector: 'app-egg',
  templateUrl: './egg.component.html',
  styleUrls: ['./egg.component.css']
})
export class EggComponent implements OnInit {
  @Input() egg!:Egg;
  @Input() imageSize!:string; // 'small', 'medium', 'large'
  imageUrl!:string;
  hoursHatching!:number;

  constructor() {
  }

  ngOnInit(): void {
    this.hoursHatching = (new Date().getTime() - new Date(this.egg.timeCreated).getTime())/3600000;
    this.imageUrl = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com';
    let size:string = this.egg.currentSize.toString();
    let red:string = this.egg.redValue.toString();
    let green:string = this.egg.greenValue.toString();
    let blue:string = this.egg.blueValue.toString();
    this.imageUrl = this.imageUrl.concat('/image/egg/',this.imageSize,'?size=',size,'&red=',red,'&green=',green,'&blue=',blue);
  }

}
