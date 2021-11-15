package com.airline.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AirlineManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineManagementApplication.class, args);
	}

}
