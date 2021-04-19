import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pet } from '../models/Pet';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  dev:string = 'http://localhost:9000';
  cors:string = 'https://cors.io/?'
  prod:string = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com';
  get:string = '/pet';
  getById:string = '/pet/{user-id}';
  getByIdFR:string ='/pet/48';
  getAllUrlDev:string = `${this.dev}${this.get}`
  getAllUrl:string = `${this.prod}${this.get}`
  getByIdUrl:string = `${this.prod}${this.getById}`

  getByIdUrlTesting:string = `${this.prod}${this.getByIdFR}`

  constructor(private http:HttpClient) { }

  getPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(
      this.getByIdUrlTesting);
    
  }

}
