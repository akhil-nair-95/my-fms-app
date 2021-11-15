package com.flight.admin.models;

public class Flight {

	private int id;
	private String name;
	private int capacity;
	private int availability;
	private String airline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Flight(String name, int capacity, int availability, String airline) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.availability = availability;
		this.airline = airline;
	}

	public Flight(int id, String name, int capacity, int availability, String airline) {
		this(name, capacity, availability, airline);
		this.id = id;

	}
	
	public Flight() {
		
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", capacity=" + capacity + ", availability=" + availability
				+ ", airline=" + airline + "]";
	}
	
	
}
