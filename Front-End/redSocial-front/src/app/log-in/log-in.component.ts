import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { errorContext } from 'rxjs/internal/util/errorContext';
import { Logindata } from '../interfaces/Logindata';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {

  authService = inject(AuthService)

  logForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })


  tryLogIn(){

    console.log("Llamo al api")
    this.authService.logIn(this.logForm.value as Logindata).subscribe({
      next:(res) => {
        console.log(res);
      }, error: (err) => {
        console.log(err);
      }
    });

  }

  
}
