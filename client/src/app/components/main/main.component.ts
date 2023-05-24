import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service'; 

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  enteredCode!: string;
  message: string = '';

  constructor(public authService: AuthService, private alertService: AlertService) { }

  ngOnInit() {
    this.alertService.message.subscribe((message) => {
      this.message = message;
    });
  }

  //Check if user entered code is equal to system generated code
  matchCode() {
    const otpCode = localStorage.getItem("code");
    
    if (this.enteredCode != otpCode) {
      this.enteredCode = "";
      this.alertService.setMessage("OTP does not match");
    }
    else {
      this.enteredCode = "";
      localStorage.setItem("code", "verified")
      this.alertService.setMessage("Email verified");
      //Data to be cleared after 3min
      setTimeout(() => {
        this.clearData();
      }, 180000);
    }
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

  logout() {
    this.authService.logoutUser();
  }

}