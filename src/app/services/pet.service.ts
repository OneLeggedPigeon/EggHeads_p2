import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Egg } from '../models/egg';

import { Pet } from '../models/Pet';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  storage:Storage = localStorage;

  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
    ,'Authorization': `Bearer ${this.storage.getItem("token")}`
  })

  dev:string = 'http://localhost:9000';
  cors:string = 'https://cors.io/?'
  prod:string = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/pet';

  constructor(
    private http:HttpClient,
    private messageService: MessageService
  ) { }

  /** GET Pets of current user */
  getPets(): Observable<Pet[]> {
    let id = localStorage.getItem("user-id");
    const url = `${this.prod}/${id}`;
    return this.http.get<Pet[]>(url,{
        headers: this.headers
      }).pipe(
        tap(_ => this.log(`fetched Pets`)),
        catchError(this.handleError<Pet[]>(`getPets`, []))
      );
  }
  
  /** GET Pet of current user */
  getPet(petId:number): Observable<Pet> {
    let id = localStorage.getItem("user-id");
    const params = new HttpParams()
      .set("pet-id", petId.toString());
    const url = `${this.prod}/${id}?${params.toString()}`;
    return this.http.get<Pet>(url,{
        headers: this.headers
      }).pipe(
        tap(_ => this.log(`fetched Pet id=${petId}`)),
        catchError(this.handleError<Pet>(`getPet`))
      );
  }

  /** POST new Pet to current user */
  addPetFromEgg(egg: Egg, name: string): Observable<Pet> {
    let id = localStorage.getItem("user-id");
    const params = new HttpParams()
      .set("egg-id", egg.id!.toString())
      .set("name", name);
    const url = `${this.prod}/${id}?${params.toString()}`;
    return this.http.post<Pet>(url,{
        headers: this.headers,
      }).pipe(
      tap(_ => this.log(`added Pet ${name} to User id=${id} using Egg id=${egg.id!.toString()}`)),
      catchError(this.handleError<Pet>(`addPetFromEgg`))
    );
  }

  /** Log a IncubatorService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`IncubatorService: ${message}`);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
