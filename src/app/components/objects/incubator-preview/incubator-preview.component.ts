import { Component, OnInit } from '@angular/core';

import { Egg } from 'src/app/models/egg';
import { Incubator } from 'src/app/models/incubator';
import { EggTemplateService } from 'src/app/services/egg-template.service';
import { IncubatorService } from 'src/app/services/incubator.service';

@Component({
  selector: 'app-incubator-preview',
  templateUrl: './incubator-preview.component.html',
  styleUrls: ['./incubator-preview.component.css']
})
export class IncubatorPreviewComponent implements OnInit {
  eggs:Egg[] = [];

  constructor(private incubatorService: IncubatorService) { }

  ngOnInit(): void {
    this.getEggs();
  }

  getEggs(): void {
    this.incubatorService.getIncubator()
      .subscribe(incubator => this.eggs = incubator.eggs);
  }
}
