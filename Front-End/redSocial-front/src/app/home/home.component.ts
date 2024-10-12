import { Component, inject } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  userService = inject(UserService);

  users:any[] = [];

  fetchUsers(): void {
    this.userService.getUsers().subscribe((data: any[]) => {
      this.users = data;
    });
  }


}
