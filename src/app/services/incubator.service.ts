import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Egg } from '../models/egg';
import { Incubator } from '../models/incubator';

@Injectable({
  providedIn: 'root'
})
export class IncubatorService {

  private incubatorUrl = 'http://Incubatorheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/incubator';  // URL to web api
  
  httpOptions = {
    headers: new HttpHeaders({ 
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  /** GET Incubator by id. Will 404 if id not found */
  getIncubator(): Observable<Incubator> {
    const url = `${this.incubatorUrl}/${localStorage.getItem("user-id")}`;
    return this.http.get<Incubator>(url).pipe(
      tap(_ => this.log(`fetched Incubator id=${id}`)),
      catchError(this.handleError<Incubator>(`getIncubator id=${id}`))
    );
  }

  /** POST: add a new Egg to the User's Incubator */
  addEggToIncubator(egg: Egg): Observable<Incubator> {
    return this.http.post<Incubator>(this.incubatorUrl, egg, this.httpOptions).pipe(
      tap((newIncubator: Incubator) => this.log(`added Incubator w/ id=${newIncubator.id}`)),
      catchError(this.handleError<Incubator>('addIncubator'))
    );
  }

  /** DELETE: delete the Incubator from the server */
  deleteIncubator(id: number): Observable<Incubator> {
    const url = `${this.IncubatoresUrl}/${id}`;

    return this.http.delete<Incubator>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted Incubator id=${id}`)),
      catchError(this.handleError<Incubator>('deleteIncubator'))
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
