import { Component, OnInit } from '@angular/core';
import { Egg } from 'src/app/models/egg';

@Component({
  selector: 'app-egg',
  templateUrl: './egg.component.html',
  styleUrls: ['./egg.component.css']
})
export class EggComponent implements OnInit {
  egg:Egg;
  imageUrl:string;
  imageSize:string;

  constructor() {
    this.egg = new Egg();
    this.imageSize = "small";// 'small', '', 'large'
    this.imageUrl = "http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/image/egg/"
  }

  ngOnInit(): void {
    // let size:string = this.egg.currentSize.toString();
    // let red:string = this.egg.redValue.toString();
    // let green:string = this.egg.greenValue.toString();
    // let blue:string = this.egg.blueValue.toString();
    let size = "10";
    let red = "100";
    let green = "100";
    let blue = "100";
    this.imageUrl = this.imageUrl.concat(this.imageSize,'?size=',size,'&red=',red,'&green=',green,'&blue=',blue);
  }

}
