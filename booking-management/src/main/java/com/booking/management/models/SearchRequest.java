package com.booking.management.models;

public class SearchRequest {
	
	private String startPlace;
	private String endPlace;
	private String journeyDate;
	
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
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public SearchRequest(String startPlace, String endPlace, String journeyDate) {
		super();
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.journeyDate = journeyDate;
	}
	
	public SearchRequest() {}
	
	@Override
	public String toString() {
		return "SearchRequest [startPlace=" + startPlace + ", endPlace=" + endPlace + ", journeyDate=" + journeyDate
				+ "]";
	}
	
}
