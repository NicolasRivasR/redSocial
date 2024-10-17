import { HttpClient, } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  storage = inject(LocalStorageService)

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

  private USERAPI: string = "http://localhost:8080/user"

  private HOMEAPI: string = "http://localhost:8080/home"

  usersFormBack: any[] = [];

  key = "Bearer " + this.storage.get('auth-key')


  public getUsers(): Observable<any[]>{

    return this.http.get<any[]>(this.HOMEAPI, { headers: { Accept: 'application/json' }});

  }
  getAllUsers(){

    return this.users;

  }

  getUserInfo(usernaeme: string){

    console.log("A ver si llego aqui")

    console.log("La clave es " + this.key)

    return this.http.get(this.USERAPI + "/info/name/" + usernaeme, { headers: { Authorization: this.key }})


  }

}
