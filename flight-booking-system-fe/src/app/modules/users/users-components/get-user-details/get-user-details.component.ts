import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/model/flight-search-result';
import { FlightTicket } from 'src/app/model/flight-ticket';
import { Passenger } from 'src/app/model/passenger-details';
import { TicketDetails } from 'src/app/model/ticket-details';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-get-user-details',
  templateUrl: './get-user-details.component.html',
  styleUrls: ['./get-user-details.component.scss']
})
export class GetUserDetailsComponent implements OnInit {

  inputValue = 0;
  totalCost: number;
  ticketDetails: FormGroup;
  passengerForm: FormGroup;
  passengers: Passenger[] = [];
  displayedColumns: string[] = ['seatNo', 'name', 'sex', 'age', 'meal'];
  selectedJourney: SearchResult;
  num: number = 0;
  constructor(private service: FlightServiceService, private router: Router) {
    this.selectedJourney = history.state.data.selected;
    this.passengerForm = new FormGroup({
      seatNo: new FormControl(),
      name: new FormControl(),
      sex: new FormControl(),
      age: new FormControl(),
      meal: new FormControl()
    })
    this.ticketDetails = new FormGroup({
      userName: new FormControl(),
      email: new FormControl(),
      totalSeats: new FormControl(),
      totalCost: new FormControl()
    })
    this.totalCost = this.selectedJourney.price;
  }

  ngOnInit(): void {

  }

  onChangeEvent(event: any) {
    this.num = event.target.value;
    this.totalCost = this.selectedJourney.price * this.num;
  }

  addPassenger() {
    this.passengers.push(this.passengerForm.value);
    this.passengerForm.reset();
  }

  finalSubmit() {
    let ticket: TicketDetails = new TicketDetails(this.selectedJourney.airline, this.selectedJourney.flightName, this.selectedJourney.startPlace, this.selectedJourney.endPlace, this.selectedJourney.startTime, this.selectedJourney.endTime, this.selectedJourney.startDate, this.ticketDetails.value.userName, this.ticketDetails.value.email, this.ticketDetails.value.totalSeats, this.ticketDetails.value.totalCost, this.selectedJourney.id, '')
    let flightTicket: FlightTicket = new FlightTicket(ticket, this.passengers);
    console.log(flightTicket);
    this.service.sendTicketBookingDetails(flightTicket).subscribe((res: any) => {
      console.log("AfterBooking");
      window.alert("Your Ticket has booked Successfully!!! \n Your ticket number: " + res.ticket.ticketId);
      let route = '/users/dashboard/booking-history';
      localStorage.setItem("ticketId", res.ticket.ticketId);
      this.router.navigate([route]);
    })
  }

}
