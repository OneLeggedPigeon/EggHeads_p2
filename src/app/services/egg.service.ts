import { Injectable } from '@angular/core';

import { Egg } from '../models/egg'

@Injectable({
  providedIn: 'root'
})
export class EggService {

  constructor() { }

  getEggs(): Egg[]{
    let eggs:Egg[] = [
      {
        "id": 1,
        "timeCreated": "2021-04-19T19:13:21.607Z",
        "timeComplete": "2021-04-19T19:13:21.607Z",
        "startingSize": 0,
        "maxSize": 0,
        "currentSize": 10,
        "redValue": 10,
        "greenValue": 10,
        "blueValue": 10,
        "animalType": "one",
        "ready": false
      },
      {
        "id": 2,
        "timeCreated": "2021-04-19T19:13:21.607Z",
        "timeComplete": "2021-04-19T19:13:21.607Z",
        "startingSize": 0,
        "maxSize": 0,
        "currentSize": 20,
        "redValue": 100,
        "greenValue": 100,
        "blueValue": 100,
        "animalType": "two",
        "ready": true
      },
      {
        "id": 3,
        "timeCreated": "2021-04-19T19:13:21.607Z",
        "timeComplete": "2021-04-19T19:13:21.607Z",
        "startingSize": 0,
        "maxSize": 0,
        "currentSize": 30,
        "redValue": 200,
        "greenValue": 200,
        "blueValue": 200,
        "animalType": "three",
        "ready": false
      },
      {
        "id": 4,
        "timeCreated": "2021-04-19T19:13:21.607Z",
        "timeComplete": "2021-04-19T19:13:21.607Z",
        "startingSize": 0,
        "maxSize": 0,
        "currentSize": 40,
        "redValue": 250,
        "greenValue": 250,
        "blueValue": 250,
        "animalType": "four",
        "ready": false
      }
    ]
    return eggs;
  }
}
