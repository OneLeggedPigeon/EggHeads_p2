import { Component, OnInit, Input } from '@angular/core';
import { Egg } from 'src/app/models/egg';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-egg',
  templateUrl: './egg.component.html',
  styleUrls: ['./egg.component.css']
})
export class EggComponent implements OnInit {
  @Input() size!:number;
  @Input() red!:number;
  @Input() green!:number;
  @Input() blue!:number;
  baseUrl:string = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/image/egg/';
  imageUrl!:string;

  constructor(private userService: UserService) {
  }

  getSize() { return this.size ?? 0;}
  getRed() { return this.red ?? 0;}
  getGreen() { return this.green ?? 0;}
  getBlue() { return this.blue ?? 0;}

  ngOnInit(): void {
    this.updateImage();
  }

  ngOnChanges(): void {
    this.updateImage();
  }

  updateImage(): void {
    this.imageUrl = this.baseUrl.concat(
      '?size=',Math.min(this.getSize(), 100).toString(),
      '&red=',Math.min(this.getRed(), 255).toString(),
      '&green=',Math.min(this.getGreen(), 255).toString(),
      '&blue=',Math.min(this.getBlue(), 255).toString()
    );
  }
}