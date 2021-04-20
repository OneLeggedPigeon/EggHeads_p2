import { TestBed } from '@angular/core/testing';

import { IncubatorService } from './incubator.service';

describe('IncubatorService', () => {
  let service: IncubatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IncubatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
