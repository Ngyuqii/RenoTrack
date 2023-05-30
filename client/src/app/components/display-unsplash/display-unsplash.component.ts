import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Inspiration, Unsplash } from 'src/app/models';
import { AlertService } from 'src/app/services/alert.service';
import { InspoService } from 'src/app/services/inspo.service';
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
  
  userId = localStorage.getItem('userId') || "";
  message: string = '';

  constructor(private unsplashSvc: UnsplashService, private inspoSvc: InspoService, private alertSvc: AlertService, 
    private activatedRoute: ActivatedRoute) { }

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

    //Subscribe to the message observable from the alertService
    this.alertSvc.message.subscribe((message) => {
      this.message = message;
    });

  }

  //Save imageUrl to database
  saveInspo(imageUrl: string): void {
    let newInspo: Inspiration = {
      inspoId: 0,
      imageUrl: imageUrl
    };
    this.inspoSvc.createInspo(this.userId, newInspo).subscribe();
    this.alertSvc.setMessage("Image added to your collection!");
  }

  //Unsubscribe to observable
  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }
  
}