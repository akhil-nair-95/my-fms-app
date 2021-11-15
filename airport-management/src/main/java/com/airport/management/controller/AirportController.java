package com.airport.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.management.models.Airport;
import com.airport.management.service.AirportService;

@RestController
//@RequestMapping("/airport")
public class AirportController {

	@Autowired
	private AirportService airService;
	
	@GetMapping("/test")
	public String testMethod() {
		return "Working Fine";
	}
	
	@GetMapping("/allAirport")
	public List<Airport> viewAllAirport(){
		return airService.getAllAirports();
	}
	
	@GetMapping("/viewAirport/{id}")
	public Airport viewAirport(@PathVariable("id") String id) throws Exception{
		return airService.getAirport(id);
	}
	
	@PostMapping("/addAirport")
	public void addAirport(@RequestBody Airport airport) {
		airService.addAirport(airport);
	}
	
	@PutMapping("/updateAirport")
	public Airport updateAirport(@RequestBody Airport airport) throws Exception {
		return airService.updateAirport(airport);
	}
	
	@DeleteMapping("/deleteAirport/{id}")
	public void deleteAirport(@PathVariable("id") String id) throws Exception{
		airService.deleteAirport(id);
	}
	
}
