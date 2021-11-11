package com.flightbooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class FlightBookingController {
	
	@GetMapping("/test")
	public String testMethod() {
		return "Working Fine";
	}
	

}
