package com.booking.management.util;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.booking.management.models.Passenger;

@Service
public class EmailSenderUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String from, String to, String subject, String ticketId, String name, List<Passenger> passengers) {
	    try {
	    	
			/*
			 * JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			 * mailSender.setHost("smtp.ethereal.email"); mailSender.setPort(587);
			 * mailSender.setUsername("elias.homenick53@ethereal.email");
			 * mailSender.setPassword("hd2SpfF53FKDd1gNr2");
			 * 
			 * Properties properties = new Properties();
			 * properties.setProperty("mail.smtp.auth", "true");
			 * properties.setProperty("mail.smtp.starttls.enable", "true");
			 * 
			 * mailSender.setJavaMailProperties(properties);
			 */
	      System.out.println("Creating message content...");
	      String content = getMessage(ticketId, name, passengers);
	      System.out.println("Created message content");
	      MimeMessage mimeMessage = mailSender.createMimeMessage();
	      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

	      helper.setFrom(from);
	      helper.setTo(to);
	      helper.setSubject(subject);
	      mimeMessage.setContent(content, "text/html");
	      
	      mailSender.send(mimeMessage);
	      System.out.println("Email sent succesfully");
	    } catch (MessagingException e) {
	    	System.out.println("Error while sending email: " + e.getMessage());
	    }
	  }
	
	public String getMessage(String ticketId, String name, List<Passenger> passengers) {
		String tableheader = "  <tr>\r\n"
				+ "    <th>Name</th>\r\n"
				+ "    <th>Seat No.</th>\r\n"
				+ "    <th>Age</th>\r\n"
				+ "    <th>Gender</th>\r\n"
				+ "  </tr>";
		String tableContent = "";
		StringBuilder sb = new StringBuilder();
		for(Passenger passenger : passengers) {			
			tableContent = "  <tr>\r\n"
					+ "    <td>" + passenger.getName() + "</td>\r\n"
					+ "    <td>" + passenger.getSeatNo() + "</td>\r\n"
					+ "    <td>" + passenger.getAge() + "</td>\r\n"
					+ "    <td>" + passenger.getSex() + "</td>\r\n"
					+ "  </tr>";
			sb.append(tableContent);
		}
		String table = "<table style=\"margin-left:1px; margin-top: 5px; margin-bottom: 5px\">" + tableheader + sb.toString() + "</table>";
		
		String msg = "  \r\n"
				+ "<!doctype html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<style>\r\n"
				+ "table {\r\n"
				+ "  font-family: arial, sans-serif;\r\n"
				+ "  border-collapse: collapse;\r\n"
				+ "  width: 100%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "td, th {\r\n"
				+ "  border: 1px solid #dddddd;\r\n"
				+ "  text-align: left;\r\n"
				+ "  padding: 8px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "tr:nth-child(even) {\r\n"
				+ "  background-color: #dddddd;\r\n"
				+ "}\r\n"
				+ "</style>\r\n"
				+ "</head>"
				+ "  <body class=\"\">\r\n"
				+ "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n"
				+ "      <tr>\r\n"
				+ "        <td>&nbsp;</td>\r\n"
				+ "        <td class=\"container\">\r\n"
				+ "          <div class=\"content\">\r\n"
				+ "\r\n"
				+ "            <!-- START CENTERED WHITE CONTAINER -->\r\n"
				+ "            <table role=\"presentation\" class=\"main\">\r\n"
				+ "\r\n"
				+ "              <!-- START MAIN CONTENT AREA -->\r\n"
				+ "              <tr>\r\n"
				+ "                <td class=\"wrapper\">\r\n"
				+ "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                    <tr>\r\n"
				+ "					  <p style=\"text-align:center; font-weight:bolder; color:white; border-style: solid; background-color:red\">Ticket Cancelled !!!!</p>\r\n"
				+ "                      <td style=\"border-style: solid; background-color:palegreen\">\r\n"
				+ "                        <p style=\"margin-left:5px\">Hi "+ name +",</p>\r\n"
				+ "                        <p style=\"margin-left:5px\">Your flight schedule for ticket with PNR <span style=font-weight:bolder; color:white>" + ticketId + "</span> has been cancelled.</p>\r\n"
				+ "                        <p style=\"margin-left:5px\">Sorry for the inconvenience caused. Please search and book a new ticket.</p>\r\n"
				+ "                        <p style=\"margin-left:5px\">Please find below the passenger details given below.</p>\r\n"
				+ "                        <p style=\"margin-left:5px\">Happing flying! Happy Journey!</p>\r\n"
				+ "                      </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                  </table>\r\n"
				+ "                </td>\r\n"
				+ "              </tr>\r\n"
				+ "\r\n"
				+ "            <!-- END MAIN CONTENT AREA -->\r\n"
				+ "            </table>\r\n"
				+ table
				+ "\r\n"
				+ "            <!-- START FOOTER -->\r\n"
				+ "            <div class=\"footer\">\r\n"
				+ "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                <tr>\r\n"
				+ "                  <td class=\"content-block\">\r\n"
				+ "				  <br><br>\r\n"
				+ "                    <span style=\"font-style:italic; margin-left:5px\">Regards,</span>\r\n"
				+ "                    <br> <span style=\"margin-left:5px\">Flight Booking</span>\r\n"
				+ "                  </td>\r\n"
				+ "                </tr>\r\n"
				+ "              </table>\r\n"
				+ "            </div>\r\n"
				+ "            <!-- END FOOTER -->\r\n"
				+ "\r\n"
				+ "          </div>\r\n"
				+ "        </td>\r\n"
				+ "        <td>&nbsp;</td>\r\n"
				+ "      </tr>\r\n"
				+ "    </table>\r\n"
				+ "  </body>\r\n"
				+ "</html>";
		return msg;
	}
	
	/*
	 * public static void main(String[] args) { EmailSenderUtil email = new
	 * EmailSenderUtil(); String name = "Akhil"; String ticket = "ABC_00000456790";
	 * String html= "  \r\n" + "<!doctype html>\r\n" + "<html>\r\n" +
	 * "  <body class=\"\">\r\n" +
	 * "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n"
	 * + "      <tr>\r\n" + "        <td>&nbsp;</td>\r\n" +
	 * "        <td class=\"container\">\r\n" +
	 * "          <div class=\"content\">\r\n" + "\r\n" +
	 * "            <!-- START CENTERED WHITE CONTAINER -->\r\n" +
	 * "            <table role=\"presentation\" class=\"main\">\r\n" + "\r\n" +
	 * "              <!-- START MAIN CONTENT AREA -->\r\n" +
	 * "              <tr>\r\n" + "                <td class=\"wrapper\">\r\n" +
	 * "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
	 * + "                    <tr>\r\n" +
	 * "					  <p style=\"text-align:center; font-weight:bolder; color:white; border-style: solid; background-color:red\">Ticket Cancelled !!!!</p>\r\n"
	 * +
	 * "                      <td style=\"border-style: solid; background-color:palegreen\">\r\n"
	 * + "                        <p style=\"margin-left:5px\">Hi "+ name
	 * +",</p>\r\n" +
	 * "                        <p style=\"margin-left:5px\">Your flight schedule for ticket with PNR <span style=font-weight:bolder; color:white>"
	 * + ticket + "</span> has been cancelled.</p>\r\n" +
	 * "                        <p style=\"margin-left:5px\">Sorry for the inconvenience caused. Please search and book a new ticket.</p>\r\n"
	 * +
	 * "                        <p style=\"margin-left:5px\">Happing flying! Happy Journey!</p>\r\n"
	 * + "                      </td>\r\n" + "                    </tr>\r\n" +
	 * "                  </table>\r\n" + "                </td>\r\n" +
	 * "              </tr>\r\n" + "\r\n" +
	 * "            <!-- END MAIN CONTENT AREA -->\r\n" + "            </table>\r\n"
	 * + "            <!-- END CENTERED WHITE CONTAINER -->\r\n" + "\r\n" +
	 * "            <!-- START FOOTER -->\r\n" +
	 * "            <div class=\"footer\">\r\n" +
	 * "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
	 * + "                <tr>\r\n" +
	 * "                  <td class=\"content-block\">\r\n" +
	 * "				  <br><br>\r\n" +
	 * "                    <span style=\"font-style:italic; margin-left:5px\">Regards,</span>\r\n"
	 * +
	 * "                    <br> <span style=\"margin-left:5px\">Flight Booking</span>\r\n"
	 * + "                  </td>\r\n" + "                </tr>\r\n" +
	 * "              </table>\r\n" + "            </div>\r\n" +
	 * "            <!-- END FOOTER -->\r\n" + "\r\n" + "          </div>\r\n" +
	 * "        </td>\r\n" + "        <td>&nbsp;</td>\r\n" + "      </tr>\r\n" +
	 * "    </table>\r\n" + "  </body>\r\n" + "</html>"; String msg =
	 * "Hi ABC, \n \n" +
	 * "Yourticket ABC_00778967 for journey from ABC to CDB on 12/05/2021 at 12:00 has been cancelled."
	 * ; email.sendMail("akhil@gmail.com", "abc@gmail.com", "Test Email", html); }
	 */

}
