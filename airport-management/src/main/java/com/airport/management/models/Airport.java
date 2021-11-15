package com.airport.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport_details")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int airportId;
	private String airportLocation;
	private String airportName;

	public Airport() {
		// Unparameterized constructor
	}

	public Airport(String airportLocation, String airportName) {
		super();
		this.airportLocation = airportLocation;
		this.airportName = airportName;
	}

	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

}
