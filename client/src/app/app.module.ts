import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LookbookComponent } from './components/lookbook/lookbook.component';
import { MoodboardComponent } from './components/moodboard/moodboard.component';
import { MapComponent } from './components/map/map.component';
import { SearchUnsplashComponent } from './components/search-unsplash/search-unsplash.component';
import { DisplayUnsplashComponent } from './components/display-unsplash/display-unsplash.component';
import { GoogleMapsService } from './services/google-maps.service';
import { MainComponent } from './components/main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    LookbookComponent,
    MoodboardComponent,
    MapComponent,
    SearchUnsplashComponent,
    DisplayUnsplashComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [GoogleMapsService],
  bootstrap: [AppComponent]
})

export class AppModule { }