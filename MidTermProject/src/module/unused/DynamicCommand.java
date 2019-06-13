package module.unused;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicCommand {
	private PreparedStatement pstmt;

	public DynamicCommand(Connection conn, String sqlcmd) {
		this.conn = conn;
		this.sqlcmd = sqlcmd;
		try {
			this.pstmt = this.conn.prepareStatement(this.sqlcmd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String sqlcmd = "";
	private Connection conn = null;

	public ResultSet executeQuery() throws SQLException {
		//TODO: close problem with preparedStatement

		try {
			ResultSet rs = this.pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean executeUpdate() throws SQLException {
		try {
			return pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean close() {
		try {
			this.pstmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
