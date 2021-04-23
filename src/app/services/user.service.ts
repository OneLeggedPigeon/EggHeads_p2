import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../models/User';

const httpOptions = {
  headers: new HttpHeaders({
    'skip': 'true'
  })
}
@Injectable({
  providedIn: 'root'
})
export class UserService {
  dev:string = 'http://localhost:9000';
  prod:string = 'http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com';
  authenticate:string = '/user/authenticate';
  create:string = '/user/create';
  loginUrl:string = `${this.prod}${this.authenticate}`;
  registerUrl:string = `${this.prod}${this.create}`;
  storage:Storage = localStorage;

  constructor(private http:HttpClient, private router: Router) {

  }

  loginUser(user:User){
    let response = this.http.post<any>(this.loginUrl, user, httpOptions)

    .pipe(
      catchError(err => {
        alert("Could Not Authorize Your Credentials.");
        return throwError(err);
      })
    )

    .subscribe(res => {
      if(res.jwt){
        this.storage.setItem("token",res.jwt);
        this.storage.setItem("username", user.username);
        this.storage.setItem("user-id",res.userId);
        this.router.navigate(['/dashboard']);
      }
    })
  }

  registerUser(user:User){
    let response = this.http.post<any>(this.registerUrl, user, httpOptions)

    .pipe(
      catchError(err => {
        alert("User Already Exists");
        return throwError(err);
      })
    )


    .subscribe(res => {
      if(res.username){
        this.loginUser(user);
      }
    })
  }

  isUserLoggedIn(){
    let username = this.storage.getItem("username");
    let token = this.storage.getItem("token");
    let userId = this.storage.getItem("user-id");
    return !(username === null || token === null || userId === null);
  }

  logoutUser(){
    this.storage.removeItem("token");
    this.storage.removeItem("username");
    this.storage.removeItem("user-id");
    this.router.navigate(['']);
  }
}
