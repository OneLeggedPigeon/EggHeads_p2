import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Egg } from '../models/egg';
import { EggTemplate } from '../models/egg-template';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class EggTemplateService {

  private eggTemplateUrl = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/egg_template';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }
  
  /** GET EggTemplates from the server */
  getEggTemplates(): Observable<EggTemplate[]> {
    return this.http.get<EggTemplate[]>(this.eggTemplateUrl, this.httpOptions)
      .pipe(
        tap(_ => this.log('fetched EggTemplates')),
        catchError(this.handleError<EggTemplate[]>('getEggTemplates', []))
      );
  }

  /** GET EggTemplate by id. Will 404 if id not found */
  getEggTemplate(id: number): Observable<EggTemplate> {
    const url = `${this.eggTemplateUrl}/${id}`;
    return this.http.get<EggTemplate>(url, this.httpOptions).pipe(
      tap(_ => this.log(`fetched Template id=${id}`)),
      catchError(this.handleError<EggTemplate>(`getEggTemplate id=${id}`))
    );
  }
  

  /** GET Eggs, one from each existing template */
  getAllRandomEggs(): Observable<Egg[]> {
    const url = `${this.eggTemplateUrl}/market`;
    return this.http.get<Egg[]>(url, this.httpOptions).pipe(
      tap(_ => this.log('fetched Eggs')),
      catchError(this.handleError<Egg[]>(`getAllRandomEggs`, []))
    );
  }

  /** GET Eggs, 'count' of them, from different templates unless count is higher than the number of templates */
  getRandomEggs(count: number): Observable<Egg[]> {
    const url = `${this.eggTemplateUrl}/market/${count}`;
    return this.http.get<Egg[]>(url, this.httpOptions).pipe(
      tap(_ => this.log(`fetched ${count} Eggs`)),
      catchError(this.handleError<Egg[]>(`getRandomEggs count=${count}`))
    );
  }

  // /** PUT: update the EggTemplate on the server */
  // updateEggTemplatee(eggTemplate: EggTemplate): Observable<any> {
  //   return this.http.put(this.eggTemplateUrl, eggTemplate, this.httpOptions).pipe(
  //     tap(_ => this.log(`updated EggTemplate id=${eggTemplate.id}`)),
  //     catchError(this.handleError<any>('updateEggTemplatee'))
  //   );
  // }

  /** POST: add a new Template to the server */
  addEggTemplate(eggTemplate: EggTemplate): Observable<EggTemplate> {
    return this.http.post<EggTemplate>(this.eggTemplateUrl, eggTemplate, this.httpOptions).pipe(
      tap((newTemplate: EggTemplate) => this.log(`added EggTemplate id=${newTemplate.id}`)),
      catchError(this.handleError<EggTemplate>('addEggTemplate'))
    );
  }

  /** DELETE: delete the Template from the server */
  deleteEggTemplate(id: number): Observable<EggTemplate> {
    const url = `${this.eggTemplateUrl}/${id}`;
    return this.http.delete<EggTemplate>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted EggTemplate id=${id}`)),
      catchError(this.handleError<EggTemplate>('deleteEggTemplate'))
    );
  }

  /** Log a TemplateService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TemplateService: ${message}`);
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
