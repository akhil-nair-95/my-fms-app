package com.flight.management.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String airline;
	private String flightName;
	private String startPlace;
	private String endPlace;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String scheduledDay;
	private float price;
	private int availability;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScheduledDay() {
		return scheduledDay;
	}

	public void setScheduledDay(String scheduledDay) {
		this.scheduledDay = scheduledDay;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public FlightSchedule(String airline, String flightName, String startPlace, String endPlace, String startDate,
			String startTime, String endDate, String endTime, String scheduledDay, float price, int availability) {
		super();
		this.airline = airline;
		this.flightName = flightName;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.scheduledDay = scheduledDay;
		this.price = price;
		this.availability = availability;
	}

	public FlightSchedule() {

	}

	@Override
	public String toString() {
		return "FlightSchedule [id=" + id + ", airline=" + airline + ", flightName=" + flightName + ", startPlace="
				+ startPlace + ", endPlace=" + endPlace + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", endDate=" + endDate + ", endTime=" + endTime + ", scheduledDay=" + scheduledDay + ", price="
				+ price + ", availability=" + availability + "]";
	}

}
