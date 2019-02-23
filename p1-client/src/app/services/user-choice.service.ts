import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReimbursementData } from '../models/reimbursement-data';

@Injectable({
  providedIn: 'root'
})
export class UserChoiceService {
  private urls = {
    1: 'http://localhost:8080/p1/all-expense-reimbursements/',
    2: 'http://localhost:8080/p1/user-expense-reimbursements/',
    3: 'http://localhost:8080/p1/expense-reimbursement',
    4: 'http://localhost:8080/p1/users'
  };

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<any> {
    return this.httpClient.get(`${this.urls[4]}`);
  }

  getAllUsersReimbursements(): Observable<any> {
    return this.httpClient.get(`${this.urls[1]}`);
   }

  getUserReimbursements(userId: number): Observable<any> {
    console.log(userId);
    return this.httpClient.get(`${this.urls[2]}?id=${userId}`);
  }

  addReimbursement(reimbursementData: ReimbursementData): Observable<any> {
    return this.httpClient.post(`${this.urls[3]}`, reimbursementData);
  }

  updateReimbursement(reimbursementData: ReimbursementData): Observable<any> {
    return this.httpClient.put(`${this.urls[3]}`, reimbursementData);
  }}
