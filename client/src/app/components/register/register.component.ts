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

  ngOnInit() {
    this.registerForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      userEmail: new FormControl('', [Validators.required, Validators.email]),
      userPassword: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required)
    });
  }

  //Search button disabled if the 2 passwords does not match
  invalidEntry(): boolean {
    const passA = this.registerForm.value.userPassword;
    const passB = this.registerForm.value.confirmPassword;
    return passA != passB;  
  }

  register() {
    if (this.registerForm.valid) {
      this.authService.registerUser(this.registerForm.value);
      setTimeout(() => {
        this.registerForm.reset();
      }, 3000);
    }
  }

}