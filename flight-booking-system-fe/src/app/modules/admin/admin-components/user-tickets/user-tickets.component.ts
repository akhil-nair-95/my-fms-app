import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-user-tickets',
  templateUrl: './user-tickets.component.html',
  styleUrls: ['./user-tickets.component.scss']
})
export class UserTicketsComponent implements OnInit {

  constructor(private route: Router, private service: AdminService) { }

  ngOnInit(): void {
    this.getBookingHistory();
  }

  ticketHistory: any;

  // Search and Retrive Data:
  getBookingHistory() {
    this.service.getBookedFlightDetails().subscribe(data => {
      console.log("All Ticket data received")
      console.log(data);
      this.ticketHistory = data;
    });
  }

}
