import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Egg } from '../models/egg';
import { Incubator } from '../models/incubator';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class IncubatorService {

  private incubatorUrl = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/incubator';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /** GET Incubator current user ID */
  getIncubator(): Observable<Incubator> {
    let id = localStorage.getItem("user-id");
    const url = `${this.incubatorUrl}/${id}`;
    return this.http.get<Incubator>(url).pipe(
      tap(_ => this.log(`fetched Incubator id=${id}`)),
      catchError(this.handleError<Incubator>(`getIncubator`))
    );
  }

  /** POST: add a new Egg to the User's Incubator */
  addEggToIncubator(egg: Egg): Observable<Incubator> {
    let id = localStorage.getItem("user-id");
    const url = `${this.incubatorUrl}/${id}`;
    return this.http.post<Incubator>(url, egg).pipe(
      tap(_ => this.log(`added Egg to Incubator ${id}`)),
      catchError(this.handleError<Incubator>('addEggToIncubator'))
    );
  }

  /** DELETE: delete an egg from the User's Incubator */
  deleteEggFromIncubator(egg: Egg): Observable<Incubator> {
    let id = localStorage.getItem("user-id");
    let eggId:string = egg.id!.toString();
    const url = `${this.incubatorUrl}/${id}`;
    return this.http.delete<Incubator>(url).pipe(
      tap(_ => this.log(`deleted Egg from Incubator ${id}`)),
      catchError(this.handleError<Incubator>('deleteEggFromIncubator'))
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
