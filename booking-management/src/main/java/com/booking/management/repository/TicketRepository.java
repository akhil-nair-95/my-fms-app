package com.booking.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.management.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {

	List<Ticket> findByEmail(String email);
	
	List<Ticket> findByFlightName(String flightName);
	
	List<Ticket> findByScheduleId(int scheduleId);

}
