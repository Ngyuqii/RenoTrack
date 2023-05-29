import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ExpenseService } from 'src/app/services/expense.service';

@Component({
  selector: 'app-create-expense',
  templateUrl: './create-expense.component.html',
  styleUrls: ['./create-expense.component.css']
})

export class CreateExpenseComponent implements OnInit {

  userId = localStorage.getItem('userId') || "";
  createExpenseForm!: FormGroup;
  categories: string[] = [
    'Architectural / Design',
    'Carpentry',
    'Ceiling and Partition',
    'Electrical Appliances',
    'Electrical Wiring',
    'Furniture',
    'Hacking',
    'Lighting',
    'Masonry',
    'Miscellaneous',
    'Painting and Wallpaper',
    'Plumbing',
    'Window Furnishing'
  ];

  constructor(private fb: FormBuilder, private expenseService: ExpenseService) { }

  //Initialize a FormGroup with validation
  ngOnInit(): void {
    this.createExpenseForm = this.fb.group({
      date: this.fb.control('', [ Validators.required ]),
      category: this.fb.control('', [ Validators.required ]),
      business: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
      description: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
      amount: this.fb.control('', [ Validators.required, Validators.min(0.01) ]),
      payment: this.fb.control('', [ Validators.required, Validators.min(0)])
    });
  }

  //Method that returns true if form input is not pristine and invalid
  invalidControl (ctrlName: string): boolean {
    const ctrl = this.createExpenseForm.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //The amount paid cannot be more than expense
  paymentLogic(): boolean {
    return this.createExpenseForm.value.payment > this.createExpenseForm.value.amount;
  }

  //Search button disabled if validators not met or the amount paid is more than expense
  invalidEntry(): boolean {
    return this.createExpenseForm.invalid || this.paymentLogic(); 
  }

  //Calculate balance and send the form values to database
  //Form data is cleared and page reload
  createExpense() {
    let balance = this.createExpenseForm.value.amount - this.createExpenseForm.value.payment;
    let newExpense = { ...this.createExpenseForm.value, balance: balance };
    console.log('>>>New expense:', newExpense);
  
    this.expenseService.createExpense(this.userId, newExpense).subscribe({
      next: response => {
        console.log(response);
        console.log('>>>Create expense', response);
      },
      error: error => {
        console.log(error);
      }
    });
    this.createExpenseForm.reset();
    window.location.reload();
  }  

}