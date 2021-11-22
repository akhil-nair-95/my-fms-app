import { Passenger } from "./passenger-details";
import { TicketDetails } from "./ticket-details";

export class FlightTicket{
    constructor(
        public ticket:TicketDetails,
        public passenger:Passenger[]
    ){}
}