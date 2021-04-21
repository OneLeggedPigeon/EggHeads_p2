import { Component, OnInit } from '@angular/core';
import { EggTemplateService } from 'src/app/services/egg-template.service';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {
  

  constructor(eggTemplateService: EggTemplateService) { }

  ngOnInit(): void {
  }

}
