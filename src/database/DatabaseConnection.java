package database;

import java.sql.*;

public class DatabaseConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/flightmanager";
	private static String root = "root";
	private static String password = "sifra";

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, root, password);
		}

		catch (Exception e) {
			System.out.println("Something went wrong : ");
			e.printStackTrace();
		}

		return null;

	}

}
