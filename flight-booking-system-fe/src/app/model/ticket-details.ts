export class TicketDetails{
    constructor(
        public airlineName:string,
        public flightName:string,
        public startPlace:string, 
        public endPlace:string, 
        public startTime:string, 
        public endTime:string, 
        public journeyDate:string,
        public userName:string,
        public email:string,
        public totalSeats:string,
        public totalCost:string,
        public scheduleId:number,
        public ticketId:string
    ){}  
}