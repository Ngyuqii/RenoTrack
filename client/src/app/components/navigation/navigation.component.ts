import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent {

  constructor(public authService: AuthService, private router:Router) { }

  //Link route to scheduler component
  schedulerRoute(){
    console.log(">>>Navigating.");
    this.router.navigate(['/scheduler']);
  }

  //Link route to expense component
  expenseRoute(){
    console.log(">>>Navigating.");
    this.router.navigate(['/expensetracker']);
  }

  //UserId logout
  logout() {
    this.authService.logoutUser();
  }

}
