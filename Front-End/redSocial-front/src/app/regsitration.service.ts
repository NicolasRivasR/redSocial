import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { RegisterData } from './interfaces/Registerdata';

@Injectable({
  providedIn: 'root'
})
export class RegsitrationService {

  constructor(private http: HttpClient) { }

  private APIURL: string = "http://localhost:8080/register/user"

  registerUser(registerForm: RegisterData){

    return this.http.post(this.APIURL, registerForm)


  }
}
