import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { EggTemplateService } from './egg-template.service';

describe('EggTemplate', () => {
  let service: EggTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(EggTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
