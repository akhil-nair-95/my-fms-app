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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flight.admin.models.Flight;

@Service
public class FlightAdminService {
	
	@Autowired
	private RestTemplate restTemplate;
	
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
		HttpHeaders reqHeader = new HttpHeaders();
		reqHeader.setContentType(MediaType.APPLICATION_JSON);
		reqHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> req = new HttpEntity<>(flight, reqHeader);
		ResponseEntity<Flight> response = restTemplate.postForEntity("http://FLIGHT-SERVICE/addFlight", req, Flight.class);
		return response.getBody();
	}

}
