import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-manage-scheduled-flights',
  templateUrl: './manage-scheduled-flights.component.html',
  styleUrls: ['./manage-scheduled-flights.component.scss']
})
export class ManageScheduledFlightsComponent implements OnInit {

  ScheduledAllFlightDetails: any;
  isCancelled:boolean = false;

  constructor(private service: AdminService) { }

  ngOnInit(): void {
    this.initializeScheduledFlightDetails();
  }

  initializeScheduledFlightDetails() {
    this.service.getAllScheduledFlights().subscribe(data => {
      this.ScheduledAllFlightDetails = data;
    });
  }

  deleteScheduledFlight(flight: any) {
    confirm("Do you wnat to Block the Sheduled Flight ?");
    flight.availability = 0;
    flight.status = false;
    this.service.deleteScheduledFlight(flight).subscribe(data => {
      this.isCancelled = JSON.parse(data).availability === 0;
      this.initializeScheduledFlightDetails();
    });
  }

}
