import { HttpClient, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  users = [

    {
      "id": 0,
      "username": "john_doe1",
      "mail": "johndoe2@example.com",
      "password": "contraseña",
      "firstName": "John",
      "secondName": "Doe",
      "bio": "Software engineer with a passion for technology.",
      "profilePicture": "https://example.com/profile/johndoe.jpg",
      "role": "USER"
    }

  ]

  usersFormBack: any[] = [];

  public getUsers(): Observable<any[]>{

    return this.http.get<any[]>("http://localhost:8080/home", { headers: { Accept: 'application/json' }});

  }
  getAllUsers(){

    return this.users;

  }

}
