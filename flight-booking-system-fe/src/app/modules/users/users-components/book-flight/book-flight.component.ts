import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchDetails } from 'src/app/model/search-details.bean';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/service/ticket';
import { SearchResult } from 'src/app/model/flight-search-result';
import { SearchRequest } from 'src/app/model/search-request';

@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.scss']
})

export class BookFlightComponent implements OnInit {

  flag:boolean = true;
  // //constructor
  // constructor(private formBuilder: FormBuilder, private flightService: FlightServiceService, private router: Router,
  //   private tservice: Ticket) {
  // }

  // //Form builder
  // flightSearchForm: FormGroup = new FormGroup({});

  // //Form Controls:
  // startPlace = new FormControl("", Validators.required);
  // endPlace = new FormControl("", Validators.required);
  // journeyDate = new FormControl("", Validators.required);

  // ngOnInit(): void {
  //   this.flightSearchForm = this.formBuilder.group({
  //     startPlace: this.startPlace,
  //     endPlace: this.endPlace,
  //     journeyDate: this.journeyDate
  //   });
  // }

 
  // restForm() {
  //   this.flightSearchForm.reset();
  // }

  // sendData() {
  //   let searchDeatils: SearchDetails = this.flightSearchForm.value;

  //   //Send this to Server and get Search Result:
  //   this.tservice.searchDetails = searchDeatils;

  //   //Redirect to 
  //   this.router.navigate(['users/dashboard/search-result']);
  // }

  // NEW

  flightSearchForm: FormGroup;
  response: SearchResult[] = [];
  displayedColumns: string[] = ['airlineName', 'flightName', 'startPlace', 'endPlace', 'startTime', 'endTime', 'journeyDate', 'price', 'availability'];
  clickedRows = new Set<SearchResult>();
  constructor(private flightService: FlightServiceService,private router: Router) {
    this.flightSearchForm = new FormGroup({
      startPlace: new FormControl("", Validators.required),
      endPlace: new FormControl("", Validators.required),
      journeyDate: new FormControl("", Validators.required)
    })
  }

  ngOnInit(): void {
  }

  search() {
    this.response = []
    if (this.flightSearchForm.valid) {
      let req = new SearchRequest(this.flightSearchForm.value.startPlace, this.flightSearchForm.value.endPlace, this.flightSearchForm.value.journeyDate);
      this.flightService.getSearchResult(req).subscribe((res: any) => {
        this.flag = false;
        this.response = res;
      })
    }
  }

  selectedJourney(selected: SearchResult){
    console.log("+++++=====================+++++");
    console.log(selected);
    let route = 'users/dashboard/enter-details';
    this.router.navigate([route],{state: {data: {selected}}});
  }

}
