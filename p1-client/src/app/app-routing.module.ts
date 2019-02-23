import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessComponent } from './components/user-access/user-access.component';
import { UserChoiceComponent } from './components/user-choice/user-choice.component';
import { AddReimbursementComponent } from './components/user-choice/add-reimbursement/add-reimbursement.component';
import { ViewReimbursementComponent } from './components/user-choice/view-reimbursement/view-reimbursement.component';
import { ViewAllReimbursementComponent } from './components/user-choice/view-all-reimbursement/view-all-reimbursement.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'expense-reimbursement-system-login',
    pathMatch: 'full'
  },
  // { path: '**', component: PageNotFoundComponent },
  { path: 'expense-reimbursement-system-login', component: UserAccessComponent },
  { path: 'employee/options', component: UserChoiceComponent },
  { path: 'employee/options/expense-reimbursement', component: AddReimbursementComponent },
  { path: 'employee/options/all-expense-reimbursements', component: ViewAllReimbursementComponent },
  { path: 'employee/options/user-expense-reimbursements', component: ViewReimbursementComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
