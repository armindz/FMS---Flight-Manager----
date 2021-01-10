
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import management.AirlineManagementSystem;
import management.AirportManagementSystem;

import models.FlightTicket;

public class FlightTicketDatabase {

	private static String statementToStoreDataIntoFlightTickets = "INSERT INTO flight_tickets"
			+ "(flight_ID, AirlineCodename, Airport_Codename,destinationAirport, Flightclass, Date_of_flight, seatRow, seatNumber, flight_Price, buyers_Name) values "
			+ " (?,?,?,?,?,?,?,?,?,?);";
	private static String statementToDisplayDataOfFlightTickets = "SELECT * FROM flight_tickets";
	private static String statementToUpdateFlightTicketsData = "UPDATE flight_tickets set AirlineCodename= ?,Airport_Codename= ?, destinationAirport = ?, Flightclass = ?, "
			+ "Date_of_flight = ?, flight_Price= ?  where flight_ID= ?, seatRow = ?, seatNumber = ?,  buyers_Name = ?";
	private static String statementToDeleteDataFromFlightTickets = "DELETE from flight_tickets where flight_ID=? , seatRow = ? , seatNumber= ?";
	private static String statementToDeleteAllDataFromFlightTicketsRelatedToSpecificFlight = "DELETE from flight_tickets where flight_ID=? ";
	final String STATEMENT_IF_CODENAME_IS_NULL = "NOT AVAILABLE";
	AirlineManagementSystem airlinems = new AirlineManagementSystem();
	AirportManagementSystem airportms = new AirportManagementSystem();

	public void storeToDatabase(FlightTicket flightTicket) {

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoFlightTickets);

			preparedStmt.setInt(1, flightTicket.getFlightId()); // Flight_ID Column
			preparedStmt.setString(2, flightTicket.getAirline().getAirlineCodename()); // AirlineCodename Column
			preparedStmt.setString(3, flightTicket.getAirport().getAirportCodename()); // AirportCodename Column
			preparedStmt.setString(4, flightTicket.getDestinationAirport().getAirportCodename()); // Destination Airport
			// Column
			preparedStmt.setString(5, flightTicket.getFlightClass()); // Flight_Class Column
			preparedStmt.setTimestamp(6, flightTicket.getDateOfFlightTicketInDateTime(flightTicket.getDateOfFlight())); // Date_OF_Flight
			// Column
			preparedStmt.setString(7, String.valueOf(flightTicket.getSeatRow())); // SeatRow Column
			preparedStmt.setInt(8, flightTicket.getSeatNumber()); // Seat_Number Column
			preparedStmt.setDouble(9, flightTicket.getFlightPrice()); // Flight_Price Column
			preparedStmt.setString(10, flightTicket.getBuyersName());

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<FlightTicket> fetchDatabaseContent() { // mechanism for fetching content from database and
															// returning as ArrayList

		ArrayList<FlightTicket> flightTickets = new ArrayList<>();

		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfFlightTickets);

			flightTickets.clear();
			while (rset.next()) {

				Calendar cal = Calendar.getInstance();
				Timestamp timestamp = rset.getTimestamp("Date_of_flight");
				cal.setTime(timestamp);

				// check if airline is not null (may be deleted)
				if (airlinems.getAirlineFromCodename(rset.getString("AirlineCodename")) != null
						&& airportms.getAirportFromCodename(rset.getString("Airport_Codename")) != null
						&& airportms.getAirportFromCodename(rset.getString("destinationAirport")) != null) {

					FlightTicket flightTicket = new FlightTicket(rset.getInt("flight_ID"),
							airlinems.getAirlineFromCodename(rset.getString("AirlineCodename")),
							airportms.getAirportFromCodename(rset.getString("Airport_Codename")),
							airportms.getAirportFromCodename(rset.getString("destinationAirport")),
							rset.getString("flightclass"), cal, rset.getString("seatRow").charAt(0),
							rset.getInt("seatNumber"), rset.getDouble("flight_Price"), rset.getString("buyers_Name"));

					flightTickets.add(flightTicket);
					System.out.println(flightTickets);
				}
			}
			conn.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return flightTickets;
	}

	public void updateDatabaseContent(int FlightID, String AirlineCodename, String Airport_Codename,
			String destinationAirport, String Flightclass, Calendar Date_of_flight, char seatRow, int seatNumber,
			double flight_Price, String buyers_Name) {

		Timestamp timestamp = new Timestamp(Date_of_flight.getTimeInMillis());

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateFlightTicketsData);

			preparedStmt.setString(1, AirlineCodename); // update Airline_Codename column
			preparedStmt.setString(2, Airport_Codename); // update Airport_Codename column
			preparedStmt.setString(3, destinationAirport); // update Destination_Airport column
			preparedStmt.setString(4, Flightclass); // update Flight_class column
			preparedStmt.setTimestamp(5, timestamp); //
			preparedStmt.setDouble(6, flight_Price); // update flight_price
			preparedStmt.setInt(7, FlightID); // where FlightID
			preparedStmt.setString(8, String.valueOf(seatRow)); // update seatRow column
			preparedStmt.setInt(9, seatNumber); // update seatNumber column
			preparedStmt.setString(10, buyers_Name);

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteContentFromDatabase(int flight_ID, char seatRow, int seatNumber) { // deleting from database
																							// content found using
																							// flight_ID as it
		// is unique

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromFlightTickets);

			preparedStmt.setInt(1, flight_ID);
			preparedStmt.setString(2, String.valueOf(seatRow));
			preparedStmt.setInt(3, seatNumber);
			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteAllContentFromDatabaseRelatedToSpecificFlight(int flight_ID) { // deleting from database content
																						// found using flight_ID as it
		// is unique

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn
					.prepareStatement(statementToDeleteAllDataFromFlightTicketsRelatedToSpecificFlight);

			preparedStmt.setInt(1, flight_ID);
			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
