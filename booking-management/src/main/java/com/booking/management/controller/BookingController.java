package com.booking.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.management.models.FlightTicket;
import com.booking.management.models.Schedule;
import com.booking.management.models.SearchRequest;
import com.booking.management.models.Ticket;
import com.booking.management.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
		
	@PostMapping("/ticket/search")
	public List<Schedule> searchTicket(@RequestBody SearchRequest request ) {
		return bookingService.searchTicket(request);
	}
	
	@PostMapping("/ticket/book")
	public FlightTicket bookTicket(@RequestBody FlightTicket ticket ) {
		return bookingService.bookTicket(ticket);
	}
	
	@PutMapping("/ticket/cancel")
	public FlightTicket cancelTicket(@RequestBody FlightTicket ticket ) {
		return bookingService.cancelTicket(ticket);
	}
	
	@GetMapping("/ticket/{ticketId}")
	public Ticket getTicketById(@PathVariable String ticketId ) {
		return bookingService.getTicketById(ticketId);
	}
	
	@GetMapping("/ticket/email/{email}")
	public Ticket getTicketByEmail(@PathVariable String email ) {
		return bookingService.getTicketByEmail(email);
	}

}
