import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Pet } from '../models/Pet';
import { MessageService } from './message.service';

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

  constructor(
    private http:HttpClient,
    private messageService: MessageService
  ) { }

  getPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(
      this.getByIdUrlTesting).pipe(
        tap(_ => this.log(`fetched Pets`)),
        catchError(this.handleError<Pet[]>(`getPets`, []))
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
