package com.flight.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.admin.models.Flight;
import com.flight.admin.service.FlightAdminService;

@RestController
public class FlightAdminController {
	
	@Autowired
	private FlightAdminService adminService;
	
	@GetMapping("/flights")
	public List<Flight> viewAllFlights(){
		return adminService.viewAllFlights();
	}
	
	@PostMapping("/addFlight")
	public Flight addFlight(@RequestBody Flight flight) {
		return adminService.saveFlight(flight);
	}

}
