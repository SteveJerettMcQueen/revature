import { TestBed } from '@angular/core/testing';

import { GetReimbursementStatusesService } from './get-reimbursement-statuses.service';

describe('GetReimbursementStatusesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetReimbursementStatusesService = TestBed.get(GetReimbursementStatusesService);
    expect(service).toBeTruthy();
  });
});
