package com.airline.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.management.dao.AirlineDao;
import com.airline.management.models.Airlines;

@Service
public class AirlineService {
	
	@Autowired
	private AirlineDao airlineRepo;

	public List<Airlines> getAllAirlines() {
		
		return airlineRepo.findAll();
	}

	public Airlines getAirline(String name) throws Exception {
		Airlines airline = airlineRepo.findByName(name);
		if (null != airline) {
			return airline;
		}
		throw new Exception("Airline not available");
	}

	public boolean addAirline(Airlines airline) {
		airlineRepo.save(airline);
		return true;

	}

	public Airlines updateAirline(Airlines airline) throws Exception {
		Optional<Airlines> airlineData = airlineRepo.findById(airline.getAirlineId());
		if (airlineData.isPresent()) {
			return airlineRepo.save(airline);
		} else {
			throw new Exception("Invalid Data entered");
		}
	}

	public void deleteAirline(String id) throws Exception {
		Optional<Airlines> airlineData = airlineRepo.findById(Integer.valueOf(id));
		if (airlineData.isPresent()) {
			airlineRepo.deleteById(Integer.valueOf(id));
		} else {
			throw new Exception("Invalid id");
		}
	}

}
