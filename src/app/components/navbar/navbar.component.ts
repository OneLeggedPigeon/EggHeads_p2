import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { of } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger!: MatMenuTrigger;
  constructor(private userService: UserService) { }

  fullNav:boolean = true;
  username!:String | null;

  menuItems = [
    {
      route: '/dashboard',
      icon: 'home',
      label: 'Dashboard'
    },
    {
      route: '/incubator',
      icon: 'inbox',
      label: 'My Incubator'
    },
    {
      route: '/pets',
      icon: 'pets',
      label: 'My Pets'
    },
    {
      route: '/market',
      icon: 'store',
      label: 'Egg Market'
    },
    {
      route: '/templates',
      icon: 'add',
      label: 'Create A Template'
    }
  ]

  ngOnInit(): void {
    this.username = localStorage.getItem("username");
  }

  someMethod() {
    this.trigger.openMenu();
  }

  logout(){
    alert("Goodbye, " + this.username);
    this.userService.logoutUser();
  }

}
