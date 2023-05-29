import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { AlertService } from './alert.service';
import { Router } from '@angular/router';
import { User } from '../models';

const SB_URL = "/api";
//const SB_URL = "http://localhost:8080/api";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient, private alertService: AlertService, private router: Router) { }

  //Method to make a HTTP POST request to the server
  //Return response message to be displayed as alert or verificationCode to be set in localStorage
  //OTP only valid for 3min
  verifyEmail(email: String) {
    console.log(">>>User Email", email);
    firstValueFrom(this.http.post<any>(`${SB_URL}/verify`, email)).then(response => {
      if (response.Msg == "Email already exists") {
          this.alertService.setMessage(response.Msg);
          localStorage.removeItem("userEmail");
      }
      else {
        this.alertService.setMessage("A verification code has been sent to your email");
        localStorage.setItem("code", response.Msg);
        setTimeout (() => {
          localStorage.removeItem("code");
        }, 180000);
      }
    }).catch(error => {
      console.log(error);
    });
  }

  //Method to make a HTTP POST request to the server
  //Return response message to be displayed as alert
  registerUser(user: User) {
    console.log(">>>Registration Details", user);
    firstValueFrom(this.http.post<any>(`${SB_URL}/register`, user)).then(response => {
      this.alertService.setMessage(response.Msg);
    }).catch(error => {
      console.log(error);
    });
  }

  //Method to make a HTTP POST request to the server
  //Return response message to be displayed as alert or userId to be set in localStorage
  //User will be logged out after 30min
  loginUser(user: User) {
    console.log(">>>Login Details", user);
    firstValueFrom(this.http.post<any>(`${SB_URL}/login`, user)).then(response => {
      if (response.Msg == "Authentication failed. Please try again."
        || response.Msg == "Email not found. Please register your account.") {
          this.alertService.setMessage(response.Msg);
      }
      else {
        this.alertService.setMessage("Welcome back!");
        localStorage.setItem('userId', response.Msg);
        setTimeout(() => {
          localStorage.removeItem('userId');
          this.router.navigate(['']);
        }, 1800000);
      }
    }).catch(error => {
      console.log(error);
    });
  }

  isAuthenticated() {
    return localStorage.getItem('userId') != null;
  }

  logoutUser() {
    localStorage.removeItem('userId');
  }
 
}