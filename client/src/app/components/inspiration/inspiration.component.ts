import { Component, OnInit } from '@angular/core';
import { InspoService } from 'src/app/services/inspo.service';
import { Inspiration } from 'src/app/models';

@Component({
  selector: 'app-inspiration',
  templateUrl: './inspiration.component.html',
  styleUrls: ['./inspiration.component.css']
})

export class InspirationComponent implements OnInit {

  userId = localStorage.getItem('userId') || "";
  inspo!: Inspiration[];

  constructor(private inspoSvc: InspoService) { }

  ngOnInit(): void {
    //Retrieve all images
    this.inspoSvc.getInspo(this.userId).subscribe(inspos => this.inspo = inspos);
  }

  //Delete selected image
  deleteInspo(inspoId: number): void {
    this.inspoSvc.deleteInspo(inspoId).subscribe(() => {
      this.inspo = this.inspo.filter(inspo => inspo.inspoId !== inspoId);
    });
  }

}