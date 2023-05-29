import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  //Initialize a FormGroup with validation
  ngOnInit() {
    this.loginForm = this.fb.group({
      userEmail: this.fb.control('', [ Validators.required, Validators.email ]),
      userPassword: this.fb.control('', [ Validators.required, Validators.minLength(8) ])
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