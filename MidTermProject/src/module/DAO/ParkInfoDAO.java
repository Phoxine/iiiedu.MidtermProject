package module.DAO;

import module.filehandle.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParkInfoDAO implements IParkInfoDAO {

	// constant field
	private static final String INSERT_STMT = "INSERT INTO parkinfo values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE parkinfo set areaId = ?, areaName = ?, parkName = ?, totalSpace = ?, surplusSpace = ?, payGuide = ?, introduction = ?, address = ?, wgsX = ?, wgsY = ? where parkId = ?";
	private static final String DELETE_STMT = "DELETE parkinfo where parkId = ?";
	private static final String GET_ONE_STMT = "select parkId, areaId, areaName, parkName, totalSpace, surplusSpace, payGuide, introduction, address, wgsX, wgsY from parkinfo where parkId = ?";
	private static final String GET_ALL_STMT = "select parkId, areaId, areaName, parkName, totalSpace, surplusSpace, payGuide, introduction, address, wgsX, wgsY from parkinfo order by parkId";
	private static final String sqlserver_jdbcdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private Connection conn = null;

	

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return this.conn;
	}

	@Override
	public void getConnection(String url, String user, String password) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName(sqlserver_jdbcdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver Error");
		}
		this.conn = DriverManager.getConnection(url, user, password);
		if (!conn.isClosed()) {
			System.out.println("DataBase Connected");
		}
	}

	@Override
	public void closeConn() throws SQLException {
		// TODO Auto-generated method stub
		if (this.conn != null) {
			this.conn.close();
			System.out.println("DataBase Disconnected");
		}
	}

	@Override
	public int insert(ParkInfoBean parkinfo) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = this.conn.prepareStatement(INSERT_STMT);
		pstmt.setString(1, parkinfo.getParkId());
		pstmt.setInt(2, parkinfo.getAreaId());
		pstmt.setString(3, parkinfo.getAreaName());
		pstmt.setString(4, parkinfo.getParkName());
		pstmt.setInt(5, parkinfo.getTotalSpace());
		pstmt.setInt(6, parkinfo.getSurplusSpace());
		pstmt.setString(7, parkinfo.getPayGuide());
		pstmt.setString(8, parkinfo.getIntroduction());
		pstmt.setString(9, parkinfo.getAddress());
		pstmt.setDouble(10, parkinfo.getWgsX());
		pstmt.setDouble(11, parkinfo.getWgsY());
		return pstmt.executeUpdate();
	}

	@Override
	public int update(ParkInfoBean parkinfo) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_STMT);
		pstmt.setInt(1, parkinfo.getAreaId());
		pstmt.setString(2, parkinfo.getAreaName());
		pstmt.setString(3, parkinfo.getParkName());
		pstmt.setInt(4, parkinfo.getTotalSpace());
		pstmt.setInt(5, parkinfo.getSurplusSpace());
		pstmt.setString(6, parkinfo.getPayGuide());
		pstmt.setString(7, parkinfo.getIntroduction());
		pstmt.setString(8, parkinfo.getAddress());
		pstmt.setDouble(9, parkinfo.getWgsX());
		pstmt.setDouble(10, parkinfo.getWgsY());
		pstmt.setString(11, parkinfo.getParkId());
		return pstmt.executeUpdate();
	}

	@Override
	public PreparedStatement addBatch(ParkInfoBean parkinfo, PreparedStatement pstmt) throws SQLException {
		// TODO Auto-generated method stub
		pstmt.setString(1, parkinfo.getParkId());
		pstmt.setInt(2, parkinfo.getAreaId());
		pstmt.setString(3, parkinfo.getAreaName());
		pstmt.setString(4, parkinfo.getParkName());
		pstmt.setInt(5, parkinfo.getTotalSpace());
		pstmt.setInt(6, parkinfo.getSurplusSpace());
		pstmt.setString(7, parkinfo.getPayGuide());
		pstmt.setString(8, parkinfo.getIntroduction());
		pstmt.setString(9, parkinfo.getAddress());
		pstmt.setDouble(10, parkinfo.getWgsX());
		pstmt.setDouble(11, parkinfo.getWgsY());
		pstmt.addBatch();
		return pstmt;
	}

	@Override
	public int[] batchExecute(PreparedStatement pstmt) throws SQLException {
		return pstmt.executeBatch();
	}

	@Override
	public int delete(String parkId) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {
			pstmt.setString(1, parkId);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public ParkInfoBean findByParkID(String parkId) throws SQLException {
		// TODO Auto-generated method stub
		ParkInfoBean parkinfo = null;
		try (PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {
			pstmt.setString(1, parkId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				parkinfo = new ParkInfoBean();
				parkinfo.setParkId(rs.getString("parkId"));
				parkinfo.setAreaId(rs.getInt("areaId"));
				parkinfo.setAreaName(rs.getString("areaName"));
				parkinfo.setParkName(rs.getString("parkName"));
				parkinfo.setTotalSpace(rs.getInt("totalSpace"));
				parkinfo.setSurplusSpace(rs.getInt("surplusSpace"));
				parkinfo.setPayGuide(rs.getString("payGuide"));
				parkinfo.setIntroduction(rs.getString("introduction"));
				parkinfo.setAddress(rs.getString("address"));
				parkinfo.setWgsX(rs.getDouble("WgsX"));
				parkinfo.setWgsY(rs.getDouble("WgsY"));
			}
			rs.close();
			return parkinfo;
		}

	}

	@Override
	public List<ParkInfoBean> getAll() throws SQLException {
		// TODO Auto-generated method stub
		List<ParkInfoBean> parks = new ArrayList<>();
		PreparedStatement pstmt = this.conn.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			parks.add(new ParkInfoBean(rs.getString("parkId"), rs.getInt("areaId"), rs.getString("areaName"),
					rs.getString("parkName"), rs.getInt("totalSpace"), rs.getInt("surplusSpace"),
					rs.getString("payGuide"), rs.getString("introduction"), rs.getString("address"),
					rs.getDouble("wgsX"), rs.getDouble("wgsY")));
		}
		return parks;
	}

	@Override
	public int ImportJsonAsString(String json) throws SQLException {

		int count = 0;
		JsonArray jarray = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray("parkingLots");
		try (PreparedStatement pstmt = this.conn.prepareStatement(INSERT_STMT);) {
			for (int i = 0; i < jarray.size(); i++) {
				JsonObject obj = jarray.get(i).getAsJsonObject();
				addBatch(new ParkInfoBean(obj.get("parkId").getAsString(), obj.get("areaId").getAsInt(),
						obj.get("areaName").getAsString(), obj.get("parkName").getAsString(),
						obj.get("totalSpace").getAsInt(), obj.get("surplusSpace").getAsInt(),
						obj.get("payGuide").getAsString(), obj.get("introduction").getAsString(),
						obj.get("address").getAsString(), obj.get("wgsX").getAsDouble(), obj.get("wgsY").getAsDouble()),
						pstmt);
				count += 1;
			}
			batchExecute(pstmt);
			return count;

		}
	}

	@Override
	public int ImportJsonFile(String filepath) throws SQLException, IOException {
		// TODO Auto-generated method stub
		return ImportJsonAsString(new ReadJson(filepath).readFile());
	}

	@Override
	public String ExportJsonAsString() throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement pstmt = this.conn.prepareStatement(GET_ALL_STMT);) {
			return ExportJSON.ResultSetToJsonString(pstmt.executeQuery());
		}
	}

	@Override
	public void ExportJsonFile(String OutputPath) throws SQLException, IOException {
		try (BufferedWriter bos1 = new BufferedWriter(new FileWriter(OutputPath));) {
			bos1.write(ExportJsonAsString());
			System.out.println("Export file to "+ OutputPath);
		}
		
	}

}
