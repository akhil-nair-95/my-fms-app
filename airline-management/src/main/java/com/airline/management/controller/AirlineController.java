package com.airline.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.management.models.Airlines;
import com.airline.management.service.AirlineService;

@RestController
public class AirlineController {

	@Autowired
	private AirlineService airService;
	
	@GetMapping("/test")
	public String testMethod() {
		return "Working Fine";
	}
	
	@GetMapping("/allAirlines")
	public List<Airlines> viewAllAirlines(){
		return airService.getAllAirlines();
	}
	
	@GetMapping("/viewAirline/{name}")
	public Airlines viewAirline(@PathVariable("name") String name) throws Exception{
		return airService.getAirline(name);
	}
	
	@PostMapping("/addAirline")
	public boolean addAirline(@RequestBody Airlines airline) {
		return airService.addAirline(airline);
	}
	
	@PutMapping("/updateAirline")
	public Airlines updateAirline(@RequestBody Airlines airline) throws Exception {
		return airService.updateAirline(airline);
	}
	
	@DeleteMapping("/deleteAirline/{id}")
	public void deleteAirline(@PathVariable("id") String id) throws Exception{
		airService.deleteAirline(id);
	}
	
}
