package database;

import java.sql.*;

// Implementiraj Singleton
public class DatabaseConnection {

	private static DatabaseConnection dbConnection;
	private Connection connection;
	private static String url = "jdbc:mysql://localhost:3306/flightmanager?useSSL=false&serverTimezone=Europe/Rome";
	private static String root = "root";
	private static String password = "sifra";

	private DatabaseConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, root, password);
		}

		catch (Exception e) {
			System.out.println("Something went wrong with database connection: ");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance() {

		try {
			
			if (dbConnection == null) {
				dbConnection = new DatabaseConnection();
			}
			if (dbConnection.getConnection().isClosed()) {
				dbConnection = new DatabaseConnection();
			}

			return dbConnection;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
