import java.util.Scanner;

import booking.BookingFlightTicket;
import management.AirlineManagementSystem;
import management.AirportManagementSystem;
import management.FlightManagementSystem;

public class FlightManagerMain {

	public static void displayMenu() {
		System.out.println("\n 1) Create airline \n 2) Create airport \n 3) Create flight \n 4) Book a flight "
				+ "\n 5) List of airlines \n 6) List of airports \n 7) List of flights \n 8) Seat availability enquiry \n 9) List of available seats for specific flight \n 0) EXIT");
	}

	public static void main(String[] args) throws Exception {

		FlightManagementSystem flightms = new FlightManagementSystem();
		AirlineManagementSystem airlinems = new AirlineManagementSystem();
		AirportManagementSystem airportms = new AirportManagementSystem();
		BookingFlightTicket bft = new BookingFlightTicket();
		Scanner scan = new Scanner(System.in);
		String keyboard = "-144";
		
	try {
	
		while (1 > 0) {
			
			
			while (keyboard != "0") {
				
				displayMenu();
				keyboard = scan.next();
				
				
				switch (keyboard) {

				case "1":		
					System.out.println("Create airline");
				//	airlinems.createAirline();
					break;
					
				case "2":
					System.out.println("Create airport");
				//	airportms.createAirport();
					break;
					
				case "3":
					System.out.println("Create flight");
					flightms.createFlight();
					break;
					
				case "4":
					System.out.println("Book a flight");
				//	System.out.println("List of flight IDs" + flightms.getListOfFlightIds());
					System.out.println("Type FlightID!");
					int flightID = scan.nextInt();
				//	System.out.println("List of flight classes" + flightms.getListOfFlightClasses());         
					System.out.println("Type flight class!");
					String flightClass = scan.next();
					flightms.displayAvailableSeatsInSpecificFlight(flightID);
					System.out.println("Type  seat row!");
					char seatRow = scan.next().charAt(0);
					System.out.println("Type seat number");
					int seatNumber = scan.nextInt();
					bft.bookAFlight(flightID, flightClass, seatRow, seatNumber);
					break;
					
				case "5":
					System.out.println("List of airlines");
					System.out.println(airlinems.fetchDatabaseContentToList());
					break;
					
					
				case "6":
					System.out.println("List of airports");
					System.out.println(airportms.fetchDatabaseContentToList());
					break;
					
				case "7" :
					System.out.println("List of flights");
					System.out.println(flightms.fetchFlightDatabaseContentToList());
					break;
					
				case "8" :
					System.out.println("Seat availability enquiry");
					System.out.println("Type flight ID");
					int flight_ID = scan.nextInt();
					System.out.println("Type seatrow");
					char seat_Row = scan.next().charAt(0);
					System.out.println("Type seat number");
					int seat_Number = scan.nextInt();
					if (bft.isSeatAvailable(flight_ID, seat_Row, seat_Number)) {
						System.out.println("Seat is available");
					}
					else {
						System.out.println("Seat is not available");
					}
					break;
					
				case "9" :
					System.out.println("List of available seats for specific flight ");
					System.out.println("Please enter flight ID");
					int id_of_flight = scan.nextInt();
					flightms.displayAvailableSeatsInSpecificFlight(id_of_flight);
					break;
					
				case "10":
					System.out.println("Remove flight");
					System.out.println("Enter flightID!");
					int flight_iD = scan.nextInt();
					flightms.removeFlightFromDatabase(flight_iD);
				}
				
			}
		}
		}
	catch (Exception e) {
		System.out.println("Something went wrong");
		e.printStackTrace();	
	}
	
	finally {
		scan.close();
	}
	}
}

