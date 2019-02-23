import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserAccessData } from '../models/user-access-data';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  private isLoggedIn = false;
  private url: 'http://localhost:8080/p1/expense-reimbursement-system-login';

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    console.log(localStorage.get('access_token'));
    this.isLoggedIn = (localStorage.get('access_token') !== null);
  }

  login(userAccessData: UserAccessData): Observable<any> {
    return this.httpClient.post(this.url, userAccessData);
  }

  logout() {
    localStorage.removeItem('access_token');
  }

}
