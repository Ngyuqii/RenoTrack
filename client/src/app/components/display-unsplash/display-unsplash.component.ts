import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Unsplash } from 'src/app/models';
import { UnsplashService } from 'src/app/services/unsplash.service';

@Component({
  selector: 'app-display-unsplash',
  templateUrl: './display-unsplash.component.html',
  styleUrls: ['./display-unsplash.component.css']
})

export class DisplayUnsplashComponent implements OnInit, OnDestroy {

  search = "";
  param$!: Subscription;

  unsplashImg$!: Promise<Unsplash[]>;

  constructor(private unsplashSvc: UnsplashService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    //Read path variable {search}
    this.param$ = this.activatedRoute.params.subscribe(
      params => {
        this.search= params["search"];
      }
    );

    //Get splash images and subscribe to the onSearchUnsplash observable in unsplashSvc
    this.unsplashSvc.getUnsplashImages(this.search);
    this.unsplashSvc.onSearchUnsplash.subscribe(
      p => {
         console.info(">>> Subscribing to server.");
        this.unsplashImg$ = p;
      }
    )

  }

  //Unsubscribe to observable
  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }

}