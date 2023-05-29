import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  //Initialize a FormGroup with validation
  ngOnInit(): void {
    const email = localStorage.getItem("userEmail");

    this.registerForm = this.fb.group({
      userName: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
      userEmail: this.fb.control(email),
      userPassword: this.fb.control('', [ Validators.required, Validators.minLength(8) ]),
      confirmPassword: this.fb.control('', [ Validators.required, Validators.minLength(8) ])
    });
  }

  //Method that returns true if form input is not pristine and invalid
  invalidControl (ctrlName: string): boolean {
    const ctrl = this.registerForm.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //The input passwords do not match
  unmatchedPW(): boolean {
    const passA = this.registerForm.value.userPassword;
    const passB = this.registerForm.value.confirmPassword;
    return passA != passB;  
  }

  //Search button disabled if validators not met or the 2 passwords do not match
  invalidEntry(): boolean {
    return this.registerForm.invalid || this.unmatchedPW(); 
  }

  //Send the form values as user to register a user account
  //Form data is cleared after 15s
  register() {
    this.authService.registerUser(this.registerForm.value);
    setTimeout(() => {
      this.registerForm.reset();
    }, 15000);
  }

}