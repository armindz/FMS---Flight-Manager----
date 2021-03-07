package database;

import java.sql.*;
import java.util.ArrayList;
import models.Seat;

public class SeatDatabase {

	private static String statementToStoreDataIntoSeats = "INSERT INTO seats"
			+ "(seat_id, flight_id, seat_row, seat_number, is_seat_available) values " + " (?,?,?,?,?);";
	private static String statementToDisplayDataOfSeats = "SELECT * FROM seats";
	private static String statementToUpdateSeatsData = "UPDATE seats set is_seat_available= ? where flight_id=? AND seat_row= ? AND seat_number= ?";
	private static String statementToDeleteDataFromSeats = "DELETE from seats where flight_id=?";

	public void storeToDatabase(Seat seat) {

		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoSeats);

			preparedStmt.setInt(1, generateSeatId());
			preparedStmt.setInt(2, seat.getFlightId()); // FlightID Column
			preparedStmt.setString(3, String.valueOf(seat.getSeatRow())); // Seatrow Column
			preparedStmt.setInt(4, seat.getSeatNumber()); // Seatnumber Column
			preparedStmt.setBoolean(5, seat.isSeatAvailable()); // IsSeatAvailable Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int generateSeatId() { // mechanism for generating seat ID based on last stored ID in database

		int seatID = 0;
		try {

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementToDisplayDataOfSeats);

			while (rs.next()) {

				if (rs.isLast()) {
					seatID = rs.getInt(1);
					seatID++;
				}
			}

			return seatID;
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return seatID;
	}

	public ArrayList<Seat> fetchDatabaseContent() { // mechanism for fetching content from database and returning as
													// ArrayList

		ArrayList<Seat> seats = new ArrayList<>();
		try {

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfSeats);

			while (rset.next()) {

				Seat seat = new Seat(rset.getInt("flight_id"), rset.getString("seat_row").charAt(0),
						rset.getInt("seat_number"), rset.getBoolean("is_seat_available"));

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

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
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

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
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
