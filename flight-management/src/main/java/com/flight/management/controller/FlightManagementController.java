package com.flight.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.management.models.FlightSchedule;
import com.flight.management.models.Flights;
import com.flight.management.models.SearchRequest;
import com.flight.management.service.FlightManagementService;

@RestController
public class FlightManagementController {
	
	@Autowired
	FlightManagementService flightService;
	
	@GetMapping("/allFlights")
	public List<Flights> viewAllFlights(){
		return flightService.viewAllFlights();
	}
	
	@PostMapping("/addFlight")
	public Flights addFlight(@RequestBody Flights flight) {
		return flightService.saveFlight(flight);
	}
	
	@GetMapping("/viewFlight/{name}")
	public Flights viewFlight(@PathVariable("name") String name) throws Exception {
		return flightService.findFlight(name);
	}
	
	@GetMapping("/viewFlight/airline/{name}")
	public List<Flights> viewFlights(@PathVariable("name") String airline){
		return flightService.findFlightByAirline(airline);
	}
	
	@GetMapping("/schedules")
	public List<FlightSchedule> viewSchedules(){
		return flightService.viewAllSchedule();
	}
	
	@PostMapping("/schedule/add")
	public FlightSchedule addSchedule(@RequestBody FlightSchedule schedule) {
		return flightService.saveSchedule(schedule);
	}
	
	@PostMapping("/schedule/cancel")
	public FlightSchedule cancelSchedule(@RequestBody FlightSchedule schedule) throws Exception {
		return flightService.updateSchedule(schedule);
	}
	
	@PostMapping("/schedule/unblock")
	public FlightSchedule unblockSchedule(@RequestBody FlightSchedule schedule) throws Exception {
		return flightService.updateSchedule(schedule);
	}
	
	@PostMapping("/search")
	public List<FlightSchedule> searchSchedule(@RequestBody SearchRequest request) {
		return flightService.findScheduleByDestination(request);
	}

}
