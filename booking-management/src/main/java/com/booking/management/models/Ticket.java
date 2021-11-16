package com.booking.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.booking.management.util.TicketGenerator;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
	@GenericGenerator(name="sequence_generator", strategy = "com.booking.management.util.TicketGenerator", parameters = {
			@Parameter(name = TicketGenerator.INCREMENT_PARAM, value="50"),
			@Parameter(name = TicketGenerator.VALUE_PREFIX_PARAMETER, value="ABC_"),
			@Parameter(name = TicketGenerator.NUMBER_FORMAT_PARAMETER, value="%05d") })
	@Column(name="ticket_id")
	private String ticketId;
	@Column(name="airline_name")
	private String airlineName;
	@Column(name="flight_name")
	private String flightName;
	@Column(name="start_place")
	private String startPlace;
	@Column(name="end_place")
	private String endPlace;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
	@Column(name="user_name")
	private String userName;
	private String email;
	@Column(name="journey_date")
	private String journeyDate;
	@Column(name="total_seat")
	private int totalSeats;
	@Column(name="total_cost")
	private float totalCost;
	private String status;
	
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Ticket(String ticketId, String airlineName, String flightName, String startPlace, String endPlace,
			String startTime, String endTime, String userName, String email, String journeyDate, int totalSeats,
			float totalCost, String status) {
		this(airlineName, flightName, startPlace, endPlace, startTime, endTime, userName, email, journeyDate, totalSeats, totalCost, status);
		this.ticketId = ticketId;
	}
	
	public Ticket(String airlineName, String flightName, String startPlace, String endPlace,
			String startTime, String endTime, String userName, String email, String journeyDate, int totalSeats,
			float totalCost, String status) {
		super();
		this.airlineName = airlineName;
		this.flightName = flightName;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userName = userName;
		this.email = email;
		this.journeyDate = journeyDate;
		this.totalSeats = totalSeats;
		this.totalCost = totalCost;
		this.status = status;
	}

	public Ticket() {}
	
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", airlineName=" + airlineName + ", flightName=" + flightName
				+ ", startPlace=" + startPlace + ", endPlace=" + endPlace + ", startTime=" + startTime + ", endTime="
				+ endTime + ", userName=" + userName + ", email=" + email + ", journeyDate=" + journeyDate
				+ ", totalSeats=" + totalSeats + ", totalCost=" + totalCost + ", status=" + status
				+ "]";
	}
}
