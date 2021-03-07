
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import management.AirlineManagementSystem;
import management.AirportManagementSystem;

import models.FlightTicket;

public class FlightTicketDatabase {

	private static String statementToStoreDataIntoFlightTickets = "INSERT INTO flight_tickets"
			+ "(ticket_ID, flight_ID, airline_codename, airport_codename, destination_airport, flight_class, date_of_flight, seat_row, seat_number, flight_price, buyers_name) values "
			+ " (?,?,?,?,?,?,?,?,?,?,?);";
	private static String statementToDisplayDataOfFlightTickets = "SELECT * FROM flight_tickets";
	private static String statementToUpdateFlightTicketsData = "UPDATE flight_tickets set airline_codename=?,airport_codename=?, destination_airport=?, flight_class =?, "
			+ "date_of_flight =?, flight_price=?  where flight_ID=?, seat_row =?, seat_number =?,  buyers_name =?";
	private static String statementToDeleteDataFromFlightTickets = "DELETE from flight_tickets where flight_ID=? AND seat_row=? AND seat_number=?";
	private static String statementToDeleteAllDataFromFlightTicketsRelatedToSpecificFlight = "DELETE from flight_tickets where flight_ID=? ";
	final String STATEMENT_IF_CODENAME_IS_NULL = "NOT AVAILABLE";
	AirlineManagementSystem airlinems = new AirlineManagementSystem();
	AirportManagementSystem airportms = new AirportManagementSystem();

	public void storeToDatabase(FlightTicket flightTicket) {

		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoFlightTickets);

			preparedStmt.setInt(1, generateTicketId());
			preparedStmt.setInt(2, flightTicket.getFlightId()); // Flight_ID Column
			preparedStmt.setString(3, flightTicket.getAirline().getAirlineCodename()); // AirlineCodename Column
			preparedStmt.setString(4, flightTicket.getAirport().getAirportCodename()); // AirportCodename Column
			preparedStmt.setString(5, flightTicket.getDestinationAirport().getAirportCodename()); // Destination Airport
			preparedStmt.setString(6, flightTicket.getFlightClass()); // Flight_Class Column
			preparedStmt.setTimestamp(7, flightTicket.getDateOfFlightTicketInDateTime(flightTicket.getDateOfFlight())); // Date_OF_Flight
			preparedStmt.setString(8, String.valueOf(flightTicket.getSeatRow())); // SeatRow Column
			preparedStmt.setInt(9, flightTicket.getSeatNumber()); // Seat_Number Column
			preparedStmt.setDouble(10, flightTicket.getFlightPrice()); // Flight_Price Column
			preparedStmt.setString(11, flightTicket.getBuyersName());

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int generateTicketId() { // mechanism for generating seat ID based on last stored ID in database

		int ticketID = 0;
		try {

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementToDisplayDataOfFlightTickets);

			while (rs.next()) {

				if (rs.isLast()) {
					ticketID = rs.getInt(1);
					ticketID++;
				}
			}

			return ticketID;
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return ticketID;
	}

	public ArrayList<FlightTicket> fetchDatabaseContent() { // mechanism for fetching content from database and
															// returning as ArrayList

		ArrayList<FlightTicket> flightTickets = new ArrayList<>();

		try {

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfFlightTickets);

			flightTickets.clear();
			while (rset.next()) {

				Calendar cal = Calendar.getInstance();
				Timestamp timestamp = rset.getTimestamp("date_of_flight");
				cal.setTime(timestamp);

				// check if airline is not null (may be deleted)
				if (airlinems.getAirlineFromCodename(rset.getString("airline_codename")) != null
						&& airportms.getAirportFromCodename(rset.getString("airport_codename")) != null
						&& airportms.getAirportFromCodename(rset.getString("destination_airport")) != null) {

					FlightTicket flightTicket = new FlightTicket(rset.getInt("flight_ID"),
							airlinems.getAirlineFromCodename(rset.getString("airline_codename")),
							airportms.getAirportFromCodename(rset.getString("airport_codename")),
							airportms.getAirportFromCodename(rset.getString("destination_airport")),
							rset.getString("flight_class"), cal, rset.getString("seat_row").charAt(0),
							rset.getInt("seat_number"), rset.getDouble("flight_price"), rset.getString("buyers_name"));

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

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
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

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
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

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
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
