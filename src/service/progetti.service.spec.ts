import { TestBed } from '@angular/core/testing';

import { ProgettiService } from './progetti.service';

describe('ProgettiService', () => {
  let service: ProgettiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgettiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
