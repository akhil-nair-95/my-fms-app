import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SearchResult } from '../model/flight-search-result';
import { FlightTicket } from '../model/flight-ticket';
import { TicketDetails } from '../model/ticket-details';

@Injectable({
  providedIn: 'root'
})
export class FlightServiceService {

  paymentSuccess: any;

  constructor(private http: HttpClient) { }

  private adminUrl = "http://localhost:8090";

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
    return this.http.get<string[]>(this.adminUrl + "/airports-location-list", { 'headers': this.addHeaderTotheUrl() });
  }

  //Get Search Result:
  getSearchResult(searchRequest: any) {
    return this.http.post<SearchResult[]>(this.adminUrl + "/api/v1/flight/search", searchRequest, { 'headers': this.addHeaderTotheUrl() });
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
    return this.http.put(this.adminUrl + "/api/v1/booking/ticket/cancel" , ticket, { 'headers': this.addHeaderTotheUrl() });
  }

  // fetch Discount:
  getDiscountDetails() {
    return this.http.get(this.adminUrl + '/get-discounts', { 'headers': this.addHeaderTotheUrl() });
  }

  // Register New User - Customer For Login:
  registerNewUser(userDetails: any) {
    return this.http.post(this.adminUrl + "/register-user", userDetails);
  }

}
