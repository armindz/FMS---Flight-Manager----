

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String airportCodename = request.getParameter("airportCodename");
		String airportFullname = request.getParameter("airportFullname");
		String airportType = request.getParameter("airportType");
		String airportCity = request.getParameter("airportCity");
		String airportCountry = request.getParameter("airportCountry");
		AirportManagementSystem airportms = new AirportManagementSystem();
		
    	airportms.createAirport(airportCodename, airportFullname, airportType, airportCity, airportCountry);
    		
    	
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html> <body>");
		
		out.println("<h2> Flight manager system </h2>");
		
		out.println(" You've been successfully created airline with data:" + "<br/> Codename:" + request.getParameter("airportCodename") 
		+ "<br/>Fullname: " + request.getParameter("airportFullname") + "<br/>Type:" +   request.getParameter("airportType") + "<br/>City:" +   request.getParameter("airportCity")
		 + "<br/>Country:" +   request.getParameter("airportCountry"));
		
		
		out.println("List of airports:" + airportms.getListOfAirports());
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
