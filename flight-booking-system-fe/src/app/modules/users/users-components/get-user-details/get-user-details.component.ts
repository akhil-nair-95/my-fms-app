import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/model/flight-search-result';
import { FlightTicket } from 'src/app/model/flight-ticket';
import { Passenger } from 'src/app/model/passenger-details';
import { TicketDetails } from 'src/app/model/ticket-details';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { Ticket } from 'src/app/service/ticket';

@Component({
  selector: 'app-get-user-details',
  templateUrl: './get-user-details.component.html',
  styleUrls: ['./get-user-details.component.scss']
})
export class GetUserDetailsComponent implements OnInit {

  // //Form Group:
  // getDetailsForm: FormGroup = new FormGroup({});
  // // returnDate = this.tService.searchDetails.returnDate;
  // billingAmount = this.tService.searchBillAmount.totalBillingAmountBeforeIncludingPassenger;
  // isDiscountApplied: boolean = false;
  // discountValidation: boolean = false;

  // oktoCheckout:boolean = false;

  // constructor(private formBulider: FormBuilder, private router: Router,
  //   private fservice: FlightServiceService, private tService: Ticket) {
  // }

  // ngOnInit(): void {
  //   this.initializeForm();
  //   this.getDetailsForm.get('name')?.setValue(sessionStorage.getItem("userName"));
  //   this.getDetailsForm.get('email')?.setValue(sessionStorage.getItem("emailId")); 
  // }

  // initializeForm() {
  //   this.getDetailsForm = this.formBulider.group({
  //     name: new FormControl('Thinesh'),
  //     email: new FormControl('Thinesh@cts.com'),
  //     passengersList: this.formBulider.array([
  //       this.formBulider.group({
  //         pName: new FormControl(),
  //         pGender: new FormControl(),
  //         age: new FormControl(),
  //         onwardSeat: new FormControl(),
  //         returnSeat: new FormControl(),
  //         optMeal: new FormControl(),
  //       })
  //     ]),
  //     discount: new FormControl(''),
  //     billingAmmount: new FormControl(0),
  //     onwardScheduleId: new FormControl(this.tService.selectedFlightDetailsOnward.id),
  //     roundScheduleId: new FormControl(this.tService.selectedFlightDetailsRound?.id),
  //     // tripType: new FormControl(this.tService.searchDetails.tripType),
  //   });
  //   this.editFormGroup();
  // }

  // editFormGroup() {
  //   console.log(this.tService.selectedFlightDetailsOnward.id);
  //   // if (this.tService.searchDetails.tripType == 'oneway') {
  //   //   this.getDetailsForm.removeControl('roundScheduleId');
  //   // }
  // }

  // //Get Passenger Array List:
  // getPassenegrArray(): FormArray {
  //   return this.getDetailsForm.get('passengersList') as FormArray;
  // }

  // //Add Passenger:
  // addPassenger() {
  //   let newUser = this.formBulider.group({
  //     pName: '',
  //     pGender: '',
  //     age: '',
  //     onwardSeat: '',
  //     returnSeat: '',
  //     optMeal: '',
  //   });

  //   this.getPassenegrArray().push(newUser);
  // }

  // //Remove Passenger:
  // removePassenger(i: any) {
  //   this.getPassenegrArray().removeAt(i);
  // }

  // showCalculate: boolean = false;
  // //Calculate Price:
  // calculatePrice() {
  //   let totalPrice: number;
  //   let numberOfCustomers = this.getPassenegrArray().length;
  //   totalPrice = numberOfCustomers * this.billingAmount;

  //   this.getDetailsForm.get('billingAmmount')?.setValue(totalPrice);
  //   this.getDetailsForm.get('tripOnewayPrice')?.setValue(this.tService.searchBillAmount.tripOnePrice * numberOfCustomers);

  //   if (!!this.tService.searchBillAmount.tripTwoPrice) {
  //     this.getDetailsForm.get('roundTripPrice')?.setValue(this.tService.searchBillAmount.tripTwoPrice * numberOfCustomers);
  //   }
  //   this.showCalculate = true;
  //   this.oktoCheckout = true;
  // }

  // discoundList: any;
  // applyDiscount() {
  //   this.discountValidation = true;
  //   this.fservice.getDiscountDetails().subscribe(data => {
  //     this.discoundList = data;
  //     var discount = this.getDetailsForm.value.discount;
  //     this.validateDiscount(discount);
  //   });

  // }

  // incorret:string = '';

  // //Validate It:
  // validateDiscount(appliedCode: string) {
  //   for (let discount of this.discoundList) {
  //     if (discount.discountCode == appliedCode) {
  //       this.changePrice(+discount.discountPercentage);
  //       this.isDiscountApplied = true;
  //       break;
  //     }
  //   }

  //   if(this.isDiscountApplied == false){
  //     this.incorret = 'Incorrect Code';
  //   }
  // }

  // //ApplyDiscountPrice:
  // changePrice(discountPercent: number) {
  //   var currentBill: Number = +this.getDetailsForm.value.billingAmmount;
  //   var disAmount: number = ((+currentBill) * (discountPercent / 100));
  //   this.getDetailsForm.get('billingAmmount')?.setValue(+currentBill - disAmount);
  // }

  // submitForm() {
  //   console.log(this.getDetailsForm.value);
  //   this.tService.ticket = this.getDetailsForm.value;
  //   this.router.navigate(['dashboard/paymet']);
  // }


  // ---------- NEW -------------------

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
