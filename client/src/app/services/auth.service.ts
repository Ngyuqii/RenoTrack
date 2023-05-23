import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { AlertService } from './alert.service';
import { User } from '../models';

const apiUrl = "http://localhost:8080/api";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient, private alertService: AlertService) { }

  registerUser(user: User) {
    console.log(">>>Registration Details", user);
    firstValueFrom(this.http.post<any>(`${apiUrl}/register`, user)).then(response => {
      this.alertService.setMessage(response.Msg);
    }).catch(error => {
      this.alertService.setMessage(error.toString());
    });
  }

  loginUser(user: User) {
    console.log(">>>Login Details", user);
    firstValueFrom(this.http.post<any>(`${apiUrl}/login`, user)).then(response => {
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