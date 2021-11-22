import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageScheduledFlightsComponent } from './manage-scheduled-flights.component';

describe('ManageScheduledFlightsComponent', () => {
  let component: ManageScheduledFlightsComponent;
  let fixture: ComponentFixture<ManageScheduledFlightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageScheduledFlightsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageScheduledFlightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
