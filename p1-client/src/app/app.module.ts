import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

import { AuthService } from './services/auth.service';
import { AuthGuard } from './services/auth.guard';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserAccessComponent } from './components/user-access/user-access.component';
import { UserChoiceComponent } from './components/user-choice/user-choice.component';
import { AddReimbursementComponent } from './components/user-choice/add-reimbursement/add-reimbursement.component';
import { ViewReimbursementComponent } from './components/user-choice/view-reimbursement/view-reimbursement.component';
import { ViewAllReimbursementComponent } from './components/user-choice/view-all-reimbursement/view-all-reimbursement.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    UserAccessComponent,
    UserChoiceComponent,
    AddReimbursementComponent,
    ViewReimbursementComponent,
    ViewAllReimbursementComponent,
    NavigationComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
