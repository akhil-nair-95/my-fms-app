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

	
	@Test
	public void shouldGetFlight() {
		String name = "ABC";
		Assertions.assertThrows(FlightNotFounfException.class, () -> {
			Flights flight = service.findFlight(name);
			Assertions.assertNotNull(flight);
			MatcherAssert.assertThat(flight, Matchers.instanceOf(Flights.class));
			MatcherAssert.assertThat(flight.getCapacity(), Matchers.greaterThan(0));
		});
		

	}

}
