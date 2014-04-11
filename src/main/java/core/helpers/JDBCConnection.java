package core.helpers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
	private static String db_ip;
	private static String db_port;
	private static String db_user;
	private static String db_password;
	private static Properties connection_details;
	static Connection connection = null;

	public static void setupConnection() {
		connection_details = new Properties();
		InputStream is = Properties.class.getResourceAsStream("/jdbcconnection.properties");
		try {
			connection_details.load(is);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		get_ip();
		get_port();
		get_user();
		get_password();
	}

	private static void get_ip() {
		db_ip = connection_details.getProperty("db_ip");
	}

	private static void get_port() {
		db_port = connection_details.getProperty("db_port");
	}

	private static void get_user( ){
		db_user = connection_details.getProperty("db_user");
	}

	private static void get_password() {
		db_user = connection_details.getProperty("db_password");
	}

	public static void connect() throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://" + db_ip + ":" + db_port + "/sw_olap",db_user, db_user);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void executeQuery(String query) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		statement.execute();
	}

	public static void closeConnection() throws SQLException {
		connection.close();
	}
}
