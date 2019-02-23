import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ReimbursementData } from '../../../models/reimbursement-data';
import { CookieService } from 'ngx-cookie-service';
import { UserChoiceService } from '../../../services/user-choice.service';
import { GetReimbursementTypesService } from '../../../services/get-reimbursement-types.service';

@Component({
  selector: 'app-add-reimbursement',
  templateUrl: './add-reimbursement.component.html',
  styleUrls: ['./add-reimbursement.component.css']
})
export class AddReimbursementComponent implements OnInit {
  private isSuccess = false;
  private message = '';
  private alertClass = '';
  private reimbursementTypes: any;
  private reimbursementData: ReimbursementData = {
    eruid: 0,
    amount: 0,
    description: '',
    status: {
      statusid: 1,
      status: 'Pending'
    },
    type: { typeid: 1 },
    receipt: null
  };

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private userChoiceService: UserChoiceService,
    private reimbursementTypeService: GetReimbursementTypesService) { }

  ngOnInit() {
    this.setReimbursementTypes();
    this.reimbursementData.eruid = this.cookieService.get('userId');
  }

  selectedReimbursementType(event: any) {
    this.reimbursementData.type.typeid = event.target.value;
  }

  setReimbursementTypes() {
    this.reimbursementTypeService.getReimbursementTypes().subscribe(
      (response) => {
        this.reimbursementTypes = response;
        console.log(response);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  submitReimbursement() {
    console.log('ReimData: ' + this.reimbursementData.eruid);
    console.log(this.reimbursementData);
    this.userChoiceService.addReimbursement(this.reimbursementData).subscribe(
      (response) => {
        if (response === false) {
          this.message = 'Reimbursement Not Added!';
          this.alertClass = 'alert-danger';
          console.log('Reimbursement Not Added!');
        } else {
          this.message = 'Reimbursement submitted!';
          this.alertClass = 'alert-success';
          this.isSuccess = true;
          console.log(response);
        }
      },
      (err) => {

      });
  }

  back() {
    this.router.navigateByUrl('/employee/options');
  }

}
