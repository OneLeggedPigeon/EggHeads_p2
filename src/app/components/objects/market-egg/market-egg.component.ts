import { Component, OnInit, Input } from '@angular/core';
import { Egg } from 'src/app/models/egg';
import { IncubatorService } from 'src/app/services/incubator.service';

@Component({
  selector: 'app-market-egg',
  templateUrl: './market-egg.component.html',
  styleUrls: ['./market-egg.component.css']
})
export class MarketEggComponent implements OnInit {
  @Input() egg!:Egg;

  constructor(private incubatorService: IncubatorService) { }

  ngOnInit(): void {
  }

  buy(): void {
    this.incubatorService.addEggToIncubator(this.egg)
      .subscribe();
  }
}
