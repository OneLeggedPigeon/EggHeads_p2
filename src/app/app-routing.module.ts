import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/forms/login/login.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { HomeComponent } from './components/pages/home/home.component';
import { IncubatorComponent } from './components/pages/incubator/incubator.component';
import { PetsComponent } from './components/pages/pets/pets.component';
import { TemplatesComponent } from './components/pages/templates/templates.component';
import { MarketComponent } from './components/pages/market/market.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login'},
  { path: 'login', component: HomeComponent},
  { path: 'dashboard', component: DashboardComponent, canActivate:[AuthGuardService]},
  { path: 'templates', component: TemplatesComponent, canActivate:[AuthGuardService]},
  { path: 'incubator', component: IncubatorComponent, canActivate:[AuthGuardService]},
  { path: 'pets', component: PetsComponent, canActivate:[AuthGuardService]},
  { path: 'market', component: MarketComponent, canActivate:[AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
