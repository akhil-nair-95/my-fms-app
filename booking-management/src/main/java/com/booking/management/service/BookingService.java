package com.booking.management.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.management.models.FlightTicket;
import com.booking.management.models.Passenger;
import com.booking.management.models.Schedule;
import com.booking.management.models.SearchRequest;
import com.booking.management.models.Ticket;
import com.booking.management.repository.PassengerRepository;
import com.booking.management.repository.TicketRepository;
import com.booking.management.util.EmailSenderUtil;

@Service
public class BookingService {
	
//	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EmailSenderUtil emailSender;
	
	@Value("${aws.lambda.invoke.api}")
	private String invokeURL;
	
	private static final String TOPIC = "kafka_topic_name";
	
	public FlightTicket bookTicket(FlightTicket ticket) {
	ticket.getTicket().setStatus("Active");
	Ticket ticketSaved = ticketRepo.save(ticket.getTicket());
	List<Passenger> savedPassenger = new ArrayList<>();
	
	ticket.getPassenger().forEach(pass ->{ 
		pass.setTicketId(ticketSaved.getTicketId());
		Passenger passenger = passengerRepo.save(pass);
		savedPassenger.add(passenger);
	});
	
	FlightTicket bookedTicket = new FlightTicket();
	bookedTicket.setTicket(ticketSaved);
	bookedTicket.setPassenger(savedPassenger);
	return bookedTicket;
	}

	public FlightTicket cancelTicket(Ticket ticket) {
		FlightTicket cancelledTicket = new FlightTicket();
		Ticket ticketDetails = ticket; 
		ticketDetails.setStatus("Cancelled");
		Ticket flightTicket = ticketRepo.save(ticketDetails);
		cancelledTicket.setTicket(flightTicket);
		cancelledTicket.setPassenger(getPassengerByTicketId(flightTicket.getTicketId()));
		return cancelledTicket;
	}
	
	public Ticket getTicketById(String ticketId) {
		return ticketRepo.findById(ticketId).get();
	}

	public List<Ticket> getTicketByEmail(String email) {
		return ticketRepo.findByEmail(email);
	}
	
	public List<Ticket> getTicketHistory() {
		return ticketRepo.findAll();
	}
	
	public List<Passenger> getPassengerByTicketId(String ticketId) {
		return passengerRepo.findByTicketId(ticketId);
	}

	public List<Schedule> searchTicket(SearchRequest request) {
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SearchRequest> req = new HttpEntity<>(request, requestHeaders);
        ResponseEntity<Schedule[]> response = restTemplate.postForEntity("http://FLIGHT-SERVICE/search", req,
        		Schedule[].class);
		return Arrays.asList(response.getBody());
	}
	
