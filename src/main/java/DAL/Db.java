package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

	private static String username = "plf_training_admin";
	private static String password = "pff123";
	private static String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";

	public Db() {
		// TODO Auto-generated constructor stub
		username = "plf_training_admin";
		password = "pff123";
		url = "jdbc:postgresql://192.168.110.48:5432/postgres";
	}

	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url, username, password);
	}

}
