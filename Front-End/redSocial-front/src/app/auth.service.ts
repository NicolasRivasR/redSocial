import { Injectable } from '@angular/core';
import { HttpClient, } from '@angular/common/http';
import { Logindata } from './interfaces/Logindata';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private APIURL: string = "http://localhost:8080/authenticate";

  private apip: string = "http://localhost:8080/geeet"

  logIn(logindata : Logindata){

    console.log("Llamo al api 2")
    return this.http.post<{ token: string }>(this.APIURL, logindata) .pipe(
      // Recoge solo el token de la respuesta
      map(response => response.token));

  }

  
}
