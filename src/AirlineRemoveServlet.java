

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.AirlineManagementSystem;

/**
 * Servlet implementation class AirlineRemoveServlet
 */
@WebServlet("/AirlineRemoveServlet")
public class AirlineRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AirlineRemoveServlet() {
        super();
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			removeAirline(request, response);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeAirline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String airlineCodename = request.getParameter("airlineCodename");
		AirlineManagementSystem airlinems = new AirlineManagementSystem();

		airlinems.removeAirlineFromDatabase(airlinems.getAirlineFromCodename(airlineCodename));
		response.sendRedirect("AirlineList.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
