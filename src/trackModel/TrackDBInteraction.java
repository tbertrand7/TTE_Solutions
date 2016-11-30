package trackModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrackDBInteraction {
	Connection conn = null;

	public TrackDBInteraction() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//Connect to the database for the first time!'
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");

		Statement stmt = conn.createStatement();
		stmt.execute("SELECT * FROM TTEDB.RailLines");
		stmt.close();
	}

	public TrackBlock getSection(String line, int block) throws SQLException{
		TrackBlock tempBlock = new TrackBlock();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TTEDB.RailLines where line = '"+line+"' and BlockNumber = "+block);
		tempBlock = populateTrackBlock(rs);
		stmt.close();

		return tempBlock;
	}

	public TrackBlock[] getLine(String line) throws SQLException{
		
		TrackBlock [] track = null;
		
		if(line.equals("Red")){
			 track = new TrackBlock[77];
		}
		else{
			track = new TrackBlock[152];
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TTEDB.RailLines where line = '"+line+"'order by BlockNumber");
		track = populateTrackLine(rs,track);
		stmt.close();

		return track;
	}

	public boolean setSection(TrackBlock theBlock) throws SQLException{
		Statement stmt = conn.createStatement();
		theBlock.nextBlock = 666;
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Section = '"+theBlock.section+"', BlockLength = "+theBlock.blockLength+", BlockGrade = "+theBlock.blockGrade+", SpeedLimit = "+theBlock.speedLimit+", Infrastructure = '"+theBlock.infrastructure+"', Elevation = "+theBlock.elevation+", CumalativeElevation = "+theBlock.cumualativeElevation+", SwitchBlock = '"+theBlock.switchBlock.id+"', SwitchPosition = "+theBlock.switchBlock.position+", ArrowDirection = '"+theBlock.arrowDirection+"', NumPass = "+theBlock.numPass+", temp = "+theBlock.temp+", status = '"+theBlock.status+"', Occupied = '"+theBlock.occupied+"', TrainID = '"+theBlock.trainID+"', Speed = '"+theBlock.speed+"', Authority = '"+theBlock.authority+"', NextBlock = '"+theBlock.nextBlock+"' WHERE Line = '"+theBlock.line+"' and BlockNumber = "+theBlock.blockNumber+";");
		stmt.close();

		return sucess;
	}

	public boolean breakSection(String line, int block) throws SQLException{
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = 'BROKEN', Status = 'BROKEN' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();


		return sucess;
	}

	public boolean cutSection(String line, int block) throws SQLException {
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = 'POWER CUT', Status = 'POWER CUT' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();


		return sucess;
	}

	public boolean breakSectionCircuit(String line, int block) throws SQLException {
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = 'CIRCUIT BROKEN', Status = 'CIRCUIT BROKEN' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();

		return sucess;
	}

	public void resetDB() throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		BufferedReader in = new BufferedReader(new FileReader("src/shared/DBWipe.sql"));
		String str;
		while ((str = in.readLine()) != null) {
			stmt.execute(str);
		}
		in.close();

		stmt.close();

	}

	private TrackBlock populateTrackBlock(ResultSet rs) throws SQLException{
		TrackBlock section = new TrackBlock();
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
			section.cumualativeElevation = rs.getDouble(10);
			section.switchBlock.id = rs.getString(11);
			section.switchBlock.position = rs.getString(12);
			section.arrowDirection = rs.getString(13);
			section.numPass = rs.getInt(14);
			section.temp = rs.getInt(15);
			section.status = rs.getString(16);
			section.occupied = rs.getString(17);
			section.trainID = rs.getInt(18);
			section.speed = rs.getDouble(19);
			section.authority = rs.getInt(20);
			section.nextBlock = rs.getInt(21);
		}
		return section;
	}

	private TrackBlock[] populateTrackLine(ResultSet rs, TrackBlock[] line) throws SQLException{
		TrackBlock section = new TrackBlock();
		for(int i =0; i<line.length; i++){
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
				section.cumualativeElevation = rs.getDouble(10);
				section.switchBlock.id = rs.getString(11);
				section.switchBlock.position = rs.getString(12);
				section.arrowDirection = rs.getString(13);
				section.numPass = rs.getInt(14);
				section.temp = rs.getInt(15);
				section.status = rs.getString(16);
				section.occupied = rs.getString(17);
				section.trainID = rs.getInt(18);
				section.speed = rs.getDouble(19);
				section.authority = rs.getInt(20);	
				section.nextBlock = rs.getInt(21);
			}
			line[i] = section;
		}
		return line;
	}

}
