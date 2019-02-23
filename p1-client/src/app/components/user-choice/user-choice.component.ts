import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';
import { LogoutService } from 'src/app/services/logout.service';

@Component({
  selector: 'app-user-choice',
  templateUrl: './user-choice.component.html',
  styleUrls: ['./user-choice.component.css']
})
export class UserChoiceComponent implements OnInit {

  private isAdmin = false;
  private name: string;

  constructor(
    private router: Router,
    private logoutService: LogoutService,
    private cookieService: CookieService) { }

  ngOnInit() {
    const role = this.cookieService.get('role');
    this.name = this.cookieService.get('name');
    console.log(role);
    if (role === 'Admin') {
      this.isAdmin = true;
    }
  }

  logout() {
    this.logoutService.logout().subscribe(
      (payload) => {
        this.cookieService.delete('userId',  ' / ' ,  ' / ');
        this.cookieService.delete('role',  ' / ' ,  ' / ');
        this.cookieService.delete('name', ' / ', ' / ');
        this.cookieService.deleteAll( '/ ',  '/' );
        this.router.navigateByUrl('/expense-reimbursement-system-login');
      },
      (err) => {
        console.log(err);
      });
  }

}
