import { Routes,RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { LogInComponent } from './log-in/log-in.component';
import { RegisterComponent } from './register/register.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

export const routes: Routes = [


    {
        path: '',
        component: HomeComponent
    },
    {
        path:'login',
        component: LogInComponent
    },
    {
        path:'register',
        component: RegisterComponent
    },
    {
        path:'userDetail/:username',
        component: UserDetailComponent
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }