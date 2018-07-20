package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	//private static Connection con;
	
	private DBConnection() { }
	
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/fxJdbcDemo?useSSL=false";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(url, "root", "MyNewPass");
		System.out.println("established connection");
		return con;
	}
	
}
