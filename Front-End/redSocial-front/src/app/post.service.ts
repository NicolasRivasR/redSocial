import { HttpClient, } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  storage = inject(LocalStorageService)

  private POSTSAPI: string = "http://localhost:8080/user/posts/"

  key = "Bearer " + this.storage.get('auth-key')


  getPostsFromUser(usernaeme: string){

    console.log(this.key)

    console.log("llamo al api")

    return this.http.get(this.POSTSAPI + usernaeme, { headers: { Authorization: this.key }})


  }


}
