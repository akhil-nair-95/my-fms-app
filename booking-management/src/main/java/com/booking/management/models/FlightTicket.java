package com.booking.management.models;

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
}
