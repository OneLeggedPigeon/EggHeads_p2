import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module'


import { LoginComponent } from './components/forms/login/login.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TemplatesComponent } from './components/pages/templates/templates.component';
import { IncubatorComponent } from './components/pages/incubator/incubator.component';
import { PetsComponent } from './components/pages/pets/pets.component';
import { UserService } from './services/user.service';
import { EggTemplateComponent } from './components/objects/egg_template/egg_template.component';
import { EggComponent } from './components/objects/egg/egg.component';
import { HomeComponent } from './components/pages/home/home.component';
import { ContributorsComponent } from './components/contributors/contributors.component';
import { MarketComponent } from './components/pages/market/market.component';
import { MarketEggComponent } from './components/objects/market-egg/market-egg.component';
import { IncubatorEggComponent } from './components/objects/incubator-egg/incubator-egg.component';
import { NewTemplateComponent } from './components/forms/new-template/new-template.component';
import { IncubatorDashboardComponent } from './components/pages/dashboard/incubator-dashboard/incubator-dashboard.component';
import { PetDashboardComponent } from './components/pages/dashboard/pet-dashboard/pet-dashboard.component';
import { MarketDashboardComponent } from './components/pages/dashboard/market-dashboard/market-dashboard.component';
import { TemplateDashboardComponent } from './components/pages/dashboard/template-dashboard/template-dashboard.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';
// import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NavbarComponent,
    TemplatesComponent,
    IncubatorComponent,
    PetsComponent,
    EggTemplateComponent,
    EggComponent,
    HomeComponent,
    ContributorsComponent,
    MarketComponent,
    MarketEggComponent,
    IncubatorEggComponent,
    NewTemplateComponent,
    IncubatorDashboardComponent,
    PetDashboardComponent,
    MarketDashboardComponent,
    TemplateDashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    // HttpClientTestingModule,
  ],
  providers: [UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
