import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Expense } from 'src/app/models';
import { ExpenseService } from 'src/app/services/expense.service';

@Component({
  selector: 'app-edit-expense',
  templateUrl: './edit-expense.component.html',
  styleUrls: ['./edit-expense.component.css']
})

export class EditExpenseComponent implements OnInit {

  userId = localStorage.getItem('userId') || "";
  editExpenseForm!: FormGroup;
  expense!: Expense;
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

  constructor(private fb: FormBuilder, private expenseService: ExpenseService, private router: Router) { }

  //Initialize a FormGroup with validation
  //Input expense record of expenseId
  ngOnInit(): void {
    this.editExpenseForm = this.fb.group({
      date: this.fb.control('', [ Validators.required ]),
      category: this.fb.control('', [ Validators.required ]),
      business: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
      description: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
      amount: this.fb.control('', [ Validators.required, Validators.min(0.01) ]),
      payment: this.fb.control('', [ Validators.required, Validators.min(0)])
    });

    let expenseId = this.expenseService.getExpenseId();
    this.expenseService.getExpense(this.userId, expenseId).subscribe((data: any) => {
      this.expense = data.Expense;
    
      this.editExpenseForm = this.fb.group({
        date: this.fb.control(this.expense.date, [ Validators.required ]),
        category: this.fb.control(this.expense.category, [ Validators.required ]),
        business: this.fb.control(this.expense.business, [ Validators.required, Validators.minLength(2) ]),
        description: this.fb.control(this.expense.description, [ Validators.required , Validators.minLength(2) ]),
        amount: this.fb.control(this.expense.amount, [ Validators.required, Validators.min(0.01) ]),
        payment: this.fb.control(this.expense.payment, [ Validators.required, Validators.min(0) ])
      });
    });
  }

  //Method that returns true if form input is not pristine and invalid
  invalidControl (ctrlName: string): boolean {
    const ctrl = this.editExpenseForm.get(ctrlName) as FormControl;
    return ctrl.invalid && (!ctrl.pristine);
  }

  //The amount paid cannot be more than expense
  paymentLogic(): boolean {
    return this.editExpenseForm.value.payment > this.editExpenseForm.value.amount;
  }

  //Search button disabled if validators not met or the amount paid is more than expense
  invalidEntry(): boolean {
    return this.editExpenseForm.invalid || this.paymentLogic(); 
  }

  //Update expense record
  //Form data is cleared and return back to expense page
  updateExpense() {
    let expenseId = this.expenseService.getExpenseId();
    let balance = this.editExpenseForm.value.amount - this.editExpenseForm.value.payment;
    let updatedExpense: Expense = { ...this.editExpenseForm.value, balance: balance };

    this.expenseService.updateExpense(expenseId, updatedExpense).subscribe({
      next: response => {
        console.log(response);
      },
      error: error => {
        console.log(error);
      }
    });
    this.editExpenseForm.reset();
    this.router.navigate(['/expensetracker']);
  }

}