import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetReimbursementTypesService {

  private url = 'http://localhost:8080/p1/expense-reimbursement-types';

  constructor(private httpClient: HttpClient) { }

  getReimbursementTypes(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
