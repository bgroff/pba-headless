import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { Bakery } from './bakery';


@Injectable({ providedIn: 'root' })
export class BakeryService {
    private bakeriesUrl = 'http://localhost:8080/bakeries'
    constructor(
    private http: HttpClient,
    ){}
 
    /** GET heroes from the server */
  getBakeries(): Observable<Bakery[]> {
    console.log(this.bakeriesUrl);
    return this.http.get<Bakery[]>(this.bakeriesUrl);
    return this.http.get<Bakery[]>(this.bakeriesUrl)
      .pipe(
        tap(_ => console.log(_)),
        catchError(this.handleError<Bakery[]>('getHeroes', []))
      );
  }
  handleError<T>(arg0: string, arg1: never[]): (err: any, caught: Observable<Bakery[]>) => import("rxjs").ObservableInput<any> {
    throw new Error(arg0);
  }


}