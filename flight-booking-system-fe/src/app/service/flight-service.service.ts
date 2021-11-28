import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AirportDetails } from '../model/airport-details';
import { SearchResult } from '../model/flight-search-result';
import { FlightTicket } from '../model/flight-ticket';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { TicketIDResp } from '../model/response-ticket';

@Injectable({
  providedIn: 'root'
})
export class FlightServiceService {

  paymentSuccess: any;

  constructor(private http: HttpClient) { }

  private adminUrl = "http://172.32.129.39:8090";

  private awsUrl = "http://ec2-3-143-215-254.us-east-2.compute.amazonaws.com:8383"

  doAuthentication(userDetails: any) {
    return this.http.post(this.adminUrl + "/authenticate/admin/login", userDetails);
  }

  addHeaderTotheUrl(): HttpHeaders {
    let headers = new HttpHeaders();
    let token = localStorage.getItem("token");
    token = 'Bearer ' + token;
    console.log(token);
    headers = headers.append('content-type', 'application/json')
    // headers = headers.append('Authorization', token);
    return headers;
  }

  //Get List of Airports API:
  getAllAirportList() {
    return this.http.get<string[]>(this.adminUrl + "/api/v1/airport", { 'headers': this.addHeaderTotheUrl() });
  }

  //Get Search Result:
  getSearchResult(searchRequest: any) {
    return this.http.post<SearchResult[]>(this.adminUrl + "/api/v1/flight/search", searchRequest, { 'headers': this.addHeaderTotheUrl().append('responseType', 'text') });
  }

  //Send booking Details:
  sendTicketBookingDetails(ticket: any) {
    return this.http.post(this.adminUrl + "/api/v1/booking/ticket/book", ticket, { 'headers': this.addHeaderTotheUrl() });
  }

  // Get Booking - History:
  getBookedFlightDetails(emailid: any) {
    return this.http.get<FlightTicket[]>(this.adminUrl + "/api/v1/booking/ticket/email/" + emailid, { 'headers': this.addHeaderTotheUrl() });
  }

  // Get Booking - History:
  getBookedFlightDetailsById(id: any) {
    return this.http.get<FlightTicket>(this.adminUrl + "/api/v1/booking/ticket/" + id, { 'headers': this.addHeaderTotheUrl() });
  }

  cancelFlight(ticket: any) {
    return this.http.put(this.adminUrl + "/api/v1/booking/ticket/cancel", ticket, { 'headers': this.addHeaderTotheUrl() });
  }

  getListOfAllAirports() {
    return this.http.get<AirportDetails[]>(this.awsUrl + "/allAirport", {
      'headers': this.addHeaderTotheUrl(),
      observe: 'response'
    }).pipe(map(data => {
      console.log("body: ", data.body);
      return data.body;
    }));
  }

  getCancelledTicketId(id: any) {
    return this.http.get<TicketIDResp>(this.adminUrl + "/api/v1/booking/getTicketId/" + id, { 'headers': this.addHeaderTotheUrl()});
  }

  notifyUser(id: any) {
    return this.http.get<string>("https://vm41og1yn7.execute-api.us-east-1.amazonaws.com/default/notifyPassenger?ticketId=" + id, { 'headers': this.addHeaderTotheUrl() });
  }

}
