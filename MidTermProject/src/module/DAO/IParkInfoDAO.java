package module.DAO;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface IParkInfoDAO {
	public Connection getConn();
	public void getConnection(String url, String user, String password) throws SQLException;
	public void closeConn() throws SQLException;
	public int insert(ParkInfoBean parkinfo) throws SQLException;
	public int  update(ParkInfoBean parkinfo) throws SQLException;
	public PreparedStatement addBatch(ParkInfoBean parkinfo ,  PreparedStatement pstmt) throws SQLException;
	public int[] batchExecute(PreparedStatement pstmt) throws SQLException;
	public int delete(String parkId) throws SQLException;
	public ParkInfoBean findByParkID(String parkId) throws SQLException;
	public List<ParkInfoBean> getAll() throws SQLException;
	public int ImportJsonAsString(String json) throws SQLException;
	public int ImportJsonFile(String filepath) throws SQLException,IOException;
	public String ExportJsonAsString() throws SQLException;
	public void ExportJsonFile(String OutputPath) throws SQLException,IOException;
}
