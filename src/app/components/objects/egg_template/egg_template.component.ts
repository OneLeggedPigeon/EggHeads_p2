import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-egg-template',
  templateUrl: './egg_template.component.html',
  styleUrls: ['./egg_template.component.css']
})
export class EggTemplateComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  title = "something"

}
