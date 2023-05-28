import { Component, OnInit } from '@angular/core';
import { ExpenseService } from 'src/app/services/expense.service';
import { Expense } from 'src/app/models'; 
import { CategorySum } from 'src/app/models'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})

export class ExpenseComponent implements OnInit {

  userId = localStorage.getItem('userId') || "";
  expenses: Expense[] = [];
  categorySummaries: CategorySum[] = [];
  totalAmount: number = 0;
  totalPayment: number = 0;
  totalBalance: number = 0;

  constructor(private expenseService: ExpenseService, private router: Router) { }

  ngOnInit(): void {
    this.getExpenses();
    this.getCategorySummaries();
    this.getSumAmount();
    this.getSumPayment();
    this.getSumBalance();
  }

  getExpenses(): void {
    this.expenseService.getExpenses(this.userId).subscribe(expenses => {
      this.expenses = expenses;
    });
  }

  getCategorySummaries(): void {
    this.expenseService.getSumAmountPerCategory(this.userId).subscribe(expenses => {
      this.categorySummaries = expenses;
    });
  }

  getSumAmount(): void {
    this.expenseService.getSumAmount(this.userId).subscribe((data: any) => {
      this.totalAmount = data['Total Amount'];  
    });
  }
  
  getSumPayment(): void {
    this.expenseService.getSumPayment(this.userId).subscribe((data: any) => {
      this.totalPayment = data['Total Payment'];
    });
  }
  
  getSumBalance(): void {
    this.expenseService.getSumBalance(this.userId).subscribe((data: any) => {
      this.totalBalance = data['Total Balance'];
    });
  }
  
  onAddExpense(): void {
    this.router.navigate(['expensetracker/create']);
  }

  onEditExpense(expenseId: number): void {
    this.router.navigate(['expensetracker/edit', expenseId]);
  }

  onDeleteExpense(expenseId: number): void {
    this.expenseService.deleteExpense(expenseId).subscribe(() => {
      this.getExpenses();
      this.getCategorySummaries();
    });
  }

}