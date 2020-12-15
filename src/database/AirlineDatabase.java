
package database;

import java.sql.*;
import java.util.ArrayList;

import models.Airline;

public class AirlineDatabase {

	private static String statementToStoreDataIntoAirlines = "INSERT INTO airlines"
			+ "(Airline_Codename, Airline_Callsign, Airline_Country) values " + " (?,?,?);";
	private static String statementToDisplayDataOfAirlines = "SELECT * FROM airlines";
	private static String statementToUpdateAirlinesData = "UPDATE airlines set Airline_Callsign= ?, Airline_Country = ? where  Airline_Codename= ? ";
	private static String statementToDeleteDataFromAirlines = "DELETE from flights where Airline_Codename=?";

	public void storeToDatabase(Airline airline) {
		
		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoAirlines);

			preparedStmt.setString(1, airline.getAirlineCodename()); // Airline_Codename Column
			preparedStmt.setString(2, airline.getAirlineCallsign()); // Airline_Callsign Column
			preparedStmt.setString(3, airline.getAirlineCountry()); // Airline_Country Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Airline> fetchDatabaseContent() { // mechanism for fetching content from database and returning as ArrayList
	
		ArrayList<Airline> airlines = new ArrayList<>();
		try {
			
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfAirlines);
			airlines.clear();
			while (rset.next()) {
				Airline airline = new Airline(rset.getString("Airline_Codename"), rset.getString("Airline_Callsign"),
						rset.getString("Airline_Country"));
				
				airlines.add(airline);
			}
		}

		catch (Exception e) {

			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return airlines;
	}

	public void updateDatabaseContent(String Airline_Callsign, String Airline_Country) {

		try {
			
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateAirlinesData);
			
			preparedStmt.setString(2, Airline_Callsign); // update Airline_Callsign column
			preparedStmt.setString(3, Airline_Country); // update Airline_Country column

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteContentFromDatabase(String Airline_Codename) {

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromAirlines);
			preparedStmt.setString(1, Airline_Codename);
			preparedStmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
