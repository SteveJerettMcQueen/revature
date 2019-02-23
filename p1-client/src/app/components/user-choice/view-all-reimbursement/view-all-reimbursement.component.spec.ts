import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllReimbursementComponent } from './view-all-reimbursement.component';

describe('ViewAllReimbursementComponent', () => {
  let component: ViewAllReimbursementComponent;
  let fixture: ComponentFixture<ViewAllReimbursementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllReimbursementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllReimbursementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
