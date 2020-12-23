

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.AirportManagementSystem;

/**
 * Servlet implementation class AirportRemoveServlet
 */
@WebServlet("/AirportRemoveServlet")
public class AirportRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AirportRemoveServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			removeAirport(request, response);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeAirport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airportCodename = request.getParameter("airportCodename");
		AirportManagementSystem airportms = new AirportManagementSystem();

		
		airportms.removeAirportFromDatabase(airportms.getAirportFromCodename(airportCodename));
		response.sendRedirect("AirportList.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
