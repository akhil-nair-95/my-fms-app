package com.airport.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.airport.management.dao.AirportDao;
import com.airport.management.models.Airport;

@Service
public class AirportService {
	
	@Autowired
	private AirportDao airport;

	public List<Airport> getAllAirports() {
		
		return airport.findAll();
	}

	@Cacheable(value = "airportById", key="#id")
	public Airport getAirport(String id) throws Exception {
		System.out.println("view airport service");
		Optional<Airport> airportData = airport.findById(Integer.valueOf(id));
		if (airportData.isPresent()) {
			return airportData.get();
		}
		throw new Exception("Airport not available");
	}

	public void addAirport(Airport airportEntry) {
		airport.save(airportEntry);

	}

	public Airport updateAirport(Airport airportEntry) throws Exception {
		Optional<Airport> airportData = airport.findById(airportEntry.getAirportId());
		if (airportData.isPresent()) {
			return airport.save(airportEntry);
		} else {
			throw new Exception("Invalid Data entered");
		}
	}

	public void deleteAirport(String id) throws Exception {
		Optional<Airport> airportData = airport.findById(Integer.valueOf(id));
		if (airportData.isPresent()) {
			airport.deleteById(Integer.valueOf(id));
		} else {
			throw new Exception("Invalid id");
		}
	}

}
