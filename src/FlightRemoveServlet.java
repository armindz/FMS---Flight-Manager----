


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.FlightManagementSystem;

/**
 * Servlet implementation class FlightRemoveServlet
 */
@WebServlet("/FlightRemoveServlet")
public class FlightRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightRemoveServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();  
		try {
			if(session != null) {
			removeFlight(request, response);
			}
			
			else {
				PrintWriter out = response.getWriter();  
				out.print("Not logged in!");
			}
		} catch (SQLException | IOException | ParseException e) {
			e.printStackTrace();
		}

	}

	private void removeFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {

		FlightManagementSystem flightms = new FlightManagementSystem();

		int flightID = Integer.parseInt(request.getParameter("product_id"));
		char seatRow = request.getParameter("seatRow").charAt(0);
		int seatNumber = Integer.valueOf(request.getParameter("seatNumber"));
		flightms.removeFlightFromDatabase(flightID);
		response.sendRedirect("FlightList.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
