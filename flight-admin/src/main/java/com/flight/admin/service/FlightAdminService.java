package com.flight.admin.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flight.admin.models.Airline;
import com.flight.admin.models.Flight;
import com.flight.admin.models.FlightSchedule;

@Service
public class FlightAdminService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "kafka_topic_name";
	
	public List<Airline> viewAllAirlines() {
		ResponseEntity<List<Airline>> response = restTemplate.exchange("http://AIRLINES-SERVICE/all", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Airline>>() {
				});
		return response.getBody();
	}

	public Airline saveAirline(Airline airline) {
		airline.setAvailability(1);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Airline> req = new HttpEntity<>(airline, requestHeaders);
		ResponseEntity<Airline> response = restTemplate.postForEntity("http://AIRLINES-SERVICE/add", req,
				Airline.class);
		return response.getBody();
	}
	
	public Airline deleteAirline(Airline airline) {
		airline.setAvailability(0);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Airline> req = new HttpEntity<>(airline, requestHeaders);
		ResponseEntity<Airline> response = restTemplate.postForEntity("http://AIRLINES-SERVICE/delete", req,
				Airline.class);
		return response.getBody();
	}

	public Airline findAirlinesByName(String name) {
		ResponseEntity<Airline> response = restTemplate.exchange("http://AIRLINES-SERVICE/" + name, HttpMethod.GET,
				null, new ParameterizedTypeReference<Airline>() {
				});
		return response.getBody();
	}
	
	public List<Flight> viewAllFlights() {
		ResponseEntity<List<Flight>> response = restTemplate.exchange("http://FLIGHT-SERVICE/allFlights", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Flight>>() {
				});
		return response.getBody();
	}
	
	public Flight findFlightByName(String name) {
		ResponseEntity<Flight> response = restTemplate.exchange("http://FLIGHT-SERVICE/viewFlight?name=" + name,
				HttpMethod.GET, null, new ParameterizedTypeReference<Flight>() {
				});
		return response.getBody();
	}
	
	public Flight findFlightByAirline(String airline) {
		ResponseEntity<Flight> response = restTemplate.exchange("http://FLIGHT-SERVICE/viewFlight?airline=" + airline,
				HttpMethod.GET, null, new ParameterizedTypeReference<Flight>() {
				});
		return response.getBody();
	}
	
	public Flight saveFlight(Flight flight) {
		flight.setAvailability(1);
		HttpHeaders reqHeader = new HttpHeaders();
		reqHeader.setContentType(MediaType.APPLICATION_JSON);
		reqHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> req = new HttpEntity<>(flight, reqHeader);
		ResponseEntity<Flight> response = restTemplate.postForEntity("http://FLIGHT-SERVICE/addFlight", req, Flight.class);
		return response.getBody();
	}
	
	public Flight deleteFlight(Flight flight) {
		flight.setAvailability(0);
		HttpHeaders reqHeader = new HttpHeaders();
		reqHeader.setContentType(MediaType.APPLICATION_JSON);
		reqHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> req = new HttpEntity<>(flight, reqHeader);
		ResponseEntity<Flight> response = restTemplate.postForEntity("http://FLIGHT-SERVICE/deleteFlight", req, Flight.class);
		return response.getBody();
	}

	public List<FlightSchedule> viewAllFlightSchedule() {
		ResponseEntity<List<FlightSchedule>> response = restTemplate.exchange("http://AIRLINES-SERVICE/schedule",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<FlightSchedule>>() {
				});
		return response.getBody();
	}

	public FlightSchedule saveFlightSchedule(FlightSchedule flightSchedule) {
		flightSchedule.setAvailability(1);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<FlightSchedule> req = new HttpEntity<>(flightSchedule, requestHeaders);
		ResponseEntity<FlightSchedule> response = restTemplate.postForEntity("http://AIRLINES-SERVICE/schedule/add",
				req, FlightSchedule.class);
		return response.getBody();
	}
	
	public FlightSchedule cancelFlightSchedule(FlightSchedule flightSchedule) {
		flightSchedule.setAvailability(0);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<FlightSchedule> req = new HttpEntity<>(flightSchedule, requestHeaders);
		ResponseEntity<FlightSchedule> response = restTemplate.postForEntity("http://AIRLINES-SERVICE/schedule/cancel",
				req, FlightSchedule.class);
		notifyUser(flightSchedule.getFlightName());
		return response.getBody();
	}

	private void notifyUser(String flightName) {
		kafkaTemplate.send(TOPIC, flightName);

	}

}
