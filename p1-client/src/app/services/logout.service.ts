import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {
  private url = 'http://localhost:8080/p1/expense-reimbursement-system-logout';

  constructor(private httpClient: HttpClient) { }

  logout() {
    return this.httpClient.get(this.url);
  }

}
