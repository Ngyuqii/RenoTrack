import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Subject, firstValueFrom } from 'rxjs';
import { Unsplash } from '../models';

const Unsplash_URL = "/api/unsplash"
//const Unsplash_URL = "http://localhost:8080/api/unsplash"

@Injectable({
  providedIn: 'root'
})

export class UnsplashService {

  constructor(private http: HttpClient) { }

  onSearchUnsplash = new Subject<Promise<Unsplash[]>>();

  //Method to make a HTTP GET request to the server
  //Returns a Promise of an array of object
  getUnsplashImages(search: string): Promise<Unsplash[]> {

    console.info(`>>>Unsplash search: ${search}`);

    const params = new HttpParams()
        .set("query", search);
    
    //Method to retrieve data and cast to Unsplash[]
    //Emit the data to subscribers of onSearchUnsplash subject
    return firstValueFrom(
      this.http.get<Unsplash[]>(Unsplash_URL, { params })
    )
    .then(result => {
      console.info(">>> Response: ", result);
      const a = result as Unsplash[];
      this.onSearchUnsplash.next(Promise.resolve(a));
      return a;
    })

  }

}