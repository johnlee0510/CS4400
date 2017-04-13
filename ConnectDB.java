import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private Connection conn;
	ConnectDB() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		try {
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		String jdbcUrl = "jdbc:mysql://academic-mysql.cc.gatech.edu:3306/cs4400_Group_52";
		String userid = "cs4400_Group_52";
		String userPass = "ZyCuVkkv";
		conn = DriverManager.getConnection(jdbcUrl, userid, userPass);
		return conn;
	}
}
