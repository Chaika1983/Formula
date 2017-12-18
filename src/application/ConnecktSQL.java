package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnecktSQL {
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement stat = null;

	public ConnecktSQL() {
		getConnection();
	}

	private void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/login_factory", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyData(String userName, String password) {
		boolean access = false;
		try {
			stat = conn.prepareStatement("SELECT name FROM user WHERE name = ? AND password = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stat.setString(1, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stat.setString(2, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs = stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				access = true;
			} else {
				access = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return access;
	}

	public boolean setRegistraion(String login, String password) {
		boolean acces = false;
		String query = "INSERT INTO user VALUES('" + login + "','" + password + "')";
		try {
			st.execute(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			rs = stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acces;
	}

	public void closeConnection() {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
