import { Component, OnInit } from '@angular/core';
import { View } from '@syncfusion/ej2-angular-schedule';
import { Event } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./scheduler.component.css']
})

export class SchedulerComponent implements OnInit {

  public setView: View = "Month"

  public data!: Event[];  // the events displayed on the scheduler
  public userId!: string;

  constructor(private eventService: EventService) { }

  //Set userId and load the events data
  ngOnInit() {
    this.userId = localStorage.getItem("userId") || '';
    this.reloadData();
  }

  //Update the events
  reloadData() {
    this.eventService.getEvents(this.userId)
      .subscribe(data => {
        this.data = data;
      });
  }

  onActionBegin(args: { [key: string]: Object }): void {
    if (args['requestType'] === 'eventCreate' || args['requestType'] === 'eventChange') {
      let event: Event = args['data'] instanceof Array ? args['data'][0] : args['data'];
      let googleCalendarUrl = this.createGoogleCalendarUrl(event);
      event.googleCalendarUrl = googleCalendarUrl;

      if (args['requestType'] === 'eventCreate') {
        this.eventService.createEvent(this.userId, event).subscribe(() => this.reloadData());
        console.log(">>>Data", this.userId, event);
        //Open Google Calendar URL with entered data
        this.addToGoogleCalendar(event);
      }
      else if (args['requestType'] === 'eventChange') {
        let id = (args['data'] instanceof Array ? args['data'][0] : args['data']).id;
        this.eventService.updateEvent(this.userId, id, event).subscribe(() => this.reloadData());
        console.log(">>>Data", this.userId, id, event);
      }
    }
    else if (args['requestType'] === 'eventRemove') {
      let id = (args['data'] instanceof Array ? args['data'][0] : args['data']).id;
      this.eventService.deleteEvent(this.userId, id).subscribe(() => this.reloadData());
      console.log(">>>Data", this.userId, id);
    }
  }

  //Method to open Google Calendar URL in a new tab
  addToGoogleCalendar(event: Event) {
    window.open(event.googleCalendarUrl, '_blank');
  }

  //Create Google Calendar URL with dynamic data
  createGoogleCalendarUrl(event: Event): string {
    let eventTitle = encodeURIComponent(event.Subject);
    let eventStart = this.formatDate(event.StartTime);
    let eventEnd = this.formatDate(event.EndTime);
    let eventDescription = event.Description ? encodeURIComponent(event.Description) : '';
    let eventLocation = event.Location ? encodeURIComponent(event.Location) : '';

    return `https://www.google.com/calendar/render?action=TEMPLATE&text=${eventTitle}&dates=${eventStart}/${eventEnd}&details=${eventDescription}&location=${eventLocation}&sf=true&output=xml`;
  }

  formatDate(date: Date): string {
    if (date) {
      return date.toISOString().replace(/-|:|\.\d+/g, '');
    } else {
      return '';
    }
  }

}
