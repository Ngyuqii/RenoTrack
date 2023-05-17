import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LookbookComponent } from './components/lookbook/lookbook.component';
import { MoodboardComponent } from './components/moodboard/moodboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LookbookComponent,
    MoodboardComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }