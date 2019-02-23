package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	public static Connection getPostgresDataSource() {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(DBUtil.class.getResourceAsStream("/db-conn.xml"));
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

	public static Properties getQueryProperties() {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(DBUtil.class.getResourceAsStream("/queries.xml"));
			return prop;
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return null;
	}

}
