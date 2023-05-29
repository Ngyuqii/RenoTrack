import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service'; 

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  email!: string;
  enteredCode!: string;
  message: string = '';

  constructor(public authService: AuthService, private alertService: AlertService) { }

  //Subscribe to the message observable from the alertService
  ngOnInit() {
    this.alertService.message.subscribe((message) => {
      this.message = message;
    });
  }

  //Check if user entered code is equal to system generated code
  matchCode() {
    this.email = this.authService.getUserEmail();
    this.authService.verifyEmailOTP(this.email, this.enteredCode);
    this.enteredCode = "";
  }

  isEmailEntered() {
    return localStorage.getItem('userEmail') != null;
  }
 
  isCodeMatch(){
    return localStorage.getItem("code") == "verified";
  }

  clearData(){
    localStorage.removeItem("userEmail");
    localStorage.removeItem("code");
  }

}