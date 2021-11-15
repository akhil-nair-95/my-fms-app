package com.flight.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.management.models.Flights;

public interface FlightManagementDao extends JpaRepository<Flights, Integer>{
	
	Flights findByName(String name);
	
	List<Flights> findByAirline(String airline);

}
