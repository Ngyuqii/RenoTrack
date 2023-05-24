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

  //Initialize a FormGroup with validation
  ngOnInit() {
    this.loginForm = new FormGroup({
      userEmail: new FormControl('', [ Validators.required, Validators.email ]),
      userPassword: new FormControl('', [ Validators.required, Validators.minLength(8) ])
    });
  }

  //Method that returns true if form input is not pristine and invalid
  invalidControl (ctrlName: string): boolean {
    const ctrl = this.loginForm.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //Send the form values as user to login a user account
  //Form data is cleared after 15s
  login() {
    this.authService.loginUser(this.loginForm.value);
    setTimeout(() => {
      this.loginForm.reset();
    }, 15000);
  }

}