
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booking.BookingFlightTicket;
import models.FlightTicket;

@WebServlet("/FlightTicketRemoveServlet")
public class FlightTicketRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightTicketRemoveServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (true) {
			removeFlightTicket(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}

	}

	protected void removeFlightTicket(HttpServletRequest request, HttpServletResponse response) {

		BookingFlightTicket bft = new BookingFlightTicket();
		ArrayList<FlightTicket> flightTicketDataToList = bft.getListOfFlightTickets();

		try {
			int flightID = Integer.parseInt(request.getParameter("product_id"));
			char seatRow = request.getParameter("seatRow").charAt(0);
			int seatNumber = Integer.valueOf(request.getParameter("seatNumber"));

			for (int i = 0; i < flightTicketDataToList.size(); i++) {

				if (flightTicketDataToList.get(i).getFlightId() == flightID
						&& flightTicketDataToList.get(i).getSeatRow() == seatRow
						&& flightTicketDataToList.get(i).getSeatNumber() == seatNumber) {

					bft.removeFlightTicketFromDatabase(flightID, seatRow, seatNumber);
					response.sendRedirect("list/flightList.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
