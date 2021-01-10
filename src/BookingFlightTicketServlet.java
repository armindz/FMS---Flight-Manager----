import management.FlightManagementSystem;
import models.Flight;
import models.FlightTicket;
import booking.BookingFlightTicket;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookingFlightTicketServlet")
public class BookingFlightTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookingFlightTicketServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		
		bookAFlight(request, response);
		requestDispatcher(request,response);
		
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void bookAFlight(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		// convert request Strings to int and char
			
		int flightID = Integer.parseInt(request.getParameter("flightID"));
		char seatRow = request.getParameter("seatRow").charAt(0);
		int seatNumber = Integer.valueOf(request.getParameter("seatNumber"));
		String buyersName = request.getParameter("name");
		
		
		BookingFlightTicket bft = new BookingFlightTicket();
		
		bft.bookAFlight(flightID,seatRow, seatNumber, buyersName);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void requestDispatcher (HttpServletRequest request, HttpServletResponse response) {
		
			FlightManagementSystem flightms = new FlightManagementSystem();
			ArrayList <Flight> flightDataList = flightms.fetchFlightDatabaseContentToList();
			int flightID = Integer.parseInt(request.getParameter("flightID"));
			char seatRow = request.getParameter("seatRow").charAt(0);
			int seatNumber = Integer.valueOf(request.getParameter("seatNumber"));
			String buyersName = request.getParameter("name");
			
		  try {
		  
		  for(int i=0; i < flightDataList.size(); i++) {
			  String flightIdFromList = String.valueOf(flightDataList.get(i).getFlightId()); // convert flightID (int to string) in order to check if it is equal
			//  if( flightID.equals(request.getParameter("flightID"))) {
			  if( flightIdFromList.equals(request.getParameter("flightID"))) {
				  
				  FlightTicket flightTicket = new FlightTicket (flightID, flightDataList.get(i).getAirline(),
						  flightDataList.get(i).getAirport(), flightDataList.get(i).getDestinationAirport(), 
						  flightDataList.get(i).getFlightClass(), flightDataList.get(i).getDateOfFlight(), 
						 seatRow, seatNumber,
						  flightDataList.get(i).getFlightPrice(), buyersName);
				  request.setAttribute("flightTicketData", flightTicket ); 
				  
				  RequestDispatcher rd = request.getRequestDispatcher("FlightTicket.jsp"); 
				  rd.forward(request, response); 
	}
		  }
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
