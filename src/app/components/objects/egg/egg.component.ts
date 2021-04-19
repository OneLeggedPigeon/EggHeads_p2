import { Component, OnInit } from '@angular/core';
import { Egg } from 'src/app/models/egg';

@Component({
  selector: 'app-egg',
  templateUrl: './egg.component.html',
  styleUrls: ['./egg.component.css']
})
export class EggComponent implements OnInit {
  egg:Egg;

  constructor(egg:Egg) {
    this.egg = egg;
  }

  ngOnInit(): void {
  }

}
