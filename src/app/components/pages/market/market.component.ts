import { Component, OnInit } from '@angular/core';
import { Egg } from 'src/app/models/egg';
import { EggTemplateService } from 'src/app/services/egg-template.service';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {
  eggs!:Egg[];
  count:number = 5;

  constructor(private eggTemplateService: EggTemplateService) { }

  ngOnInit(): void {
    this.getEggs();
  }

  getEggs(): void {
    this.eggTemplateService.getRandomEggs(this.count)
      .subscribe(eggs => {
        this.eggs = eggs;
      });
  }
}
