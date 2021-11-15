package com.flight.management.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flight.management.models.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FlightNotFounfException.class)
	public ResponseEntity<ErrorMessage> handleMovieNotFoundException(FlightNotFounfException e){
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), 500), HttpStatus.OK);
	}

}
