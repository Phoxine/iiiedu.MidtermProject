package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import module.DAO.IParkInfoDAO;
import module.DAO.ParkInfoBean;
import module.DAO.ParkInfoDAO;

public class demo {
	public static void main(String[] args) {

		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;";
		String dbName = "databaseName=MidTermProject;";
		String user = "sa";
		String password = "passw0rd";
		String filepath = "res/ParkInfo.json";
		String outputpath = "res/out.json";

		IParkInfoDAO dao = new ParkInfoDAO();
		try {
			dao.getConnection(url + dbName, user, password);	
			System.out.println("Insert " + dao.ImportJsonFile(filepath) + " rows"); 
			dao.ExportJsonFile(outputpath);	
			dao.findByParkID("P-TY-003").showDetail();
//			List<ParkInfoBean> list = dao.getAll();
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).showDetail();
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dao.closeConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
