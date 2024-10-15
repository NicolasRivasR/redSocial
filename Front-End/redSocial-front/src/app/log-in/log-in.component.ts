import { Component, inject } from '@angular/core';
import { FormGroup, FormControl,ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { errorContext } from 'rxjs/internal/util/errorContext';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {

  authService = inject(AuthService)

  logInForm = new FormGroup({

    username: new FormControl(''),
    password: new FormControl('')

  })

  token?: String

  doLogIn(){

    const formValues = this.logInForm.value;

    if(formValues.username == '' || formValues.username == null || formValues.password == '' || formValues.password == null){

      alert("Wrong Credentials")
      return;

    }

    this.authService.logIn(formValues.username, formValues.password).subscribe({
      next:(res) => {
        console.log("Received Response:"+res.token);
        this.token = res.token;
      }, error: (err) => {
        console.log("Error Received Response:"+err);
        this.token = undefined ;
      }
    });

  }

}
