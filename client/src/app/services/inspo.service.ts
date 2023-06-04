import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Inspiration } from '../models';

const Inspo_URL = "/api/inspirations"
// const Inspo_URL = "http://localhost:8080/api/inspirations"

@Injectable({
  providedIn: 'root'
})

export class InspoService {

  constructor(private http: HttpClient) { }

  //Retrieve list of inspo for a userId
  getInspo(userId: string): Observable<Inspiration[]> {
    return this.http.get(`${Inspo_URL}/${userId}`).pipe(
      map((data: any) => {
        if (data && data.Inspo) {
          return data.Inspo;
        }
        else {
          return [];
        }
      })
    );
  }

  //Create a new inspo for a userId
  createInspo(userId: string, inspo: Inspiration): Observable<Inspiration> {
    return this.http.post<Inspiration>(`${Inspo_URL}/like/${userId}`, inspo);
  }

  //Delete an existing inspo of inspoId
  deleteInspo(inspoId: number): Observable<any> {
    return this.http.delete<any>(`${Inspo_URL}/unlike/${inspoId}`);
  }

}