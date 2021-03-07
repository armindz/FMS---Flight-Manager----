
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.AirportManagementSystem;

@WebServlet("/AirportAddServlet")
public class AirportAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AirportAddServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			if (true) {
				createAirport(request, response);
			}

			else {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createAirport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airportCodename = request.getParameter("airportCodename");
		String airportFullname = request.getParameter("airportFullname");
		String airportType = request.getParameter("airportType");
		String airportCity = request.getParameter("airportCity");
		String airportCountry = request.getParameter("airportCountry");
		AirportManagementSystem airportms = new AirportManagementSystem();

		airportms.createAirport(airportCodename, airportFullname, airportType, airportCity, airportCountry);
		response.sendRedirect("list/airportList.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
