package trackModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInteraction {
	Connection conn = null;
	
	public DBInteraction() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//Connect to the database for the first time!'
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");

		Statement stmt = conn.createStatement();
		stmt.execute("SELECT * FROM TTEDB.RailLines");
		stmt.close();
	}
	public TrackBlock getSection(String line, int block) throws SQLException{
		TrackBlock section = new TrackBlock();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TTEDB.RailLines where line = '"+line+"' and BlockNumber = "+block);
		while(rs.next()){
			section.pk = rs.getInt(1);
			section.line = rs.getString(2);
			section.section = rs.getString(3);
			section.blockNumber = rs.getInt(4);
			section.blockLength = rs.getDouble(5);
			section.blockGrade = rs.getDouble(6);
			section.speedLimit = rs.getDouble(7);
			section.infrastructure = rs.getString(8);
			section.elevation = rs.getDouble(9);
			section.cumalativeElevation = rs.getDouble(10);
			section.switchBlock = rs.getString(11);
			section.arrowDirection = rs.getString(12);
			section.numPass = rs.getInt(13);
			section.temp = rs.getInt(14);
			section.status = rs.getString(15);
			section.occupiedBy = rs.getString(16);
		}
		stmt.close();
		
		return section;
	}
}
