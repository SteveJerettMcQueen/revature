import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';
import { UserChoiceService } from '../../../services/user-choice.service';

@Component({
  selector: 'app-view-reimbursement',
  templateUrl: './view-reimbursement.component.html',
  styleUrls: ['./view-reimbursement.component.css']
})
export class ViewReimbursementComponent implements OnInit {

  private reimbursements: any;

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private userChoiceService: UserChoiceService) { }

  ngOnInit() {
    const userId: any = this.cookieService.get('userId');
    console.log(userId);
    this.setUserReimbursements(userId);
  }

  private setUserReimbursements(userId: number) {
    this.userChoiceService.getUserReimbursements(userId).subscribe(
      (response) => {
        if (response === false) {
          console.log(false);
        } else {
          this.reimbursements = response;
          console.log(response);
        }
      },
      (err) => {
        console.log(err);
      });
  }

  back() {
    this.router.navigateByUrl('/employee/options');
  }
}
