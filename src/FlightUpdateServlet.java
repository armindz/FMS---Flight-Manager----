
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FlightDatabase;
import management.FlightManagementSystem;

/**
 * Servlet implementation class FlightUpdateServlet
 */
@WebServlet("/FlightUpdateServlet")
public class FlightUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			updateFlight(request, response);
		}

		catch (SQLException | IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	private void updateFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy hh:mm");
		Calendar dateOfFlight = Calendar.getInstance();
		dateOfFlight.setTime(sdf.parse(request.getParameter("dateOfFlight")));

		String airlineCodename = request.getParameter("airlineCodename");
		String airportCodename = request.getParameter("airportCodename");
		String destinationAirportCodename = request.getParameter("destinationAirportCodename");
		String flightClass = request.getParameter("flightClass");
		char seatRow = (request.getParameter("seatRow").charAt(0));
		int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));
		double flightPrice = Double.parseDouble(request.getParameter("flightPrice"));

		FlightManagementSystem flightms = new FlightManagementSystem();
		FlightDatabase flightdb = new FlightDatabase();

		// get flightID which will be used in updating database content method below
		int flightId = flightms.getFlightIdFromFlightData(airlineCodename, airportCodename, destinationAirportCodename,
				flightClass, dateOfFlight, seatRow, seatNumber, flightPrice);

		// update database content with flight id and other data
		flightdb.updateDatabaseContent(flightId, airlineCodename, airportCodename, destinationAirportCodename,
				flightClass, dateOfFlight, seatRow, seatNumber, flightPrice);

		response.sendRedirect("FlightList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
