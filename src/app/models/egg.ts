import { Timestamp } from "rxjs";

export class Egg {
    id!:number;
    isReady!:boolean;
    timeCreated!:Date;
    timeComplete!:Date;
    startingSize!:number;
    maxSize!:number;
    currentSize!:number;
    redValue!:number;
    greenValue!:number;
    blueValue!:number;
    animalType!:string;
}