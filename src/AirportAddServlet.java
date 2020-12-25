

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.AirportManagementSystem;

/**
 * Servlet implementation class AirportAddServlet
 */
@WebServlet("/AirportAddServlet")
public class AirportAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AirportAddServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
		try {
			if(session != null) {
			createAirport(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
