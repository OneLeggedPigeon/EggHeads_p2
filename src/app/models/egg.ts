export interface Egg {
    id?:number;
    ready:boolean;
    timeCreated:string;
    timeComplete:string;
    startingSize?:number;
    maxSize?:number;
    currentSize:number;
    redValue:number;
    greenValue:number;
    blueValue:number;
    animalType:string;
}