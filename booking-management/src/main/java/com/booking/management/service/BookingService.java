package com.booking.management.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
	@Value("${aws.lambda.invoke.api}")
	private String invokeURL;
	
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
	public void getDataFromKafka(String scheduleId) {
		System.out.println("ScheduleId: " + Integer.valueOf(scheduleId));
		List<Ticket> flightTickets = ticketRepo.findByScheduleId(Integer.valueOf(scheduleId));
		List<Passenger> passengers = new ArrayList<Passenger>();
		for (Ticket ticket : flightTickets) {
			passengers.addAll(getPassengerByTicketId(ticket.getTicketId()));
		}
		if (!passengers.isEmpty()) {
			System.out.println("***************************************************************************");
			System.out.println("The following tickets have been cancelled. Please find below the details:");
			System.out.println(passengers);
			System.out.println("***************************************************************************");

		}
	}

	public String getTicketId(String scheduleId) {
		StringBuilder sb = new StringBuilder();
		List<Ticket> flightTickets = ticketRepo.findByScheduleId(Integer.valueOf(scheduleId));
		List<String> ticketIds = new ArrayList<>();
		for (Ticket ticket : flightTickets) {
			ticketIds.add(ticket.getTicketId());
		}
		String sep = "";
		for (String ticketId : ticketIds) {
			sb.append(sep);
			sb.append(ticketId);
			sep = ", ";
		}
		System.out.println("TicketIds are: " + sb.toString());
		return sb.toString();
	}

	/*
	 * private void notifyUsers(String ticketId, List<Passenger> passengers, String
	 * flightName) {
	 * 
	 * System.out.println("Sending request to AWS Lambda");
	 * 
	 * String requestParams = "?ticketId=" + ticketId;
	 * 
	 * ResponseEntity<String> response = restTemplate.exchange(invokeURL +
	 * requestParams, HttpMethod.GET, null, new ParameterizedTypeReference<String>()
	 * { }); System.out.println("Response from AWS Lambda: " + response); }
	 */

}
