import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToHomeView() {
    this.router.navigateByUrl('/employee/options');
  }

  goToAllUserReimbursementsView() {
    this.router.navigateByUrl('/employee/options/all-expense-reimbursements');
  }

  goToUserReimbursementsView() {
    this.router.navigateByUrl('/employee/options/user-expense-reimbursements');
  }

  goToAddReimbursementView() {
    this.router.navigateByUrl('/employee/options/expense-reimbursement');
  }

}
