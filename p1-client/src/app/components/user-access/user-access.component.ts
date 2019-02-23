import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserAccessData } from '../../models/user-access-data';
import { UserAccessService } from '../../services/user-access.service';


@Component({
  selector: 'app-user-access',
  templateUrl: './user-access.component.html',
  styleUrls: ['./user-access.component.css']
})
export class UserAccessComponent implements OnInit {

  private alertClass: string;
  private isIncorrect: boolean;
  private incorrectMessage: string;
  private userAccessData: UserAccessData = {
    username: '',
    password: ''
  };

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private userAccessService: UserAccessService) { }

  ngOnInit() {
    this.isIncorrect = false;
    this.incorrectMessage = '';
    this.cookieService.deleteAll();
  }

  submitEvent() {
    console.log(this.userAccessData);
    this.userAccessService.getUser(this.userAccessData).subscribe(
      (response) => {
        if (response === false) {
          this.isIncorrect = true;
          this.incorrectMessage = 'Username or password is incorrect!';
          this.alertClass = 'alert-danger';
          this.router.navigateByUrl('/expense-reimbursement-system-login');
        } else {
          const userId = response.eruid;
          const role = response.role.role;
          const name = response.firstname + ' ' + response.lastname;
          this.cookieService.set('userId', userId);
          this.cookieService.set('role', role);
          this.cookieService.set('name', name);
          console.log(userId);
          console.log(role);
          console.log(name);
          this.router.navigateByUrl('/employee/options');
        }
        console.log(response);
      },
      (err) => {
        console.log(err);
      }
    );
  }

}
