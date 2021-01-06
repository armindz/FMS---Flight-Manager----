import management.FlightManagementSystem;
import booking.BookingFlightTicket;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookingFlightTicketServlet")
public class BookingFlightTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookingFlightTicketServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			FlightManagementSystem flightms = new FlightManagementSystem();
			flightms.displayAvailableSeatsInSpecificFlight(Integer.parseInt(request.getParameter("flightID")));
			System.out.println("REZULTAT");
		bookAFlight(request, response);
		
		response.sendRedirect("FlightList.jsp");
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void bookAFlight(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		// convert request Strings to int and char
			
		int flightID = Integer.parseInt(request.getParameter("flightID"));
		char seatRow = request.getParameter("seatRow").charAt(0);
		int seatNumber = Integer.valueOf(request.getParameter("seatNumber"));
		
		
		
		BookingFlightTicket bft = new BookingFlightTicket();
		
		bft.bookAFlight(flightID,seatRow, seatNumber);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
