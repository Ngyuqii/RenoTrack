import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-unsplash',
  templateUrl: './search-unsplash.component.html',
  styleUrls: ['./search-unsplash.component.css']
})

export class SearchUnsplashComponent implements OnInit {

  form!: FormGroup;
  
  constructor(private fb: FormBuilder, private router: Router) {}

  //Initialize a FormGroup with validation
  ngOnInit(): void {
    this.form = this.fb.group({
      search: this.fb.control("", [ Validators.required ])
    });
  }

  //Search button disabled if user keys in blank space
  invalidEntry(): boolean {
    const formEntry = this.form.value.search.trim();
    return this.form.invalid || formEntry.length<1;
  }

  //Retrieve form value
  //Nagivate user to unsplash/{search} page
  searchImages(): void {
    const unsplashResult = this.form.value.search;
    console.info(`>>>Inputs: ${unsplashResult}`);
    this.router.navigate(['/unsplash', unsplashResult]);
  }

}