import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Expense } from '../models';

//const Expense_URL = "/api/expenses"
const Expense_URL = "http://localhost:8080/api/expenses"

@Injectable({
  providedIn: 'root'
})

export class ExpenseService {

  constructor(private http: HttpClient) { }

  //Retrieve list of expenses for a userId
  getExpenses(userId: string): Observable<Expense[]> {
    return this.http.get(`${Expense_URL}/${userId}`).pipe(
      map((data: any) => data.Expenses)
    );
  }

  //Retrieve an expense of expenseId
  getExpense(userId: string, expenseId: number): Observable<Expense> {
    return this.http.get<Expense>(`${Expense_URL}/${userId}/${expenseId}`);
  }

  //Create a new expense for a userId
  createExpense(userId: string, expense: Expense): Observable<Expense> {
    return this.http.post<Expense>(`${Expense_URL}/create/${userId}`, expense);
  }

  //Update an existing expense of a expenseId
  updateExpense(expenseId: number, expense: Expense): Observable<Expense> {
    return this.http.put<Expense>(`${Expense_URL}/update/${expenseId}`, expense);
  }

  //Delete an existing expense of a expenseId
  deleteExpense(expenseId: number): Observable<any> {
    return this.http.delete<any>(`${Expense_URL}/delete/${expenseId}`);
  }

  //Get sum of amount for a userId
  getSumAmount(userId: string): Observable<number> {
    return this.http.get<number>(`${Expense_URL}/${userId}/amountsum`);
  }

  //Get sum of payment for a userId
  getSumPayment(userId: string): Observable<number> {
    return this.http.get<number>(`${Expense_URL}/${userId}/paymentsum`);
  }

  //Get sum of balance for a userId
  getSumBalance(userId: string): Observable<number> {
    return this.http.get<number>(`${Expense_URL}/${userId}/balancesum`);
  }

  //Get sum of amount per category for a userId
  getSumAmountPerCategory(userId: string): Observable<any> {
    return this.http.get<any>(`${Expense_URL}/${userId}/amountsum/category`).pipe(
      map((data: any) => data.Expenses));
  }

}