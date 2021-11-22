import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AirlineDetails } from '../model/airline-details';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) {
  }

  private adminUrl = "http://localhost:8090"  // <--- API Gateway Url

  //Admin Service:

  // Check Admin Login Details:
  doAuthentication(userDetails: any) {
    return this.httpClient.post(this.adminUrl + "/authenticate/admin/login", userDetails);
  }

  addHeaderTotheUrl(): HttpHeaders {
    // let headers = new HttpHeaders();
    let token = localStorage.getItem("token");
    // token = 'Bearer ' + token;
    // headers = headers.append('content-type', 'application/json')
    // headers = headers.append('Authorization', token);
    // headers.set('content-type', 'application/json')
    // .set('Access-Control-Allow-Origin', '*')
    // .set('Authorization', token);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': `Bearer ${token}`
    })
    return headers;
  }

    //Add New Airport: Done (Check and correct airport add api emndpoint)
    addNewAirport(airportData: any) {
      return this.httpClient.post(this.adminUrl + "/api/v1/airlines/addAirline", airportData, { 'headers': this.addHeaderTotheUrl() });
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
    return this.httpClient.post<string>(this.adminUrl + '/api/v1/admin/flight/schedule/cancel', flight, { 'headers': this.addHeaderTotheUrl() });
  }

  //Get All Booked Tickets:
  getBookedFlightDetails() {
    return this.httpClient.get(this.adminUrl + "/api/v1/booking/ticket/all", { 'headers': this.addHeaderTotheUrl() });
  }

}
