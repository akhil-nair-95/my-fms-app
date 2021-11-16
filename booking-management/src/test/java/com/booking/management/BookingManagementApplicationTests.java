package com.booking.management;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.booking.management.models.FlightTicket;
import com.booking.management.models.Passenger;
import com.booking.management.models.Ticket;
import com.booking.management.repository.PassengerRepository;
import com.booking.management.repository.TicketRepository;
import com.booking.management.service.BookingService;

@SpringBootTest
class BookingManagementApplicationTests {

	@Autowired
	private BookingService bookingService;
	
	@MockBean
	private PassengerRepository passengers;
	
	@MockBean
	private TicketRepository ticketRepo;
	
	@Test
	public void findTicket() {
		String checker = "a@gmail.com";
		Ticket tickets = bookingService.getTicketById(checker);
		assertNull(tickets);
		
	}
	
	@Test
	public void findPassengers() {
		List<Passenger> passengers = bookingService.getPassengerByTicketId("ABC_00001");
		MatcherAssert.assertThat(passengers.size(), Matchers.greaterThan(0));
	}
	
	@Test
	public void bookTicket() {
		Ticket ticket = new Ticket();
		Passenger passenger = new Passenger();
		FlightTicket bookedTicket = new FlightTicket();
		Mockito.when(ticketRepo.save(ticket)).then(null);
		Mockito.when(passengers.save(passenger)).then(null);
		bookedTicket.setTicket(ticket);
		bookingService.bookTicket(bookedTicket);
	}

}
