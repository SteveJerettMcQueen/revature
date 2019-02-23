import { TestBed } from '@angular/core/testing';

import { AddReimbursementServiceService } from './add-reimbursement-service.service';

describe('AddReimbursementServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddReimbursementServiceService = TestBed.get(AddReimbursementServiceService);
    expect(service).toBeTruthy();
  });
});
