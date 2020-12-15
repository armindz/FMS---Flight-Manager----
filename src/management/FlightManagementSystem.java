package management;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import database.AirlineDatabase;
import database.AirportDatabase;
import database.FlightDatabase;
import database.SeatDatabase;
import models.Airline;
import models.Airport;
import models.Flight;
import models.Seat;

public class FlightManagementSystem {

	AirlineManagementSystem airlinems = new AirlineManagementSystem();
	AirportManagementSystem airportms = new AirportManagementSystem();

	AirlineDatabase airlinedb = new AirlineDatabase();
	AirportDatabase airportdb = new AirportDatabase();
	SeatDatabase seatdb = new SeatDatabase();
	FlightDatabase flightdb = new FlightDatabase();

	
	private Flight flightCreatorQuery() {
	
		Scanner scan = new Scanner(System.in);
		try {

			System.out.println("Please type airline codename!");
			String airlineCodename = scan.next();
			System.out.println("Please type airport codename!");
			String airportCodename = scan.next();
			System.out.println("Please type destination airport codename!");
			String destinationAirportCodename = scan.next();
			System.out.println("Please type flight class!");
			String flightClass = scan.next();
			System.out.println("Please type seat row!");
			char flightSeatRows = scan.next().charAt(0);
			System.out.println("Please type seat number!");
			int flightNumberOfSeatsPerRow = scan.nextInt();
			System.out.println("Please type flight price!");
			double flightPrice = scan.nextDouble();
			System.out.println("Please type date of flight!");
			System.out.println("YEAR");
			int year = scan.nextInt();
			System.out.println("MONTH");
			int month = scan.nextInt();
			System.out.println("DAY");
			int day = scan.nextInt();
			System.out.println("HOURS");
			int hourOfDay = scan.nextInt();
			System.out.println("MIN");
			int minute = scan.nextInt();
		
			Calendar dateOfFlight = Calendar.getInstance();
			dateOfFlight.set(year, month - 1, day, hourOfDay, minute); // set date based on user input data
			
			Flight flight = new Flight(flightdb.generateFlightId(), airlinems.getAirlineFromCodename(airlineCodename),
					airportms.getAirportFromCodename(airportCodename),
					airportms.getAirportFromCodename(destinationAirportCodename), flightClass, dateOfFlight,
					flightSeatRows, flightNumberOfSeatsPerRow, flightPrice);
			
			return flight;
		
	} 
		catch (Exception e) {
		System.out.println("Something went wrong");
		e.printStackTrace();
	}
		return null;
		
		
	}
	
	private boolean isFlightDataValid(Flight flight, char flightSeatRows, String airlineCodename, String airportCodename, String destinationAirportCodename) {
		
		if (isFlightDataUnique(flight) && isSeatRowValid(flightSeatRows) && isAirlineCodenameValid(airlineCodename)
				&& isAirportCodenameValid(airportCodename) && isAirportCodenameValid(destinationAirportCodename)) {
		return true;
	}
		
		return false;
	}
	public void createFlight() {
	

			Flight flight = flightCreatorQuery();
			
			System.out.println(flight);
			if (isFlightDataValid(flight, flight.getSeatRow(), flight.getAirline().getAirlineCodename(), 
					flight.getAirport().getAirportCodename(), flight.getDestinationAirport().getAirportCodename())) {
				

         		createSeatsAndStoreToDatabase(flight, flight.getSeatRow(), flight.getSeatNumber());
				addFlightToDatabase(flight);

				System.out.println("Flight successfully created!");
			} else {
				System.out.println("Data is not unique or seat row not valid.");
			}

		
	}

	private void createSeatsAndStoreToDatabase(Flight flight, char seatRow, int numberOfSeatsPerRow) {

		for (int i = 'A'; i <= seatRow; i++) {
			for (int j = 1; j <= numberOfSeatsPerRow; j++) {

				char seatRows = (char) i;
				Seat seat = new Seat(flight.getFlight_id(), seatRows, j, true);

				flight.addToListOfSeats(seat);
				addSeatToDatabase(seat);
			}

		}
		System.out.println("List" + flight.getListOfSeats());
	}

	public void displayAvailableSeatsInSpecificFlight(int flight_id) {  
		
		ArrayList <Seat> listOfSeats = fetchSeatDatabaseContentToList(); // prepare listOfSeats ArrayList for use by fetching content from database

		for (int i = 0; i < listOfSeats.size(); i++) {

			if ((listOfSeats.get(i).getFlightId() == flight_id) && listOfSeats.get(i).isSeatAvailable()) {
				System.out.println(listOfSeats.get(i));
			}
		}
	}

	public void markSeatAsAvailable(int flightId, char seatRow, int seatNumber) {

		boolean isSeatAvailable = true;
		seatdb.updateDatabaseContent(flightId, seatRow, seatNumber, isSeatAvailable);

	}

