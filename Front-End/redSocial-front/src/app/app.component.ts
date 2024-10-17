import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LocalStorageService } from './local-storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'redSocial-front';

  localStorageService = inject(LocalStorageService)

  logOut(){
    
    this.localStorageService.remove('auth-key');

  }

}
