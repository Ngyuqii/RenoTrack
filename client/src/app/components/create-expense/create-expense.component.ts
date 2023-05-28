import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ExpenseService } from 'src/app/services/expense.service';

@Component({
  selector: 'app-create-expense',
  templateUrl: './create-expense.component.html',
  styleUrls: ['./create-expense.component.css']
})

export class CreateExpenseComponent implements OnInit {

  createExpenseForm!: FormGroup;
  userId = localStorage.getItem('userId') || "";
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

  constructor(private formBuilder: FormBuilder, private expenseService: ExpenseService) { }

  ngOnInit(): void {
    this.createExpenseForm = this.formBuilder.group({
      date: ['', Validators.required],
      category: ['', Validators.required],
      business: ['', Validators.required],
      description: ['', Validators.required],
      amount: ['', Validators.required],
      payment: ['', Validators.required]
    });
  }

  onSubmit() {
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
  }  

}