package database;

import java.sql.*;
import java.util.ArrayList;
import models.Airport;

public class AirportDatabase {

	private static String statementToStoreDataIntoAirports = "INSERT INTO airports"
			+ "(Airport_Codename, Airport_Fullname, Airport_Type, Airport_City, Airport_Country) values "
			+ " (?,?,?, ?, ?);";
	private static String statementToDisplayDataOfAirports = "SELECT * FROM airports";
	private static String statementToUpdateAirportsData = "UPDATE airports set Airport_Fullname = ?, Airport_Type = ?, Airport_City =?, Airport_Country where  Airport_Codename= ?";
	private static String statementToDeleteDataFromAirports = "DELETE from airports where Airport_Codename= ?";

	public void storeToDatabase(Airport airport) {

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoAirports);

			preparedStmt.setString(1, airport.getAirportCodename()); // Airport_Codename Column
			preparedStmt.setString(2, airport.getAirportFullname()); // Airport_Fullname Column
			preparedStmt.setString(3, airport.getAirportType()); // Airport_Type Column
			preparedStmt.setString(4, airport.getAirportCity()); // Airport_City Column
			preparedStmt.setString(5, airport.getAirportCountry()); // Airport_Country Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Airport> fetchDatabaseContent() { // mechanism for fetching content from database and returning as
														// ArrayList

		ArrayList<Airport> airports = new ArrayList<>();
		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfAirports);
			airports.clear();
			while (rset.next()) {

				Airport airport = new Airport(rset.getString("Airport_Codename"), rset.getString("Airport_Fullname"),
						rset.getString("Airport_Type"), rset.getString("Airport_City"),
						rset.getString("Airport_Country"));

				airports.add(airport);
			}
			conn.close();
		}

		catch (Exception e) {

			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return airports;

	}

	public void updateDatabaseContent(String Airport_Codename, String Airport_Fullname, String Airport_Type,
			String Airport_City, String Airport_Country) {

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateAirportsData);

			preparedStmt.setString(2, Airport_Codename); // update Airport_Codename column
			preparedStmt.setString(3, Airport_Fullname); // update Airport_Fullname column
			preparedStmt.setString(4, Airport_Type); // update Airport_Type column
			preparedStmt.setString(5, Airport_City); // update Airport_City column
			preparedStmt.setString(6, Airport_Country); // update Airport_Country

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteContentFromDatabase(String Airport_Codename) { // deleting from database content found using
																		// Airport_Codename as it is unique

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromAirports);

			preparedStmt.setString(1, Airport_Codename);
			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
