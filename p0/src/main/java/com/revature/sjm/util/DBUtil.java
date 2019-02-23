package com.revature.sjm.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.sjm.BankApp;

public class DBUtil {

	public static Connection getConnection() {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(BankApp.class.getResourceAsStream("/db-conn.xml"));
			String driver = prop.getProperty("jdbc.postgresql.driver");
			String url = prop.getProperty("jdbc.postgresql.url");
			String username = prop.getProperty("jdbc.postgresql.username");
			String password = prop.getProperty("jdbc.postgresql.password");
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (IOException | ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}
		return null;
	}

	public static Properties getProperties() {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(DBUtil.class.getResourceAsStream("/p0-sql.xml"));
			return prop;
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return null;
	}
}
