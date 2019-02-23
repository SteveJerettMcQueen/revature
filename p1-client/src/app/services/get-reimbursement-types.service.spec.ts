import { TestBed } from '@angular/core/testing';

import { GetReimbursementTypesService } from './get-reimbursement-types.service';

describe('GetReimbursementTypesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetReimbursementTypesService = TestBed.get(GetReimbursementTypesService);
    expect(service).toBeTruthy();
  });
});
