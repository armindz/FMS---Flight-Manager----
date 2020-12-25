

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AirlineDatabase;

/**
 * Servlet implementation class AirlineUpdateServlet
 */
@WebServlet("/AirlineUpdateServlet")
public class AirlineUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AirlineUpdateServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();  
		try {
			if(session != null) {
			updateAirline(request, response);
			}
			
			else {
				PrintWriter out = response.getWriter();  
				out.print("Not logged in!");
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
		response.sendRedirect("AirlineList.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
