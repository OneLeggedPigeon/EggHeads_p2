import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Egg } from '../models/egg'
import { EGGS } from '../mock/mock-eggs';
import { UserService } from './user.service'


@Injectable({
  providedIn: 'root'
})
export class EggService {

  constructor(private userService: UserService) { }

  getEggs(): Observable<Egg[]> {
    const eggs = of(EGGS);
    return eggs;
  }
  
  getHero(id: number): Observable<Egg> {
    // For now, assume that a hero with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.
    const egg = EGGS.find(e => e.id === id) as Egg;
    return of(egg);
  }
}
