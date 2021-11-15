package com.flight.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.management.dao.FlightManagementDao;
import com.flight.management.dao.FlightScheduleRepository;
import com.flight.management.exception.FlightNotFounfException;
import com.flight.management.models.FlightSchedule;
import com.flight.management.models.Flights;
import com.flight.management.models.SearchRequest;

@Service
public class FlightManagementService {
	
	@Autowired
	private FlightManagementDao flightRepo;
	
	@Autowired
	private FlightScheduleRepository scheduleRepo;

	public List<Flights> viewAllFlights() {

		return flightRepo.findAll();
	}
	
	public Flights saveFlight(Flights flight) {
		return flightRepo.save(flight);
	}
	
	public Flights findFlight(String name) throws FlightNotFounfException {
		Flights flight = flightRepo.findByName(name);
		if (null != flight) {
			return flight;
		}
		throw new FlightNotFounfException("Flight not available");
	}
	
	public List<Flights> findFlightByAirline(String airline){
		return flightRepo.findByAirline(airline);
	}
	
	public List<FlightSchedule> viewAllSchedule(){
		return scheduleRepo.findAll();
	}
	
	public FlightSchedule saveSchedule(FlightSchedule schedule) {
		return scheduleRepo.save(schedule);
	}
	
	public List<FlightSchedule> findScheduleByDestination(SearchRequest request) {
		List<FlightSchedule> scheduleList = scheduleRepo.findByStartPlaceAndEndPlace(request.getStartPlace(),
				request.getEndPlace());
		return scheduleList;
	}

}
