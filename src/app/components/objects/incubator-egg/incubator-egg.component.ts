import { Component, OnInit, Input } from '@angular/core';
import { Egg } from 'src/app/models/egg';

@Component({
  selector: 'app-incubator-egg',
  templateUrl: './incubator-egg.component.html',
  styleUrls: ['./incubator-egg.component.css']
})
export class IncubatorEggComponent implements OnInit {
  @Input() egg!:Egg;
  hoursHatching!:number;

  constructor() {
  }

  ngOnInit(): void {
    this.hoursHatching = (new Date().getTime() - new Date(this.egg.timeCreated).getTime())/3600000;
  }
}