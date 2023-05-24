import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { LookbookComponent } from './components/lookbook/lookbook.component';
import { MoodboardComponent } from './components/moodboard/moodboard.component';
import { MapComponent } from './components/map/map.component';
import { SearchUnsplashComponent } from './components/search-unsplash/search-unsplash.component';
import { DisplayUnsplashComponent } from './components/display-unsplash/display-unsplash.component';
import { VerifyEmailComponent } from './components/verify-email/verify-email.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

import { GoogleMapsService } from './services/google-maps.service';
import { AuthService } from './services/auth.service';
import { AlertService } from './services/alert.service';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    LookbookComponent,
    MoodboardComponent,
    MapComponent,
    SearchUnsplashComponent,
    DisplayUnsplashComponent,
    VerifyEmailComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    GoogleMapsService,
    AuthService,
    AlertService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }