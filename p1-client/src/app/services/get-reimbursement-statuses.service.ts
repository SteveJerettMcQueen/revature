import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetReimbursementStatusesService {

  private url = 'http://localhost:8080/p1/expense-reimbursement-statuses';

  constructor(private httpClient: HttpClient) { }

  getReimbursementStatuses(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
