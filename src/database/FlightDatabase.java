
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import management.AirlineManagementSystem;
import management.AirportManagementSystem;

import models.Flight;

public class FlightDatabase {

	private static String statementToStoreDataIntoFlights = "INSERT INTO flights"
			+ "(flight_ID, AirlineCodename, Airport_Codename,destinationAirport, Flightclass, Date_of_flight, seatRow, seatNumber, flight_Price) values "
			+ " (?,?,?,?,?,?,?,?,?);";
	private static String statementToDisplayDataOfFlights = "SELECT * FROM flights";
	private static String statementToUpdateFlightsData = "UPDATE flights set AirlineCodename= ?,Airport_Codename= ?, destinationAirport = ?, Flightclass = ?, "
			+ "Date_of_flight = ?, seatRow = ?, seatNumber = ?, flight_Price= ? where flight_ID= ?";
	private static String statementToDeleteDataFromFlights = "DELETE from flights where flight_ID=?";
	final String STATEMENT_IF_CODENAME_IS_NULL = "NOT AVAILABLE";
	AirlineManagementSystem airlinems = new AirlineManagementSystem();
	AirportManagementSystem airportms = new AirportManagementSystem();

	public void storeToDatabase(Flight flight) {

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoFlights);

			preparedStmt.setInt(1, flight.getFlight_id()); // Flight_ID Column
			preparedStmt.setString(2, flight.getAirline().getAirlineCodename()); // AirlineCodename Column
			preparedStmt.setString(3, flight.getAirport().getAirportCodename()); // AirportCodename Column
			preparedStmt.setString(4, flight.getDestinationAirport().getAirportCodename()); // Destination Airport
																							// Column
			preparedStmt.setString(5, flight.getFlightClass()); // Flight_Class Column
			preparedStmt.setTimestamp(6, flight.getDateOfFlightInDateTime(flight.getDateOfFlight())); // Date_OF_Flight
																										// Column
			preparedStmt.setString(7, String.valueOf(flight.getSeatRow())); // SeatRow Column
			preparedStmt.setInt(8, flight.getSeatNumber()); // Seat_Number Column
			preparedStmt.setDouble(9, flight.getFlightPrice()); // Flight_Price Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int generateFlightId() { // mechanism for generating flight ID based on last stored ID in database

		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementToDisplayDataOfFlights);
			int flightID = 0;

			while (rs.next()) {

				if (rs.isLast()) {
					flightID = rs.getInt(1);
					flightID++;
				}
			}
			conn.close();
			return flightID;
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public ArrayList<Flight> fetchDatabaseContent() { // mechanism for fetching content from database and returning as
														// ArrayList

		ArrayList<Flight> flights = new ArrayList<>();

		try {
			Connection conn = DatabaseConnection.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfFlights);

			flights.clear();
			while (rset.next()) {

				Calendar cal = Calendar.getInstance();
				Timestamp timestamp = rset.getTimestamp("Date_of_flight");
				cal.setTime(timestamp);

				// check if airline is not null (may be deleted)
				if (airlinems.getAirlineFromCodename(rset.getString("AirlineCodename")) != null
						&& airportms.getAirportFromCodename(rset.getString("Airport_Codename")) != null
						&& airportms.getAirportFromCodename(rset.getString("destinationAirport")) != null) {

					Flight flight = new Flight(rset.getInt("flight_ID"),
							airlinems.getAirlineFromCodename(rset.getString("AirlineCodename")),
							airportms.getAirportFromCodename(rset.getString("Airport_Codename")),
							airportms.getAirportFromCodename(rset.getString("destinationAirport")),
							rset.getString("flightclass"), cal, rset.getString("seatRow").charAt(0),
							rset.getInt("seatNumber"), rset.getDouble("flight_Price"));

					flights.add(flight);
					System.out.println(flight);
				}
			}
			conn.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return flights;
	}

	public void updateDatabaseContent(int FlightID, String AirlineCodename, String Airport_Codename,
			String destinationAirport, String Flightclass, Calendar Date_of_flight, char seatRow, int seatNumber,
			double flight_Price) {

		Timestamp timestamp = new Timestamp(Date_of_flight.getTimeInMillis());

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateFlightsData);

			preparedStmt.setString(1, AirlineCodename); // update Airline_Codename column
			preparedStmt.setString(2, Airport_Codename); // update Airport_Codename column
			preparedStmt.setString(3, destinationAirport); // update Destination_Airport column
			preparedStmt.setString(4, Flightclass); // update Flight_class column
			preparedStmt.setTimestamp(5, timestamp); //
			preparedStmt.setString(6, String.valueOf(seatRow)); // update seatRow column
			preparedStmt.setInt(7, seatNumber); // update seatNumber column
			preparedStmt.setDouble(8, flight_Price); // update flight_price
			preparedStmt.setInt(9, FlightID); // where FlightID

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteContentFromDatabase(int flight_ID) { // deleting from database content found using flight_ID as it
															// is unique

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromFlights);

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
