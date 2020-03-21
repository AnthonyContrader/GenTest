import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DioComponent } from './dio.component';

describe('DioComponent', () => {
  let component: DioComponent;
  let fixture: ComponentFixture<DioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
