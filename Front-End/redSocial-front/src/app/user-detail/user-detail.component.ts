import { Component, inject } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../interfaces/User';

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

  username?: string | null;

  user?: User

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

  }
 


}
