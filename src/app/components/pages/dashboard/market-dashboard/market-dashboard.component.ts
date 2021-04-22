import { Component, OnInit } from '@angular/core';
import { Egg } from 'src/app/models/egg';
import { EggTemplateService } from 'src/app/services/egg-template.service';

@Component({
  selector: 'app-market-dashboard',
  templateUrl: './market-dashboard.component.html',
  styleUrls: ['./market-dashboard.component.css']
})
export class MarketDashboardComponent implements OnInit {

  eggs!:Egg[];
  count:number = 6;

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
