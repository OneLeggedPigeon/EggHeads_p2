import { Component, Input, OnInit } from '@angular/core';
import { EggTemplate } from 'src/app/models/egg-template';
import { EggTemplateService } from 'src/app/services/egg-template.service';

@Component({
  selector: 'app-new-template',
  templateUrl: './new-template.component.html',
  styleUrls: ['./new-template.component.css']
})
export class NewTemplateComponent implements OnInit {
  id!: number;
  name!: string;
  incubationPeriod!: number;
  size!: number;
  redVal!: number;
  greenVal!: number;
  blueVal!: number;

  constructor(private templateService:EggTemplateService) { }

  ngOnInit(): void {
  }

  onCreate() {
    let template:EggTemplate = {
      // hardcode in an id or nah? how to set?
      id: this.id,
      eggTemplateName: this.name,
      incubationPeriod: this.incubationPeriod,
      size: this.size,
      redValue: this.redVal,
      greenValue: this.greenVal,
      blueValue: this.blueVal
    }

    this.templateService.addEggTemplate(template);
    console.log("created, maybe");
  }

}
