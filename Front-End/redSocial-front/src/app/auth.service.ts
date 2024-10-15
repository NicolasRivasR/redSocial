import { Injectable } from '@angular/core';
import { HttpClient, } from '@angular/common/http';
import { Observable, retry } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  logIn(username: string | null | undefined, password: string | null | undefined): Observable<any>{

    return this.http.post<any>("http://localhost:8080/login", {username, password});

  }

  
}
