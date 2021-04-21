import { Egg } from '../models/egg';

export interface Incubator {
    id:number;
    capacity:number;
    eggs:Egg[];
}