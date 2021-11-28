import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AirlineDetails } from '../model/airline-details';
import { map } from 'rxjs/operators';
import { AirportDetails } from '../model/airport-details';
import { ScheduleDetails } from '../model/schedule-details';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) {
  }

  private adminUrl = "http://172.32.129.39:8090"  // <--- API Gateway Url

  private awsUrl = "http://ec2-3-143-215-254.us-east-2.compute.amazonaws.com:8383"

  //Admin Service:

  // Check Admin Login Details:
  doAuthentication(userDetails: any) {
    return this.httpClient.post(this.adminUrl + "/authenticate/admin/login", userDetails);
  }

  addHeaderTotheUrl(): HttpHeaders {
    // let headers = new HttpHeaders();
    let token = localStorage.getItem("token");

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': `Bearer ${token}`
    })
    return headers;
  }

    //Add New Airport: Done (Check and correct airport add api emndpoint)
    addNewAirport(airportData: any): Observable<HttpResponse<Object>> {
      return this.httpClient.post(this.awsUrl + "/addAirport", airportData, { 'headers': this.addHeaderTotheUrl(),
    observe: 'response' }).pipe(map(data => {
      return data;
    }));
    }

    getListOfAllAirports() {
      return this.httpClient.get<AirportDetails[]>(this.awsUrl + "/allAirport", { 'headers': this.addHeaderTotheUrl(),
    observe: 'response' }).pipe(map(data => {
      console.log("body: ", data.body);
      return data.body;
    }));
    }

  //Add New AirLine: Done
  addNewAriline(airlineData: any) {
    return this.httpClient.post(this.adminUrl + "/api/v1/airlines/addAirline", airlineData, { 'headers': this.addHeaderTotheUrl() });
  }

  //Get All Airlines List: Done
  getListOfAllAirlines() {
    return this.httpClient.get<AirlineDetails[]>(this.adminUrl + "/api/v1/airlines/allAirlines", { 'headers': this.addHeaderTotheUrl() });
  }

  //Schedule Flight: Done
  scheduleFlight(scheduleFlight: any) {
    return this.httpClient.post<string>(this.adminUrl + "/api/v1/admin/flight/schedule/add", scheduleFlight, { 'headers': this.addHeaderTotheUrl() });
  }

  //getAll Scheduled Flights:Done
  getAllScheduledFlights() {
    return this.httpClient.get(this.adminUrl + '/api/v1/admin/flight/schedules', { 'headers': this.addHeaderTotheUrl() });
  }

  //Delete Schedule Flights: Done
  deleteScheduledFlight(flight: any) {
    return this.httpClient.post<ScheduleDetails>(this.adminUrl + '/api/v1/admin/flight/schedule/cancel', flight, { 'headers': this.addHeaderTotheUrl() });
  }

  unblockScheduledFlight(flight: any) {
    return this.httpClient.post<ScheduleDetails>(this.adminUrl + '/api/v1/admin/flight/schedule/unblock', flight, { 'headers': this.addHeaderTotheUrl() });
  }

  //Get All Booked Tickets:
  getBookedFlightDetails() {
    return this.httpClient.get(this.adminUrl + "/api/v1/booking/ticket/all", { 'headers': this.addHeaderTotheUrl() });
  }

}
