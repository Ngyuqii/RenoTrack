import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class GoogleMapsService {
  
  private promise!: Promise<void>;

  public load(): Promise<void> {
    if (this.promise) {
      return this.promise;
    }

    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.async = true;
    script.defer = true;
    script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAkqmbpY6D5aEuHRHaqQ0JgCWpEkhZhEzE`;

    this.promise = new Promise<void>(resolve => {
      script.onload = (): void => {
        resolve();
      };
    });

    document.body.appendChild(script);
    return this.promise;
  }

}
