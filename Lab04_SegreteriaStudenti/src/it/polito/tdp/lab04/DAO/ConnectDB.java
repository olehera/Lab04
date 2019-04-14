package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?serverTimezone=UTC&user=root&password=sascia";

	public static Connection getConnection() {
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL); 
			
			return conn;
			
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		
		return null;
	}

}

