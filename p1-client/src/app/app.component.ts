import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'p1-app';

  constructor(
    private router: Router,
    private auth: AuthService) { }

  logout() {
    this.auth.logout();
    this.router.navigate(['expense-reimbursement-system-login']);
  }

}
