package database;

import java.sql.*;
import java.util.ArrayList;
import models.Seat;

public class SeatDatabase {

	private static String statementToStoreDataIntoSeats = "INSERT INTO seats"
			+ "(flight_ID, seatRow, seatNumber, isSeatAvailable) values " + " (?,?,?,?);";
	private static String statementToDisplayDataOfSeats = "SELECT * FROM seats";
	private static String statementToUpdateSeatsData = "UPDATE seats set isSeatAvailable= ? where flight_ID=? AND seatRow= ? AND seatNumber= ?";
	private static String statementToDeleteDataFromSeats = "DELETE from seats where flight_ID=?";

	public void storeToDatabase(Seat seat) {

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoSeats);

			preparedStmt.setInt(1, seat.getFlightId()); // FlightID Column
			preparedStmt.setString(2, String.valueOf(seat.getSeatRow())); // Seatrow Column
			preparedStmt.setInt(3, seat.getSeatNumber()); // Seatnumber Column
			preparedStmt.setBoolean(4, seat.isSeatAvailable()); // IsSeatAvailable Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Seat> fetchDatabaseContent() { // mechanism for fetching content from database and returning as
													// ArrayList

		ArrayList<Seat> seats = new ArrayList<>();
		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfSeats);

			while (rset.next()) {

				Seat seat = new Seat(rset.getInt("flight_ID"), rset.getString("seatRow").charAt(0),
						rset.getInt("seatNumber"), rset.getBoolean("isSeatAvailable"));

				seats.add(seat);
			}
			conn.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}
		return seats;

	}

	public void updateDatabaseContent(int FlightID, char seatRow, int seatNumber, boolean isSeatAvailable) { // mechanism
																												// for
																												// updating
																												// database
																												// content

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateSeatsData);

			preparedStmt.setBoolean(1, isSeatAvailable); // update isSeatAvailable column
			preparedStmt.setInt(2, FlightID); // where Flight_ID
			preparedStmt.setString(3, String.valueOf(seatRow)); // where seatRow
			preparedStmt.setInt(4, seatNumber); // where seatNumber

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteContentFromDatabase(int flight_ID) { // deleting content from database

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromSeats);

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
