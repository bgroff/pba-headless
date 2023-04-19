import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { CreateBakery } from './createBakery';


@Injectable({ providedIn: 'root' })
export class BakeryService {
    private bakeriesUrl = 'http://localhost:8080/bakeries/'
    constructor(
    private http: HttpClient,
    ){}
 
    /** GET heroes from the server */
  createBakery(id: number): Observable<CreateBakery> {
    console.log(this.bakeriesUrl);
    return this.http.post<CreateBakery>(this.bakeriesUrl + id, null);
  }
  handleError<T>(arg0: string, arg1: never[]): (err: any, caught: Observable<CreateBakery[]>) => import("rxjs").ObservableInput<any> {
    throw new Error(arg0);
  }


}