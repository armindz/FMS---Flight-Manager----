
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AirlineDatabase;

@WebServlet("/AirlineUpdateServlet")
public class AirlineUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AirlineUpdateServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			if (true) {
				updateAirline(request, response);
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

	private void updateAirline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airlineCodename = request.getParameter("airlineCodename");
		String airlineCallsign = request.getParameter("airlineCallsign");
		String airlineCountry = request.getParameter("airlineCountry");
		AirlineDatabase airlinedb = new AirlineDatabase();

		airlinedb.updateDatabaseContent(airlineCodename, airlineCallsign, airlineCountry);
		response.sendRedirect("list/airlineList.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
