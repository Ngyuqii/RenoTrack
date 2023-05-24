import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { AlertService } from './alert.service';
import { User } from '../models';

const auth_URL = "http://localhost:8080/api";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient, private alertService: AlertService) { }

  //Method to make a HTTP POST request to the server
  //Return response message to be displayed as alert
  registerUser(user: User) {
    console.log(">>>Registration Details", user);
    firstValueFrom(this.http.post<any>(`${auth_URL}/register`, user)).then(response => {
      this.alertService.setMessage(response.Msg);
    }).catch(error => {
      this.alertService.setMessage(error.toString());
    });
  }

  //Method to make a HTTP POST request to the server
  //Return response message to be displayed as alert or userId to be set in localStorage
  loginUser(user: User) {
    console.log(">>>Login Details", user);
    firstValueFrom(this.http.post<any>(`${auth_URL}/login`, user)).then(response => {
      if (response.Msg == 'Authentication failed. Please try again.' 
        || response.Msg == 'Email not found. Please register your account.') {
          this.alertService.setMessage(response.Msg);
      }
      else {
        this.alertService.setMessage("Welcome back!");
        localStorage.setItem('userId', response.Msg);
      }
    }).catch(error => {
      this.alertService.setMessage(error.toString());
    });
  }

  logoutUser() {
    localStorage.removeItem('userId');
  }

  isAuthenticated() {
    return localStorage.getItem('userId') != null;
  }
  
}