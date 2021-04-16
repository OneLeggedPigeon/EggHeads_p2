import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  someMethod() {
    this.trigger.openMenu();
  }

  logout(){
    this.userService.logoutUser();
  }

}
