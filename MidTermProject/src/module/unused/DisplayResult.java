package module.unused;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayResult {

	private ResultSet rs = null;

	public DisplayResult(ResultSet rs) {
		this.rs  = rs;
	}
	
	public String display(String ...field) throws SQLException {
		String buffer = "";
		String fieldName = "";
		for (int i = 0; i < field.length; i++) {
			fieldName+=field[i] + "      ";
		}
		while (rs.next()) {
			for (int i = 0; i < field.length; i++) {
				buffer += rs.getString(field[i]) + "\t";
			}
			buffer+="\n";
		} 
		return fieldName + "\n--------------------------------------------------\n" + buffer;
	}
	
}