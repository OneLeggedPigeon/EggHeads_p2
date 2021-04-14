import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username!: string;
  password!: string;

  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  onLogin(){
    let user:User = {
      username: this.username,
      password: this.password
    }
    this.userService.loginUser(user);
    //retrieve the token
    //console.log(localStorage.getItem("token"));
  }

  onRegister(){
    let user:User = {
      username: this.username,
      password: this.password
    }
    this.userService.registerUser(user);
  }
}
