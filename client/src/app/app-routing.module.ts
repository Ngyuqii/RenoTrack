import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayUnsplashComponent } from './components/display-unsplash/display-unsplash.component';
import { MainComponent } from './components/main/main.component';
import { SchedulerComponent } from './components/scheduler/scheduler.component';
import { ExpenseComponent } from './components/expense/expense.component';
import { CreateExpenseComponent } from './components/create-expense/create-expense.component';
import { EditExpenseComponent } from './components/edit-expense/edit-expense.component';

const routes: Routes = [
  {path:'', component: MainComponent}, //localhost:4200/
  {path:'unsplash/:search', component: DisplayUnsplashComponent}, //localhost:4200/unsplash/{search}
  {path:'scheduler', component: SchedulerComponent}, //localhost:4200/scheduler
  {path:'expensetracker', component: ExpenseComponent}, //localhost:4200/expensetracker
  {path:'expensetracker/edit', component: EditExpenseComponent }, //localhost:4200/expensetracker/edit
  {path: '**', redirectTo: '/', pathMatch:"full"} //redirect to localhost:4200
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }