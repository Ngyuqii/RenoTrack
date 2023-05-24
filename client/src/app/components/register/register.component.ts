import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(private authService: AuthService) { }

  //Initialize a FormGroup with validation
  ngOnInit() {
    this.registerForm = new FormGroup({
      userName: new FormControl('', [ Validators.required, Validators.minLength(2) ]),
      userEmail: new FormControl('', [ Validators.required, Validators.email ]),
      userPassword: new FormControl('', [ Validators.required, Validators.minLength(8) ]),
      confirmPassword: new FormControl('', [ Validators.required, Validators.minLength(8) ])
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
    return passA.trim().length > 0 && passB.trim().length > 0 && passA != passB;  
  }

  //Search button disabled if validators not met or the 2 passwords do not match
  invalidEntry(): boolean {
    return this.registerForm.invalid || this.unmatchedPW(); 
  }

  //Send the form values as user to register a user account
  //Form data is cleared after 8s
  register() {
    if (this.registerForm.valid) {
      this.authService.registerUser(this.registerForm.value);
      setTimeout(() => {
        this.registerForm.reset();
      }, 8000);
    }
  }

}