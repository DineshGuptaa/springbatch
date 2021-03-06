package springbatch;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

	private final static String createTable = "CREATE TABLE `example` (id INT, data VARCHAR(100))";

	public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {

		Connection connection;

		Properties prop = new Properties();
		System.out.println("test");
		prop.load(this.getClass().getResourceAsStream("/batchdb.properties"));
		System.out.println("user.home: "+System.getProperty("user.home"));
		String host = prop.getProperty("host").toString();
		String username = prop.getProperty("username").toString();
		String password = prop.getProperty("password").toString();
		String driver = prop.getProperty("driver").toString();

		System.out.println("host: " + host + "\\username: " + username + "\\password: " + password + "\\ndriver: " + driver);

		Class.forName(driver);
		System.out.println("--------------------------");
		System.out.println("DRIVER: " + driver);
		connection = DriverManager.getConnection(host, username, password);
		System.out.println("CONNECTION: " + connection);

		return connection;
	}

	public void runSqlStatement() {
		try {
			Statement statement = createConnection().createStatement();
			boolean rs = statement.execute(createTable);

		} catch (IOException ex) {
			Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBManager dbManager = new DBManager();
		dbManager.runSqlStatement();
	}
}