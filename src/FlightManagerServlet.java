
import management.AirlineManagementSystem;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlightManagerServlet
 */
@WebServlet("/FlightManagerServlet")
public class FlightManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		String airlineCodename = request.getParameter("airlineCodename");
		String airlineCallsign = request.getParameter("airlineCallsign");
		String airlineCountry = request.getParameter("airlineCountry");
		AirlineManagementSystem airlinems = new AirlineManagementSystem();
		
    	airlinems.createAirline(airlineCodename, airlineCallsign, airlineCountry);
    		
    	
    	
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html> <body>");
		
		out.println("<h2> Flight manager system </h2>");
		
		out.println(" You've been successfully created airline with data:" + "<br/> Codename:" + request.getParameter("airlineCodename") + "<br/>Callsign: " + request.getParameter("airlineCallsign") + "<br/>Country:" +   request.getParameter("airlineCountry"));
		out.println("</body></html> ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
