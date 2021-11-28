import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AdminService } from 'src/app/service/admin.service';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-manage-scheduled-flights',
  templateUrl: './manage-scheduled-flights.component.html',
  styleUrls: ['./manage-scheduled-flights.component.scss']
})
export class ManageScheduledFlightsComponent implements OnInit {

  ScheduledAllFlightDetails: any;
  // isCancelled: boolean = false;
  ticketId: string = "";
  notified: boolean = false;
  // isUnblocked: boolean =false;

  constructor(private service: AdminService, private flightService: FlightServiceService, private toastrService: ToastrService) { }

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
      // this.isCancelled = data.availability === 0;
      if(data.availability === 0){
        this.toastrService.success('Schedule blocked successfully', 'Success');
      }
      console.log("my log: ", data);
      //this.ticketId = this.getTicketId(data.id);
      //this.notified = this.notifyUser(this.ticketId);
      if(this.notified)
      this.initializeScheduledFlightDetails();
      
    });
  }
  notifyUser(ticketId: string): boolean {
    this.flightService.notifyUser(ticketId).subscribe(data => {
      console.log("Notified users: ", data);
    });
    return true;
  }
  getTicketId(id: any): string {
    this.flightService.getCancelledTicketId(id).subscribe(data => {
      this.ticketId = data.ticketId;
      console.log("Notify for ticket: ", data.ticketId);
      this.notifyUser(data.ticketId);
    });

    return this.ticketId;
  }

  unblockScheduledFlight(flight: any) {
    confirm("Do you wnat to Un-Block the Sheduled Flight ?");
    flight.availability = 1;
    // this.isUnblocked = true;
    // this.isCancelled = false;
    flight.status = true;
    this.service.unblockScheduledFlight(flight).subscribe(data => {
      console.log("unblocked log: ", data);
      if(data.availability === 1){
        this.toastrService.success('Schedule un-blocked successfully', 'Success');
      }
      this.initializeScheduledFlightDetails();
    });
  }

}

