package com.flight.management;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.management.exception.FlightNotFounfException;
import com.flight.management.models.Flights;
import com.flight.management.service.FlightManagementService;

@SpringBootTest
class FlightManagementApplicationTests {

	@Autowired
	private FlightManagementService service;

	
//	@Test
//	public void shouldGetFlight() {
//		String name = "ABC";
//		Flights flight = service.findFlight(name);
//			System.out.println(flight);
//			Assertions.assertNotNull(flight);
//			Assertions.assertNotNull(flight.getCapacity());
//			MatcherAssert.assertThat(flight, Matchers.instanceOf(Flights.class));
//			MatcherAssert.assertThat(flight.getCapacity(), Matchers.greaterThan(0));
//		
//	}

}
