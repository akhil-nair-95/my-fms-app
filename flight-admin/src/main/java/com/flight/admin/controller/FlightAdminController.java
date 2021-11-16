package com.flight.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.admin.models.Airline;
import com.flight.admin.models.Flight;
import com.flight.admin.models.FlightSchedule;
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
	
	@PostMapping("/cancelFlight")
	public Flight cancelFlight(@RequestBody Flight flight){
		return adminService.deleteFlight(flight);
	}
	
	@GetMapping("/airlines")
	public List<Airline> viewAllAirlines(){
		return adminService.viewAllAirlines();
	}
	
	@PostMapping("/airline/add")
	public Airline saveAirline(@RequestBody Airline airline){
		return adminService.saveAirline(airline);
	}
	
	@GetMapping("/airline/{name}")
	public Airline findAirlineByName(@PathVariable String name){
		return adminService.findAirlinesByName(name);
	}
	
	@PostMapping("/airline/cancel")
	public Airline cancelAirline(@RequestBody Airline airline){
		return adminService.deleteAirline(airline);
	}
	
	@GetMapping("/schedules")
	public List<FlightSchedule> findAllFlightSchedule(){
		return adminService.viewAllFlightSchedule();
	}
	
	@PostMapping("/schedule/add")
	public FlightSchedule saveFlightSchedule(@RequestBody FlightSchedule flightSchedule){
		return adminService.saveFlightSchedule(flightSchedule);
	}
	
	@PostMapping("/schedule/cancel")
	public FlightSchedule cancelFlightSchedule(@RequestBody FlightSchedule flightSchedule){
		System.out.println("Inside Cancel Schedule cntroller");
		return adminService.cancelFlightSchedule(flightSchedule);
	}

}
