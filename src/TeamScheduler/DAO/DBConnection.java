/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author james.clair
 */
public class DBConnection {
	private static final String DATABASENAME = "U05BNo";
	private static final String DB_URL = "jdbc:mysql://52.206.157.109/" + DATABASENAME;
	private static final String USERNAME = "U05BNo";
	private static final String PASSWORD = "53688453415";
        private static final String DRIVER = "com.mysql.jdbc.Driver";
	public static Connection conn;

	public static void makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		System.out.println("Connection Successful!");
	}
	
	public static void closeConnection() throws SQLException {
		conn.close();
		System.out.println("Connection Closed!");
		
	}
}
