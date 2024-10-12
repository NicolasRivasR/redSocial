import { TestBed } from '@angular/core/testing';

import { RegsitrationService } from './regsitration.service';

describe('RegsitrationService', () => {
  let service: RegsitrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegsitrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
