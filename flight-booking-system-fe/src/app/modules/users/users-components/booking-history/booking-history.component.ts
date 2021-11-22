import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { jsPDF } from "jspdf";
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.scss']
})
export class BookingHistoryComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private route: Router, private service: FlightServiceService) { 
    this.searchForm =  new FormGroup({});
  }

  ngOnInit(): void {
    //this.getBookingHistory();
  }

  ticketHistory: any;
  ticketData: any;

  search(email:string){
    console.log("SUcess");
  }
  // Search and Retrive Data:
  getBookingHistory(emailId: string) {
    this.ticketData = null;
    this.service.getBookedFlightDetails(emailId).subscribe(data => {
      this.ticketHistory = data;
      console.log(this.ticketHistory);
    });
  }
  
  getBookingHistoryById(ticketId: string) {
    this.ticketHistory=[];
    this.service.getBookedFlightDetailsById(ticketId).subscribe(data => {
      this.ticketData = data;
      console.log(this.ticketData);
    });
  }


  msgs: string[] = [];
  download(ticket: any) {
    console.log("ABCCCCCCCCCCCCCC");
    console.log(ticket);
    this.msgs.push(''); this.msgs.push('');
    this.msgs.push(''); this.msgs.push('');
    this.msgs.push('PNR Number '+ticket.ticketId);
    this.msgs.push('Booked by Name : ' + ticket.userName);
    this.msgs.push('Email Id : ' + ticket.email);
    this.msgs.push(' Flight Name :'+ticket.flightName);
    this.msgs.push('Total seats booked : ' + ticket.totalSeats);
    this.msgs.push('Source : ' + ticket.startPlace);
    this.msgs.push('Destination : ' + ticket.endPlace)
    this.msgs.push('OnBoarding Time : ' + ticket.startTime)
    this.msgs.push('Arrival Time : ' + ticket.endTime)
    if(ticket.status === 'Active'){
      this.msgs.push('Ticket Status :  Completed')
    }else{
      this.msgs.push('Ticket Status :  Cancelled')
    }
    this.msgs.push('');
    this.msgs.push('Totail Amount Paid ' + ticket.totalCost)

    var doc = new jsPDF;
    doc.text(this.msgs, 8, 8);
    doc.save('Ticket_' + ticket.userName);
    this.msgs = [];

  }

  cancel(ticket: any){
    this.service.cancelFlight(ticket).subscribe(data => {
      console.log("DELETE/CANCEL TICKET");
      console.log(ticket);
    });
    this.ticketData = null;
    this.ticketHistory = [];
  }
}
