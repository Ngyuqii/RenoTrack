import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AlertService {

  constructor() { }

  public message = new Subject<string>();

  //Set alert message and clear the message after 3s
  setMessage(value: string,) {
    this.message.next(value);
    setTimeout(() => {
      this.clearMessage();
    }, 3000);
  }

  clearMessage() {
    this.message.next('');
  }

}