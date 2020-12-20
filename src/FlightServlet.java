
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AirportDatabase;
import database.FlightDatabase;
import management.AirportManagementSystem;
import management.FlightManagementSystem;
import models.Airline;
import models.Airport;
import models.Flight;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		try {
			createFlight(request, response);
		}

		catch (SQLException | IOException | ParseException e) {
			e.printStackTrace();
		}

	}

	private void createFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		Date date = (Date) formatter.parse(request.getParameter("dateOfFlight"));
		Calendar dateOfFlight = Calendar.getInstance();
		dateOfFlight.setTime(date);
		System.out.println("Date" + date);
		System.out.println("Datetime Local" + request.getParameter("dateOfFlight"));

		String airlineCodename = request.getParameter("airlineCodename");
		String airportCodename = request.getParameter("airportCodename");
		String destinationAirportCodename = request.getParameter("destinationAirportCodename");
		String flightClass = request.getParameter("flightClass");
		char seatRow = (request.getParameter("seatRow").charAt(0));
		int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));
		double flightPrice = Double.parseDouble(request.getParameter("flightPrice"));

		FlightManagementSystem flightms = new FlightManagementSystem();
		flightms.createFlight(airlineCodename, airportCodename, destinationAirportCodename, flightClass, dateOfFlight,
				seatRow, seatNumber, flightPrice);

		response.sendRedirect("FlightList.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
