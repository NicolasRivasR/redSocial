import { Component, inject } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../interfaces/User';
import { PostService } from '../post.service';
import { Post } from '../interfaces/Post';

@Component({
  selector: 'app-user-detail',
  standalone: true,
  imports: [],
  templateUrl: './user-detail.component.html',
  styleUrl: './user-detail.component.css'
})
export class UserDetailComponent {

  constructor( private route: ActivatedRoute ) { }

  userservice = inject(UserService)

  postservice = inject(PostService)

  username?: string | null;

  user?: User

  postslist?: [Post]

  ngOnInit(): void {

    this.username = this.route.snapshot.paramMap.get('username');
    this.userservice.getUserInfo(this.username as string).subscribe({
      next:(res) => {
        console.log(res);
        this.user = res as User
      }, error: (err) => {
        console.log(err);
      }
    }
    );

    this.postservice.getPostsFromUser(this.username as string).subscribe({
      next:(res) => {
        console.log(res);
        this.postslist = res as [Post]
      }, error: (err) => {
        console.log(err);
      }
    }
    );

  }
 


}
