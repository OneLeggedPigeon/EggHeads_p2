import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
  loginUrl:string = "http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com/9000/user/authenticate"
  registerUrl:string = "http://eggheadp2-backend.eba-sq2v6sgu.us-east-2.elasticbeanstalk.com//user/create"

  constructor(private http:HttpClient) {

  }

  loginUser(user:User):Observable<any>{
    return this.http.post<User>(this.loginUrl, user, httpOptions);
  }

  registerUser(user:User):Observable<any>{
    return this.http.post<User>(this.registerUrl, user, httpOptions);
  }
}
