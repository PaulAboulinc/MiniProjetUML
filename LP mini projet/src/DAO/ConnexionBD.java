package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {
	private Connection cn = null;
	private static ConnexionBD instance;
	
	private ConnexionBD () {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "aboulincp", "1107003954T");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static ConnexionBD getInstance(){
		if(instance == null){
			return new ConnexionBD();
		}
		return instance;
	}
	
	public Connection getConnection () {
		return cn;
	}
	
	
}
