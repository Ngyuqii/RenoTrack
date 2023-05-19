import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayUnsplashComponent } from './components/display-unsplash/display-unsplash.component';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  {path:'', component: MainComponent}, //localhost:4200/
  {path:'unsplash/:search', component: DisplayUnsplashComponent}, //localhost:4200/unsplash/{search}
  {path: '**', redirectTo: '/', pathMatch:"full"} //redirect to localhost:4200
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }