import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { UserAccessData } from '../models/user-access-data';

@Injectable({
  providedIn: 'root'
})
export class UserAccessService {
  private url = 'http://localhost:8080/p1/expense-reimbursement-system-login';

  constructor(private httpClient: HttpClient) { }

  getUser(userAccessData: UserAccessData): Observable<any> {
    return this.httpClient.post(this.url, userAccessData);
  }

}
