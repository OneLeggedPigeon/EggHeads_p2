import { Component, OnInit } from '@angular/core';

import { Egg } from 'src/app/models/egg';
import { IncubatorService } from 'src/app/services/incubator.service';

@Component({
  selector: 'app-incubator-dashboard',
  templateUrl: './incubator-dashboard.component.html',
  styleUrls: ['./incubator-dashboard.component.css']
})
export class IncubatorDashboardComponent implements OnInit {
  eggs:Egg[] = [];
  // only the first 9 eggs are shown in a preview, as there is no scrolling.
  previewEggs:Egg[] = [];
  loaded:boolean = false;
  // Eggs where egg.ready
  hatchableEggs: Egg[] = [];

  constructor(private incubatorService: IncubatorService) { }

  ngOnInit(): void {
    this.getEggs();
  }

  getEggs(): void {
    this.incubatorService.getIncubator()
      .subscribe(incubator => {
        if(incubator.eggs){
          this.eggs = incubator.eggs;
          this.previewEggs = incubator.eggs.slice(0, 9);
          this.hatchableEggs = this.eggs.filter(this.setHatchable);
        }
        this.loaded = true;
      });
  }

  setHatchable(egg: Egg): boolean {
    return egg.ready;
  }
}