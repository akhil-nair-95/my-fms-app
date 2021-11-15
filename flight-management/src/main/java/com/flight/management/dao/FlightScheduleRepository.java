package com.flight.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.models.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {
	
	List<FlightSchedule> findByStartPlaceAndEndPlace(String startPlace, String endPlace);
	
	List<FlightSchedule> findByScheduledDay(String scheduledDay);

}
