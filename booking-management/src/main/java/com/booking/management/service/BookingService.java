package com.booking.management.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.management.models.FlightTicket;
import com.booking.management.models.Passenger;
import com.booking.management.models.Schedule;
import com.booking.management.models.SearchRequest;
import com.booking.management.models.Ticket;
import com.booking.management.repository.PassengerRepository;
import com.booking.management.repository.TicketRepository;

@Service
public class BookingService {
	
//	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String TOPIC = "kafka_topic_name";
	
	public FlightTicket bookTicket(FlightTicket ticket) {
	ticket.getTicket().setStatus("Active");
	Ticket ticketSaved = ticketRepo.save(ticket.getTicket());
	List<Passenger> savedPassenger = new ArrayList<>();
	
	ticket.getPassenger().forEach(pass ->{ 
		pass.setTicketId(ticketSaved.getTicketId());
		Passenger passenger = passengerRepo.save(pass);
		savedPassenger.add(passenger);
	});
	
	FlightTicket bookedTicket = new FlightTicket();
	bookedTicket.setTicket(ticketSaved);
	bookedTicket.setPassenger(savedPassenger);
	return bookedTicket;
	}

	public FlightTicket cancelTicket(Ticket ticket) {
		FlightTicket cancelledTicket = new FlightTicket();
		Ticket ticketDetails = ticket; 
		ticketDetails.setStatus("Cancelled");
		Ticket flightTicket = ticketRepo.save(ticketDetails);
		cancelledTicket.setTicket(flightTicket);
		cancelledTicket.setPassenger(getPassengerByTicketId(flightTicket.getTicketId()));
		return cancelledTicket;
	}
	
	public Ticket getTicketById(String ticketId) {
		return ticketRepo.findById(ticketId).get();
	}

	public List<Ticket> getTicketByEmail(String email) {
		return ticketRepo.findByEmail(email);
	}
	
	public List<Ticket> getTicketHistory() {
		return ticketRepo.findAll();
	}
	
	public List<Passenger> getPassengerByTicketId(String ticketId) {
		return passengerRepo.findByTicketId(ticketId);
	}

	public List<Schedule> searchTicket(SearchRequest request) {
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SearchRequest> req = new HttpEntity<>(request, requestHeaders);
        ResponseEntity<Schedule[]> response = restTemplate.postForEntity("http://FLIGHT-SERVICE/search", req,
        		Schedule[].class);
		return Arrays.asList(response.getBody());
	}
	
	@KafkaListener(topics = TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
	public void getDataFromKafka(String flightName) {
		List<Ticket> flightTickets = ticketRepo.findByFlightName(flightName);
		List<Passenger> passengers = new ArrayList<Passenger>();
		for (Ticket ticket : flightTickets) {
			passengers = getPassengerByTicketId(ticket.getTicketId());
		}
		if (!passengers.isEmpty()) {
			System.out.println("***************************************************************************");
			System.out.println("The following tickets have been cancelled. Please find below the details:");
			System.out.println(passengers);
			System.out.println("***************************************************************************");

		}
	}

}
