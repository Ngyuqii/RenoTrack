import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      userEmail: new FormControl('', [Validators.required, Validators.email]),
      userPassword: new FormControl('', Validators.required)
    });
  }

  login() {
    if (this.loginForm.valid) {
      this.authService.loginUser(this.loginForm.value);
      setTimeout(() => {
        this.loginForm.reset();
      }, 3000);
    }
  }

}