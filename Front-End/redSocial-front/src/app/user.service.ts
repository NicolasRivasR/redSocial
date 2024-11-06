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



  private USERAPI: string = "http://localhost:8080/user"

  private HOMEAPI: string = "http://localhost:8080/home"

  usersFormBack: any[] = [];

  key = "Bearer " + this.storage.get('auth-key')


  public getUsers(): Observable<any[]>{

    return this.http.get<any[]>(this.HOMEAPI, { headers: { Accept: 'application/json' }});

  }

  getUserInfo(usernaeme: string){

    return this.http.get(this.USERAPI + "/info/name/" + usernaeme, { headers: { Authorization: this.key }})

  }

}
