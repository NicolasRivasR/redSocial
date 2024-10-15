import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RegsitrationService } from '../regsitration.service';
import { RegisterData } from '../interfaces/Registerdata';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  private regService = inject(RegsitrationService)

  registerForm = new FormGroup({
    
    username: new FormControl(''),
    mail : new FormControl(''),
    firstName: new FormControl(''),
    secondName: new FormControl(''),
    bio: new FormControl(''),
    profilePicture: new FormControl(''),
    password: new FormControl(''),
    confPassword: new FormControl(''),
    role: new FormControl('USER')

  }
  )

  onRegister(){
    const data = this.registerForm.value
    delete data.confPassword
    console.log(this.registerForm.value)
    this.regService.registerUser(data as RegisterData).subscribe({
      next:(res) => {
        console.log(res);
      }, error: (err) => {
        console.log("Error Received Response:"+err);
      }
    });

  }

}
