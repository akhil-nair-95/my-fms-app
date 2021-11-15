package com.airline.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.management.models.Airlines;

public interface AirlineDao extends JpaRepository<Airlines, Integer> {
	
	Airlines findByName(String name);

}
