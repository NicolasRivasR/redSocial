import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { errorContext } from 'rxjs/internal/util/errorContext';
import { Logindata } from '../interfaces/Logindata';
import { LocalStorageService } from '../local-storage.service';
import { Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {

  authService = inject(AuthService)
  localStorageService = inject(LocalStorageService)
  router = inject(Router)

  logForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })


  tryLogIn(){

    console.log("Llamo al api")
    this.authService.logIn(this.logForm.value as Logindata).subscribe({
      next:(res) => {
        console.log(res);
        this.localStorageService.set('auth-key', res);
        this.router.navigateByUrl("/userDetail/" + this.logForm.value.username)
      }, error: (err) => {
        console.log(err);
        this.localStorageService.remove('auth-key');
      }
    });

  }

  
}
