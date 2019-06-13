package module.unused;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQLServer {
	private String driver = null;
	private String connectionUrl = null;
	private String db = null;
	private String user = null;
	private String password = null;

	public ConnectSQLServer(String driver, String connectionUrl, String db, String user, String password) {
		this.driver = driver;
		this.connectionUrl = connectionUrl;
		this.db = db;
		this.user = user;
		this.password = password;
		this.checkDriver();
	}

	public void checkDriver() {

		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver Error");
			e.printStackTrace();
		}
	}

	public  Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(this.connectionUrl+this.db, this.user, this.password);
			if (!connection.isClosed()) {
				System.out.println("Ë≥áÊ?ôÂ∫´???é•??êÂ??");
			}
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
