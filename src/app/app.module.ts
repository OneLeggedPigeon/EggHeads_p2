import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module'


import { LoginComponent } from './components/forms/login/login.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TemplatesComponent } from './components/pages/templates/templates.component';
import { IncubatorComponent } from './components/pages/incubator/incubator.component';
import { PetsComponent } from './components/pages/pets/pets.component';
import { UserService } from './services/user.service';
import { EggTemplateComponent } from './components/objects/egg_template/egg_template.component';
import { EggComponent } from './components/objects/egg/egg.component';

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
    EggComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
