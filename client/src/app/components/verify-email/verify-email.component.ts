import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})

export class VerifyEmailComponent {

  emailForm!: FormGroup;

  constructor(private authService: AuthService) { }

  //Initialize a FormGroup with validation
  ngOnInit() {
    this.emailForm = new FormGroup({
      userEmail: new FormControl('', [ Validators.required, Validators.email ]),
    });
  }

  //Method that returns true if form input is not pristine and invalid
  invalidControl (ctrlName: string): boolean {
    const ctrl = this.emailForm.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //Send the email string to server and save the email in localStorage
  //Form data is cleared after 3min
  submitEmail() {
    const email = this.emailForm.value.userEmail;
    localStorage.setItem("userEmail", email)
    this.authService.verifyEmail(email);
    setTimeout(() => {
      this.emailForm.reset();
      localStorage.removeItem("userEmail");
    }, 180000);
  }

}