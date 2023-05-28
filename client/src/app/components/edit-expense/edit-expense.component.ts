import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Expense } from 'src/app/models';
import { ExpenseService } from 'src/app/services/expense.service';

@Component({
  selector: 'app-edit-expense',
  templateUrl: './edit-expense.component.html',
  styleUrls: ['./edit-expense.component.css']
})

export class EditExpenseComponent implements OnInit {

  editExpenseForm!: FormGroup;
  userId = localStorage.getItem('userId') || "";
  expense!: Expense;
  categories: string[] = [
    'Architectural/Design Service',
    'Hacking',
    'Masonry',
    'Carpentry',
    'Ceiling and Partition',
    'Lighting',
    'Painting and Wallpaper',
    'Window Furnishing',
    'Electrical Wiring',
    'Plumbing',
    'Furniture',
    'Electrical Appliances',
    'Miscellaneous'
  ];

  constructor(private formBuilder: FormBuilder, private expenseService: ExpenseService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.editExpenseForm = this.formBuilder.group({
      date: ['', Validators.required],
      category: ['', Validators.required],
      business: ['', Validators.required],
      description: ['', Validators.required],
      amount: ['', Validators.required],
      payment: ['', Validators.required]
    });

    let expenseId = this.route.snapshot.params['id'];

    this.expenseService.getExpense(this.userId, expenseId).subscribe((data: any) => {
       this.expense = data.Expense;

       this.editExpenseForm = this.formBuilder.group({
        date: [this.expense.date, Validators.required],
        category: [this.expense.category, Validators.required],
        business: [this.expense.business, Validators.required],
        description: [this.expense.description, Validators.required],
        amount: [this.expense.amount, Validators.required],
        payment: [this.expense.payment, Validators.required]
        });
    });   
     
  }

  onSubmit() {
    let expenseId = this.route.snapshot.params['id'];
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
  }

}
