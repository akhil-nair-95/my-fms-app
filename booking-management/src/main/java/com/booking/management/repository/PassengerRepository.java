package com.booking.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.management.models.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	List<Passenger> findByTicketId(String ticketId);

}
