package test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import module.filehandle.ExportJSON;
import module.unused.ConnectSQLServer;
import module.unused.DisplayResult;
import module.unused.DynamicCommand;
import module.unused.ImportData;

public class testcase {

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
		// ----------------------
		if (!id.sendSQLCommand())
			System.out.println("Update Scuccess");
		else
			System.out.println("Query Success");
		// -----------------------

		String output = null;
		DynamicCommand dc = new DynamicCommand(conn, "select * from parkinfo");
		
		
		
		try (ResultSet rs = dc.executeQuery()) {
			System.out.println(new DisplayResult(rs).display("parkId", "areaId", "areaName"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//-------
		try (ResultSet rs = dc.executeQuery()) {
			output = ExportJSON.ResultSetToJsonString(rs);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (BufferedWriter bos1 = new BufferedWriter(new FileWriter("F:\\test.json"));) {
			bos1.write(output);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		try {
			//dc.close();
			conn.close();
			System.out.println("鞈�澈��蝺葉�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
