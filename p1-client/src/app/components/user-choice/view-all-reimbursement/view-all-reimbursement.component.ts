import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';
import { UserChoiceService } from '../../../services/user-choice.service';
import { GetReimbursementStatusesService } from '../../../services/get-reimbursement-statuses.service';
import { AddReimbursementServiceService } from 'src/app/services/add-reimbursement-service.service';

@Component({
  selector: 'app-view-all-reimbursement',
  templateUrl: './view-all-reimbursement.component.html',
  styleUrls: ['./view-all-reimbursement.component.css']
})
export class ViewAllReimbursementComponent implements OnInit {

  private reimbursementStatuses: any;
  private selectedStatus: any;
  private setClickedUser: any;
  private setClickedStatus: any;
  private isStatusEditable = false;

  private isSelected = false;
  private selectedUser: any;
  private users: any;
  private resolverId: any;
  private reimbursements: any;

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private userChoiceService: UserChoiceService) { }

  ngOnInit() {
    this.resolverId = this.cookieService.get('userId');
    this.setClickedUser = function(user: any) {
      this.isSelected = true;
      this.selectedUser = user;
    };
    this.setAllUsers();
  }

  setAllUsers() {
    this.userChoiceService.getAllUsers().subscribe(
      (response) => {
        this.users = response;
        this.removeCurrentAdmin(this.users);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  removeCurrentAdmin(users: any) {
    for (const user of users) {
      if (user.eruid == this.resolverId) {
        this.users.splice(this.users.indexOf(user), 1);
      }
    }
  }

  viewSelectedUserReimbursements() {
    const userId = this.selectedUser.eruid;
    console.log(this.selectedUser);
    this.userChoiceService.getUserReimbursements(userId).subscribe(
      (response) => {
        this.reimbursements = response;
        console.log(response);
      },
      (err) => {
        console.log(err);
      });
  }

  doOperation(reim: any, statusid: any, status: string) {
    reim.status.statusid = statusid;
    reim.status.status = status;
    reim.resolver = this.resolverId;
    console.log('approve: ' + reim.resolver);
    this.userChoiceService.updateReimbursement(reim).subscribe(
      (response) => {
        console.log(response);
      },
      (err) => {
        console.log(err);
      });
  }

  back() {
    this.router.navigateByUrl('/employee/options');
  }

  // setReimbursementStatuses() {
  //   this.reimbursementStatusesService.getReimbursementStatuses().subscribe(
  //     (response) => {
  //       this.reimbursementStatuses = response;
  //       console.log(this.reimbursementStatuses);
  //     },
  //     (err) => {
  //       console.log(err);
  //     }
  //   );
  // }

}
