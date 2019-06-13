package module.unused;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class ImportData {

	private Connection conn = null;
	private String filepath = null;
	private String sql = null;
	private String c1 = "Declare @JSON varchar(max)\r\n" + "SELECT @JSON = BulkColumn\r\n" + "FROM OPENROWSET (BULK '";
	private String c2 = "', SINGLE_CLOB) as j\r\n" + "insert into ParkInfo\r\n"
			+ "select * from OPENJSON(@JSON,'$.parkingLots')\r\n" + "with\r\n" + "(parkId varchar(20) '$.parkId',\r\n"
			+ "areaId int '$.areaId',\r\n" + "areaName varchar(20) '$.areaName',\r\n"
			+ "parkName varchar(20) '$.parkName',\r\n" + "totalSpace int '$.totalSpace',\r\n"
			+ "surplusSpace int '$.surplusSpace',\r\n" + "payGuide varchar(50) '$.payGuide',\r\n"
			+ "introduction varchar(20) '$.introduction',\r\n" + "address varchar(20) '$.address',\r\n"
			+ "wgsX decimal(7,4) '$.wgsX',\r\n" + "wgsY decimal(7,4) '$.wgsY')";
	private String json = null;
	private JsonElement je = null;

	public ImportData(Connection conn, String filepath) {
		this.conn = conn;
		this.filepath = filepath;
		this.sql = c1 + this.filepath + c2;
	}

	public ImportData(String json, Connection conn) {
		this.conn = conn;
		this.json = json;
		this.je = JsonParser.parseString(json);
	}

	public void sendJSONCommand(String tableName) throws IOException, SQLException {
		// TODO find how to deal with jsonelement data
		String sqlQuery = "select * from ";
		String sqlUpdate = "Insert into " + tableName + " values";
		String valuesArg = "";
		
		ArrayList<String> columnName = new ArrayList<String>();
		try (Statement stmt = this.conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlQuery + tableName);) {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnName.add(rsmd.getColumnLabel(i));
				if (i == rsmd.getColumnCount()) {
					valuesArg += "?)";
				} else if (i == 1) {
					valuesArg += "(?,";
				} else {
					valuesArg += "?,";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonObject jobject = this.je.getAsJsonObject();
		JsonArray jarray = jobject.getAsJsonArray("parkingLots");
		try (PreparedStatement pstmt = this.conn.prepareStatement(sqlUpdate + valuesArg);) {
			for (int i = 0; i < jarray.size(); i++) {
				jobject = jarray.get(i).getAsJsonObject();
				ListIterator<String> ite = columnName.listIterator();
				while (ite.hasNext()) {
					pstmt.setString(ite.nextIndex()+1, jobject.get(ite.next()).getAsString());
					//System.out.println(ite.nextIndex()+1 + " " + jobject.get(ite.next()).getAsString());
				}
				
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		

	}

	public boolean sendSQLCommand() {
		try {
			return new DynamicCommand(this.conn, this.sql).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
