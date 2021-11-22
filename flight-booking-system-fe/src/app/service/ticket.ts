import { Injectable } from '@angular/core';
import { SearchResult } from '../model/flight-search-result';
import { PriceDetails } from '../model/price-details';
import { SearchDetails } from '../model/search-details.bean';

@Injectable({
  providedIn: 'root'
})

export class Ticket {
  searchDetails: SearchDetails = new SearchDetails();
  searchBillAmount: PriceDetails = new PriceDetails();
  ticket: any;
  selectedFlightDetailsOnward: SearchResult = new SearchResult();
  selectedFlightDetailsRound?: SearchResult = new SearchResult();
}