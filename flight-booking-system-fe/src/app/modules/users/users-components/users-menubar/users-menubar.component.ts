import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users-menubar',
  templateUrl: './users-menubar.component.html',
  styleUrls: ['./users-menubar.component.scss']
})
export class UsersMenubarComponent implements OnInit {


  name:any;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.name = sessionStorage.getItem("userName");    
  }

  logout() {
    // localStorage.removeItem('isUserValid');
    // localStorage.removeItem('token');
    this.router.navigate(["users/users"]);
  }
  search(abc: string){
    console.log(abc)
  }
}