	@KafkaListener(topics = TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
	public void getDataFromKafka(String scheduleId) {
		System.out.println("ScheduleId: " + Integer.valueOf(scheduleId));
		List<Ticket> flightTickets = ticketRepo.findByScheduleId(Integer.valueOf(scheduleId));
		List<Passenger> passengers = new ArrayList<Passenger>();
		for (Ticket ticket : flightTickets) {
			List<Passenger> passengerList = getPassengerByTicketId(ticket.getTicketId());
			sendEmail(ticket.getEmail(), ticket.getUserName(), ticket.getTicketId(), passengerList,
					ticket.getJourneyDate());
			passengers.addAll(passengerList);
		}
		System.out.println("Passengers size: " + passengers.size());
		if (!passengers.isEmpty()) {
			System.out.println("***************************************************************************");
			System.out.println("The following tickets have been cancelled. Please find below the details:");
			System.out.println(passengers);
			System.out.println("***************************************************************************");

		}
	}
	
	public void sendEmail(String toEmail, String name, String ticketId, List<Passenger> passengers,
			String journeyDate) {
		System.out.println("Inside method to send email notification");
		String from = "flightbooking@aero.com";
		String subject = "Scheduled cancelled for journey on " + journeyDate;
		emailSender.sendMail(from, toEmail, subject, ticketId, name, passengers);
	}
	
	/*
	 * public static void main(String[] args) { System.out.println("Inside main");
	 * try { testMethod("4"); } catch (SQLException e) {
	 * System.out.println("Error occured in main"); } } public static void
	 * testMethod(String scheduleId) throws SQLException { Connection con =
	 * getRemoteConnection(); Statement stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery("SELECT * FROM flight_app.ticket where schedule_id='4';");
	 * List<Ticket> tickets = new ArrayList<>(); while(rs.next()) { Ticket ticket =
	 * new Ticket(); ticket.setAirlineName(rs.getString("airline_name"));
	 * ticket.setTicketId(rs.getString("ticket_id"));
	 * System.out.println("TicketId: " + rs.getString("ticket_id"));
	 * tickets.add(ticket); } System.out.println("Result: " + tickets);
	 * System.out.println("Result size: " + tickets.size());
	 * System.out.println("Finding passengers"); List<Passenger> passengers = new
	 * ArrayList<Passenger>(); for(Ticket ticketData : tickets) { String query =
	 * "SELECT * FROM flight_app.passenger where ticket_id='" +
	 * ticketData.getTicketId() + "';"; System.out.println("Query: " + query);
	 * ResultSet rspass = stmt.executeQuery( query); List<Passenger> passengerList =
	 * new ArrayList<>(); while(rspass.next()) { Passenger passenger = new
	 * Passenger(); passenger.setName(rspass.getString("name"));
	 * passenger.setTicketId(rspass.getString("ticket_id"));
	 * passenger.setSeatNo(rspass.getInt("seat_no"));
	 * passenger.setAge(rspass.getInt("age"));
	 * passenger.setMeal(rspass.getString("meal"));
	 * passenger.setSex(rspass.getString("sex")); passengerList.add(passenger);
	 * System.out.println("try: " + passengerList); }
	 * passengers.addAll(passengerList); }
	 * 
	 * System.out.println("Passengers size: " + passengers.size());
	 * System.out.println(passengers);
	 * 
	 * // List<Ticket> flightTickets =
	 * ticketRepo.findByScheduleId(Integer.valueOf(scheduleId)); //
	 * System.out.println("Size of tickets booked: " + flightTickets.size()); //
	 * List<Passenger> passengers = new ArrayList<Passenger>(); // for (Ticket
	 * ticket : flightTickets) { //
	 * passengers.addAll(getPassengerByTicketId(ticket.getTicketId())); // } //
	 * System.out.println("Size of passengers: " + passengers.size()); // if
	 * (!passengers.isEmpty()) { // System.out.println("Result: " + passengers); //
	 * }else { // System.out.println("No data"); // } }
	 * 
	 * private static Connection getRemoteConnection() { if (true) { try {
	 * Class.forName("com.mysql.jdbc.Driver"); // String dbName =
	 * System.getenv("RDS_DB_NAME"); String userName = "admin"; String password =
	 * "Admin12345"; // String hostname = System.getenv("RDS_HOSTNAME"); String port
	 * = System.getenv("RDS_PORT"); String jdbcUrl =
	 * "jdbc:mysql://flight-admin-aws.cyif5r1ubywm.us-east-2.rds.amazonaws.com:3306/flight_app"
	 * + "?user=" + userName + "&password=" + password;
	 * System.out.println("Trying to get connection...."); Connection con =
	 * DriverManager.getConnection(jdbcUrl);
	 * System.out.println("Remote connection successful."); return con; } catch
	 * (ClassNotFoundException e) { System.out.println("Error: " + e.getMessage());}
	 * catch (SQLException e) { System.out.println("Error: " + e.getMessage());} }
	 * return null; }
	 */ 
	public String getTicketId(String scheduleId) {
		StringBuilder sb = new StringBuilder();
		List<Ticket> flightTickets = ticketRepo.findByScheduleId(Integer.valueOf(scheduleId));
		List<String> ticketIds = new ArrayList<>();
		for (Ticket ticket : flightTickets) {
			ticketIds.add(ticket.getTicketId());
		}
		String sep = "";
		for (String ticketId : ticketIds) {
			sb.append(sep);
			sb.append(ticketId);
			sep = ", ";
		}
		System.out.println("TicketIds are: " + sb.toString());
		return sb.toString();
	}

	/*
	 * private void notifyUsers(String ticketId, List<Passenger> passengers, String
	 * flightName) {
	 * 
	 * System.out.println("Sending request to AWS Lambda");
	 * 
	 * String requestParams = "?ticketId=" + ticketId;
	 * 
	 * ResponseEntity<String> response = restTemplate.exchange(invokeURL +
	 * requestParams, HttpMethod.GET, null, new ParameterizedTypeReference<String>()
	 * { }); System.out.println("Response from AWS Lambda: " + response); }
	 */

}
