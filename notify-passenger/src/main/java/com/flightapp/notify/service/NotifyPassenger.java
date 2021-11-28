package com.flightapp.notify.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class NotifyPassenger {
    
//	@Autowired
//	private TicketRepository ticketRepo;
//	
//	@Autowired
//	private PassengerRepository passengerRepo;
	
	
	  @Value("${sns.topic.arn}") 
	  private String snsTopicARN;
	  
	  @Autowired 
	  private AmazonSNSClient amazonSNSclient;
	 
	
    @Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> handleCancelledSchedules() {
		Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> f = (event) -> {
			// path?ticketId="ticketId"
			String ticketId = event.getQueryStringParameters().get("ticketId");
			String msg = "Tickets against ticket id " + ticketId
					+ " has been cancelled. Sorry for the inconvenience. \n "
					+ "Please search and book another ticket.";

			PublishRequest publishRequest = new PublishRequest(snsTopicARN, msg, "Schedule Cancelled !!!!");
			amazonSNSclient.publish(publishRequest);

			return new APIGatewayProxyResponseEvent().withBody("Successfully notified users").withStatusCode(200);
		};
		return f;
	}

}
