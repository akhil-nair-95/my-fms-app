import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/model/flight-search-result';
import { SearchRequest } from 'src/app/model/search-request';
import { AirportDetails } from 'src/app/model/airport-details';

@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.scss']
})

export class BookFlightComponent implements OnInit {

  flag:boolean = true;

  airportList: AirportDetails[] = [];

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
    this.getAirportList();
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

  getAirportList(){
    this.flightService.getListOfAllAirports().subscribe(data => {
      if(!!data){
        this.airportList = data;
      }
    })
  }

}
