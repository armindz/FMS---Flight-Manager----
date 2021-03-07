package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import models.User;

public class UserDatabase {

	private static String statementToStoreDataIntoUsers = "INSERT INTO users" + "(user_ID, username, password) values "
			+ " (?,?,?);";
	private static String statementToDisplayDataOfUsers = "SELECT * FROM users";
	private static String statementToUpdateUsersData = "UPDATE users set password=? where  username=? ";
	private static String statementToDeleteDataFromUsers = "DELETE from users where user_ID";

	public void storeToDatabase(User user) {

		try {

			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreDataIntoUsers);

			preparedStmt.setInt(1, generateUserID()); // UserID Column
			preparedStmt.setString(2, user.getUsername()); // Username Column
			preparedStmt.setString(3, user.getPassword()); // Password Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<User> fetchDatabaseContent() { // mechanism for fetching content from database
													// and returning as
		// ArrayList

		ArrayList<User> users = new ArrayList<>();

		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayDataOfUsers);
			while (rset.next()) {
				User user = new User(rset.getInt("user_ID"), rset.getString("username"), rset.getString("password"));

				users.add(user);
			}

			conn.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}
		

		return users;
	}

	public void updateDatabaseContent(String username, String password) {

		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToUpdateUsersData);

			preparedStmt.setString(1, username); //
			preparedStmt.setString(2, password); // update password column

			preparedStmt.executeUpdate();

			conn.close();
			preparedStmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteContentFromDatabase(int userID) {

		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToDeleteDataFromUsers);
			preparedStmt.setInt(1, userID);
			preparedStmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int generateUserID() { // mechanism for generating userID based on last stored ID in
									// database
		int userID = 0;
		try {
			DatabaseConnection dbConnection = DatabaseConnection.getInstance();
			Connection conn = dbConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementToDisplayDataOfUsers);
			

			while (rs.next()) {

				if (rs.isLast()) {
					userID = rs.getInt(1);
					userID++;
				}
			}
			return userID;
		}

		catch (Exception e) {
			System.out.println("Something went wrong with generating userID");
			e.printStackTrace();
		}

		return 0;
	}

}
