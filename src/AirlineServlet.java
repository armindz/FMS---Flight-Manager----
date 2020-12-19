

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AirlineDatabase;
import management.AirlineManagementSystem;

/**
 * Servlet implementation class AirlineServlet
 */
@WebServlet("/AirlineServlet")
public class AirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
	
		switch (action) {
		case "/new":

		case "/AirlineServlet":

			try {
				createAirline(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				removeAirline(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateAirline(request, response);
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		}
		/*
		 * private void showNewForm (HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { RequestDispatcher dispatcher
		 * = request.getRequestDispatcher(""); dispatcher.forward(request, response); }
		 */
	}

	private void createAirline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airlineCodename = request.getParameter("airlineCodename");
		String airlineCallsign = request.getParameter("airlineCallsign");
		String airlineCountry = request.getParameter("airlineCountry");
		AirlineManagementSystem airlinems = new AirlineManagementSystem();

		airlinems.createAirline(airlineCodename, airlineCallsign, airlineCountry);
		response.sendRedirect("AirlineList.jsp");
	}

	private void removeAirline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airlineCodename = request.getParameter("airlineCodename");
		AirlineManagementSystem airlinems = new AirlineManagementSystem();

		airlinems.removeAirlineFromDatabase(airlinems.getAirlineFromCodename(airlineCodename));
		response.sendRedirect("AirlineList.jsp");
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
