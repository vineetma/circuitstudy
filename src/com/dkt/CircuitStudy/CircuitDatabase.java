package com.dkt.CircuitStudy;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CircuitDatabase {
	public final String USER = "root";
	public final String PASSWORD = "";
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/circuitdb";
	java.sql.Connection conn = null;

	public CircuitDatabase() {
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public boolean writeObject(DBInterface obj) {
		obj.writeToDb(conn);
		return false;
	}

	public boolean readObject(DBInterface obj) {
		obj.readFromDb(conn);
		return false;
	}
}