	public void markSeatAsUnavailable(int flightId, char seatRow, int seatNumber) {

		boolean isSeatAvailable = false;
		seatdb.updateDatabaseContent(flightId, seatRow, seatNumber, isSeatAvailable);

	}

	private boolean isFlightDataUnique(Flight flight) {

		ArrayList <Flight> listOfFlights = flightdb.fetchDatabaseContent(); // return flight database content through given ArrayList
		if (!listOfFlights.isEmpty() && listOfFlights.contains(flight)) {
			return false;
		}

		return true;
	}

	private boolean isAirlineCodenameValid(String airlineCodename) {

		ArrayList <Airline> listOfAirlines = airlinedb.fetchDatabaseContent(); // // return airline database content through
																		// given ArrayList
		for (int i = 0; i < listOfAirlines.size(); i++) {
			String airlineCodenameFromList = listOfAirlines.get(i).getAirlineCodename();
			if (airlineCodenameFromList.equals(airlineCodename)) {
				return true;
			}
		}
		System.out.println("Airline codename is not in database.");
		return false;
	}

	private boolean isAirportCodenameValid(String airportCodename) {

		ArrayList <Airport> listOfAirports = airportdb.fetchDatabaseContent();  // return airport database content through given ArrayList

		for (int i = 0; i < listOfAirports.size(); i++) {
			String airportCodenameFromList = listOfAirports.get(i).getAirportCodename();

			if (airportCodenameFromList.equals(airportCodename)) {
				return true;
			}
		}
		System.out.println("Airport codename is not in database.");
		return false;
	}

	private boolean isSeatRowValid(char seatRow) {

		if (seatRow == 'A' || seatRow == 'B' || seatRow == 'C' || seatRow == 'D' || seatRow == 'E' || seatRow == 'F') {
			return true;
		}
		return false;
	}

	public Flight getFlightFromFlightID(int flightID) {

		ArrayList <Flight> listOfFlights = flightdb.fetchDatabaseContent(); // return flight database content through given ArrayList

		for (int i = 0; i < listOfFlights.size(); i++) {

			int flightIDFromList = listOfFlights.get(i).getFlight_id();

			if (flightID == flightIDFromList) {
				Flight flight = new Flight(listOfFlights.get(i).getFlight_id(), listOfFlights.get(i).getAirline(), listOfFlights.get(i).getAirport(),
						listOfFlights.get(i).getDestinationAirport(), listOfFlights.get(i).getFlightClass(),
						listOfFlights.get(i).getDateOfFlight(), listOfFlights.get(i).getSeatRow(),
						listOfFlights.get(i).getSeatNumber(), listOfFlights.get(i).getFlightPrice());
				return flight;

			}
		}

		return null;

	}

	public ArrayList<Flight> getListOfFlights() {
	
		return fetchFlightDatabaseContentToList(); 
	}

	public ArrayList<Seat> getListOfSeats() {
		
		return fetchSeatDatabaseContentToList();
	}

	/*public ArrayList <Integer> getListOfFlightIds() {            // TOO MANY CONNECTIONS 
		
		ArrayList <Integer> listOfFlightIds = new ArrayList<>();
		
		for (int i= 0; i < getListOfFlights().size(); i++) {
			
			listOfFlightIds.add(getListOfFlights().get(i).getFlight_id());
			
		
		}
		
	return listOfFlightIds;
	}


	public ArrayList <String> getListOfFlightClasses() {
		
		ArrayList <String> listOfFlightClass = new ArrayList<>();
		
			for (int i= 0; i < getListOfFlights().size(); i++) {
			
			listOfFlightClass.add(getListOfFlights().get(i).getFlightClass());
			
		
		}
			
			return listOfFlightClass;
	}
*/
	public ArrayList<Seat> fetchSeatDatabaseContentToList() { 

		ArrayList <Seat> listOfSeats = seatdb.fetchDatabaseContent(); 
		if (listOfSeats.isEmpty()) {
			System.out.println("There's no seats stored in database!");
			return null;
		}
		return listOfSeats;
	}

	public ArrayList<Flight> fetchFlightDatabaseContentToList() {

		ArrayList <Flight> listOfFlights = flightdb.fetchDatabaseContent();
		
		if (listOfFlights.isEmpty()) {
			System.out.println("There's no flights stored in database!");
			return null;
		}
		return listOfFlights;
	}
	
	public void addFlightToDatabase (Flight flight) {
		
		flightdb.storeToDatabase(flight);
	}

	public void removeFlightFromDatabase(int flight_ID) {

		flightdb.deleteContentFromDatabase(flight_ID);
	}

	private void addSeatToDatabase(Seat seat) {

		seatdb.storeToDatabase(seat);
	}
}
