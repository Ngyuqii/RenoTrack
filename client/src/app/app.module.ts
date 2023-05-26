import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ScheduleModule, RecurrenceEditorModule } from '@syncfusion/ej2-angular-schedule';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LookbookComponent } from './components/lookbook/lookbook.component';
import { MoodboardComponent } from './components/moodboard/moodboard.component';
import { MapComponent } from './components/map/map.component';
import { SearchUnsplashComponent } from './components/search-unsplash/search-unsplash.component';
import { DisplayUnsplashComponent } from './components/display-unsplash/display-unsplash.component';
import { VerifyEmailComponent } from './components/verify-email/verify-email.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { SchedulerComponent } from './components/scheduler/scheduler.component';

import { GoogleMapsService } from './services/google-maps.service';
import { AuthService } from './services/auth.service';
import { AlertService } from './services/alert.service';
import { EventService } from './services/event.service';
import { DayService, WeekService, MonthService, AgendaService } from '@syncfusion/ej2-angular-schedule';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavigationComponent,
    LookbookComponent,
    MoodboardComponent,
    MapComponent,
    SearchUnsplashComponent,
    DisplayUnsplashComponent,
    VerifyEmailComponent,
    RegisterComponent,
    LoginComponent,
    SchedulerComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ScheduleModule,
    RecurrenceEditorModule
  ],
  providers: [
    GoogleMapsService,
    AuthService,
    AlertService,
    EventService,
    DayService,
    WeekService,
    MonthService,
    AgendaService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }