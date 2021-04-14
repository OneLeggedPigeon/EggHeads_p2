import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../models/User';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
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
  loginUrl:string = `${this.prod}${this.authenticate}`
  registerUrl:string = `${this.prod}${this.create}`

  constructor(private http:HttpClient, private router: Router) {

  }

  loginUser(user:User){
    let response = this.http.post<any>(this.loginUrl, user, httpOptions);
    response.subscribe(res => {
      if(res.jwt){
        localStorage.setItem("token",res.jwt);
        this.router.navigate(['/dashboard']);
      }
      else{
        //invalid credentials popup
      }
    })
  }

  registerUser(user:User){
    let response = this.http.post<any>(this.registerUrl, user, httpOptions);
    response.subscribe(res => {
      if(res.jwt){
        localStorage.setItem("token",res.jwt);
        this.router.navigate(['/dashboard']);
      }
      else{
        //invalid credentials popup
      }
    })
  }

  logoutUser(){
    localStorage.removeItem("token");
    this.router.navigate(['']);
  }
}
