package com.booking.management.models;

import java.util.ArrayList;
import java.util.List;

public class FlightTicket {

	private Ticket ticket;
	private List<Passenger> passenger;
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	
	public FlightTicket(Ticket ticket, List<Passenger> passenger) {
		super();
		this.ticket = ticket;
		this.passenger = passenger;
	}
	
	public FlightTicket() {}
	
	
	
	@Override
	public String toString() {
		return "FlightTicket [ticket=" + ticket + ", passenger=" + passenger + "]";
	}
	public static void main(String[] args) {
		FlightTicket flight = new FlightTicket();
		Ticket ticket = new Ticket("SpiceJet", "ABC", "Kochi", "Bangalore", "05:00 AM", "07:30 AM", "ABC",
				"ABC@gmail.com", "15/11/2021", 5, 2040.50f, "Booked");
		List<Passenger> passengerList = new ArrayList<>();
		Passenger passenger = new Passenger(5, "Kohli", "Male", 35, "Non-Veg", null);
		passengerList.add(passenger);
		flight.setPassenger(passengerList);
		flight.setTicket(ticket);
		System.out.println(flight);
	}
}
