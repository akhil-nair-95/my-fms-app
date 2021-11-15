package com.airport.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.management.models.Airport;

public interface AirportDao extends JpaRepository<Airport, Integer> {

}
