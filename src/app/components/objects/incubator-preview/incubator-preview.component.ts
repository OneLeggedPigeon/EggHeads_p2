import { Component, OnInit } from '@angular/core';

import { Egg } from 'src/app/models/egg';
import { EggService } from 'src/app/services/egg.service';

@Component({
  selector: 'app-incubator-preview',
  templateUrl: './incubator-preview.component.html',
  styleUrls: ['./incubator-preview.component.css']
})
export class IncubatorPreviewComponent implements OnInit {
  eggs:Egg[] = [];
  imageSize!:string;

  constructor(private eggService: EggService) { }

  ngOnInit(): void {
    this.imageSize = 'small';
    this.getEggs();
  }

  getEggs(): void {
    this.eggService.getEggs()
      .subscribe(eggs => this.eggs = eggs);
  }
}
