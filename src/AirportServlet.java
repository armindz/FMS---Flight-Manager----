

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AirlineDatabase;
import database.AirportDatabase;
import management.AirlineManagementSystem;
import management.AirportManagementSystem;

/**
 * Servlet implementation class AirportServlet
 */
@WebServlet("/AirportServlet")
public class AirportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":

		case "/AirportServlet":

			try {
				createAirport(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				removeAirport(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateAirport(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;
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
		response.sendRedirect("AirportList.jsp");
	}

	private void removeAirport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airportCodename = request.getParameter("airportCodename");
		AirportManagementSystem airportms = new AirportManagementSystem();

		
		airportms.removeAirportFromDatabase(airportms.getAirportFromCodename(airportCodename));
		response.sendRedirect("AirportList.jsp");
	}

	private void updateAirport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airportCodename = request.getParameter("airportCodename");
		String airportFullname = request.getParameter("airportFullname");
		String airportType = request.getParameter("airportType");
		String airportCity = request.getParameter("airportCity");
		String airportCountry = request.getParameter("airportCountry");
		AirportDatabase airportdb = new AirportDatabase();
		
		airportdb.updateDatabaseContent(airportCodename, airportFullname, airportType, airportCity, airportCountry);

		response.sendRedirect("AirlineList.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
