import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/forms/login/login.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { IncubatorComponent } from './components/pages/incubator/incubator.component';
import { PetsComponent } from './components/pages/pets/pets.component';
import { TemplatesComponent } from './components/pages/templates/templates.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login'},
  { path: 'login', component: LoginComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'templates', component: TemplatesComponent},
  { path: 'incubator', component: IncubatorComponent},
  { path: 'pets', component: PetsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
