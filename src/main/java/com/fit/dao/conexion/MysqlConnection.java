package com.fit.dao.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/FitStats?", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
