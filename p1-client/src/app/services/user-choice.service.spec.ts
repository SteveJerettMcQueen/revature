import { TestBed } from '@angular/core/testing';

import { UserChoiceService } from './user-choice.service';

describe('UserChoiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserChoiceService = TestBed.get(UserChoiceService);
    expect(service).toBeTruthy();
  });
});
