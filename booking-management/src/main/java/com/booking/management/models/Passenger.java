package com.booking.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="seat_no",unique=true)
	private int seatNo;
	private String name;
	private String sex;
	private int age;
	private String meal;
	@Column(name="ticket_id")
	private String ticketId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	public Passenger(int id, int seatNo, String name, String sex, int age, String meal, String ticketId) {
		this(seatNo, name, sex, age, meal, ticketId);
		this.id = id;
	}
	
	public Passenger(int seatNo, String name, String sex, int age, String meal, String ticketId) {
		super();
		this.seatNo = seatNo;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.meal = meal;
		this.ticketId = ticketId;
	}
	
	public Passenger() {}
	
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", seatNo=" + seatNo + ", name=" + name + ", sex=" + sex + ", age=" + age
				+ ", meal=" + meal + ", ticketId=" + ticketId + "]";
	}
		
}
