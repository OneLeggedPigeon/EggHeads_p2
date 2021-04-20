import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Egg } from '../models/egg'
import { EGGS } from '../mock/mock-eggs';
import { UserService } from './user.service'


@Injectable({
  providedIn: 'root'
})
export class EggTemplateService {

  private eggsUrl = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/incubator';  // URL to web api
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient
  ) { }
  
  /** GET Egges from the server */
  getEggs(): Observable<Egg[]> {
    return this.http.get<Egg[]>(this.EggesUrl)
      .pipe(
        tap(_ => this.log('fetched Egges')),
        catchError(this.handleError<Egg[]>('getEgges', []))
      );
  }

  /** GET Egg by id. Will 404 if id not found */
  getEgg(id: number): Observable<Egg> {
    const url = `${this.EggesUrl}/${id}`;
    return this.http.get<Egg>(url).pipe(
      tap(_ => this.log(`fetched Egg id=${id}`)),
      catchError(this.handleError<Egg>(`getEgg id=${id}`))
    );
  }

  /** PUT: update the Egg on the server */
  updateEgg(Egg: Egg): Observable<any> {
    return this.http.put(this.EggesUrl, Egg, this.httpOptions).pipe(
      tap(_ => this.log(`updated Egg id=${Egg.id}`)),
      catchError(this.handleError<any>('updateEgg'))
    );
  }

  /** POST: add a new Egg to the server */
  addEgg(Egg: Egg): Observable<Egg> {
    return this.http.post<Egg>(this.EggesUrl, Egg, this.httpOptions).pipe(
      tap((newEgg: Egg) => this.log(`added Egg w/ id=${newEgg.id}`)),
      catchError(this.handleError<Egg>('addEgg'))
    );
  }

  /** DELETE: delete the Egg from the server */
  deleteEgg(id: number): Observable<Egg> {
    const url = `${this.EggesUrl}/${id}`;

    return this.http.delete<Egg>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted Egg id=${id}`)),
      catchError(this.handleError<Egg>('deleteEgg'))
    );
  }

  /** Log a EggService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`EggService: ${message}`);
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
