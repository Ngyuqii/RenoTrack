import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Event } from '../models';

const Scheduler_URL = 'http://localhost:8080/api/events';
//Railway
//const Scheduler_URL = "https://railway.app/api/events"

@Injectable({
  providedIn: 'root'
})

export class EventService {

  constructor(private http: HttpClient) { }

  getEvents(userId: string): Observable<Event[]> {
    return this.http.get(`${Scheduler_URL}/${userId}`, {responseType: 'text'})
      .pipe(map((data: string) => JSON.parse(data).Events));
  }
  
  createEvent(userId: string, event: Event): Observable<Object> {
    return this.http.post(`${Scheduler_URL}/${userId}`, event);
  }
  
  updateEvent(userId: string, eventId: number, event: Event): Observable<Object> {
    return this.http.put(`${Scheduler_URL}/${userId}/${eventId}`, event);
  }

  deleteEvent(userId: string, eventId: number): Observable<any> {
    return this.http.delete(`${Scheduler_URL}/${userId}/${eventId}`);
  }

}