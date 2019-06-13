package test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import module.filehandle.ExportJSON;
import module.filehandle.ReadJson;
import module.unused.ConnectSQLServer;
import module.unused.DisplayResult;
import module.unused.DynamicCommand;
import module.unused.ImportData;

public class testcase2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectionUrl = "jdbc:sqlserver://localhost:1433;";
		String db = "databaseName=MidTermProject;";
		String user = "sa";
		String password = "passw0rd";
		String filepath = "F:\\ParkInfo.json";

		Connection conn = new ConnectSQLServer(driver, connectionUrl, db, user, password).getConnection();

		ImportData id = new ImportData(conn, filepath);
		id.sendSQLCommand();
		//ImportData json = new ImportData(new ReadJson(filepath).readFile(),conn);
//		try {
//			//json.sendJSONCommand("ParkInfo");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			conn.close();
			System.out.println("è³‡æ?™åº«??ç·šä¸­?–·");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
