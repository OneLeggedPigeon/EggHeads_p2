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
  imageUrl!:string;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.imageUrl = this.userService.prod;
    this.imageUrl = this.imageUrl.concat('/image/egg/',
      '?size=',this.size.toString(),
      '&red=',this.red.toString(),
      '&green=',this.green.toString(),
      '&blue=',this.blue.toString()
    );
  }
}