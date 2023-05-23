import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service'; 

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  message: string = '';

  constructor(public authService: AuthService, private alertService: AlertService) { }

  ngOnInit() {
    this.alertService.message.subscribe((message) => {
      this.message = message;
    });
  }

  logout() {
    this.authService.logoutUser();
  }

}