package trackModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import trackModel.TrackBlock.BlockStatus;

public class TrackDBInteraction {
	Connection conn = null;

	public TrackDBInteraction() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//Connect to the database for the first time
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");

		Statement stmt = conn.createStatement();
		stmt.execute("SELECT * FROM TTEDB.RailLines");
		stmt.close();
	}

	//*************************************************************************
	//This method will access that database and return one track block object 
	//This object is determined by the input of line and block number
	//*************************************************************************
	public TrackBlock getSection(String line, int block) throws SQLException{
		TrackBlock tempBlock = new TrackBlock();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TTEDB.RailLines where line = '"+line+"' and BlockNumber = "+block);
		tempBlock = populateTrackBlock(rs);
		stmt.close();
		conn.close();


		return tempBlock;
	}
	
	//*************************************************************************
	//This method will access that database and return an array of trackBlocks 
	//The array is determined by the line and the contents of the index array 
	//*************************************************************************
	public TrackBlock[] getBlocks(String line, int [] blocks) throws SQLException{
		TrackBlock [] track = new TrackBlock[blocks.length];
		String[] blockNum = new String[blocks.length];
		for(int i=0; i< blocks.length; i++)
			blockNum[i] = String.valueOf(blocks[i]);
			
		String joined = String.join(",", blockNum);

		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TTEDB.RailLines where line = '"+line+"' and BlockNumber in ("+joined+")");
		rs.next();
		track = populateTrackLine(rs,track);
		stmt.close();
		conn.close();
		
		return track;
	}

	//*************************************************************************
	//This method will access that database and return and array of trackBlocks
	//The array it returns is representative of one of the track lines 
	//*************************************************************************
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
		rs.next();
		track = populateTrackLine(rs,track);
		stmt.close();
		conn.close();

		return track;
	}

	//*************************************************************************
	//This method will access that database and set on track block object 
	//This object is determined by the input of line and block number
	//*************************************************************************
	public boolean setSection(TrackBlock theBlock) throws SQLException{

		boolean sucess = false;

		try{
		Statement stmt = conn.createStatement();
		sucess = stmt.execute("UPDATE TTEDB.RailLines SET Section = '"+theBlock.section+"', BlockLength = "+theBlock.blockLength+", BlockGrade = "+theBlock.blockGrade+", SpeedLimit = "+theBlock.speedLimit+", Infrastructure = '"+theBlock.infrastructure+"', Elevation = "+theBlock.elevation+", CumalativeElevation = "+theBlock.cumualativeElevation+", SwitchBlock = '"+theBlock.switchBlock.id+"', SwitchPosition = "+theBlock.switchBlock.position+", ArrowDirection = '"+theBlock.arrowDirection+"', NumPass = "+theBlock.numPass+", temp = "+theBlock.temp+", status = '"+theBlock.status.ordinal()+"', Occupied = '"+theBlock.occupied+"', TrainID = '"+theBlock.trainID+"', Speed = '"+theBlock.speed+"', Authority = '"+theBlock.authority+"', NextBlock = '"+theBlock.nextBlock+"', Destination = '"+theBlock.destination+"' WHERE Line = '"+theBlock.line+"' and BlockNumber = "+theBlock.blockNumber+";");
		stmt.close();
		conn.close();
		
		}
		catch(SQLException e){
			JScrollPane parent = new JScrollPane();
			JOptionPane.showMessageDialog(parent,"SQL ERROR! Block not updated!\n"+e.getMessage(),"SQL ERROR", JOptionPane.ERROR_MESSAGE);		
		}
		
		return sucess;
		
	}
	
	//*************************************************************************
	//This method will access that database and set a track block object
	//This method will update the DB with all values except the TrainID
	//*************************************************************************
	public boolean setSectionPassive(TrackBlock theBlock) throws SQLException{

		boolean sucess = false;

		try{
		Statement stmt = conn.createStatement();
		sucess = stmt.execute("UPDATE TTEDB.RailLines SET Section = '"+theBlock.section+"', BlockLength = "+theBlock.blockLength+", BlockGrade = "+theBlock.blockGrade+", SpeedLimit = "+theBlock.speedLimit+", Infrastructure = '"+theBlock.infrastructure+"', Elevation = "+theBlock.elevation+", CumalativeElevation = "+theBlock.cumualativeElevation+", SwitchBlock = '"+theBlock.switchBlock.id+"', SwitchPosition = "+theBlock.switchBlock.position+", ArrowDirection = '"+theBlock.arrowDirection+"', NumPass = "+theBlock.numPass+", temp = "+theBlock.temp+", status = '"+theBlock.status.ordinal()+"', Occupied = '"+theBlock.occupied+"', Speed = '"+theBlock.speed+"', Authority = '"+theBlock.authority+"', NextBlock = '"+theBlock.nextBlock+"', Destination = '"+theBlock.destination+"' WHERE Line = '"+theBlock.line+"' and BlockNumber = "+theBlock.blockNumber+";");
		stmt.close();
		conn.close();
		
		}
		catch(SQLException e){
			JScrollPane parent = new JScrollPane();
			JOptionPane.showMessageDialog(parent,"SQL ERROR! Block not updated!\n"+e.getMessage(),"SQL ERROR", JOptionPane.ERROR_MESSAGE);		
		}
		
		return sucess;
		
	}
	
	//*************************************************************************
	//This method will access that database and break a block from given input
	//*************************************************************************
	public boolean breakSection(String line, int block) throws SQLException{
		
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = '3', Status = '3' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();
		conn.close();

		return sucess;
	}

	//*************************************************************************
	//This method will access that database and cut a rail from given input
	//*************************************************************************
	public boolean cutSection(String line, int block) throws SQLException {
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = '4', Status = '4' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();
		conn.close();

		return sucess;
	}

	//*************************************************************************
	//This method will access that database and break a circuit from given input 
	//*************************************************************************
	public boolean breakSectionCircuit(String line, int block) throws SQLException {
		Statement stmt = conn.createStatement();
		boolean sucess = stmt.execute("UPDATE TTEDB.RailLines SET Status = '5', Status = '5' where Line = '"+line+"' and BlockNumber = "+block+";");
		stmt.close();
		conn.close();

		return sucess;
	}

	//*************************************************************************
	//This method will access that database and reset it to the initial state
	//*************************************************************************
	public void resetDB() throws SQLException, IOException, InterruptedException {
		Statement stmt = conn.createStatement();
		InputStream in = getClass().getResourceAsStream("/shared/NEWWIPE.sql"); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		//BufferedReader in = new BufferedReader(new FileReader("src/shared/NEWWIPE.sql"));
		String str;
		while ((str = reader.readLine()) != null) {

			stmt.execute(str);
		}
		in.close();
		conn.close();

		stmt.close();

	}

	//*************************************************************************
	//This method is used to populate a trackBlock object from the results of 
	//a SQL commands execution
	//*************************************************************************
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
			section.status = BlockStatus.values()[(rs.getInt(16))];
			section.occupied = rs.getString(17);
			section.trainID = rs.getInt(18);
			section.speed = rs.getDouble(19);
			section.authority = rs.getInt(20);
			section.nextBlock = rs.getInt(21);
			section.destination = rs.getInt(22);
		}
		return section;
	}

	//*************************************************************************
	//This method is used to populate a trackBlock array from the results of a 
	//SQL commands execution
	//*************************************************************************
	private TrackBlock[] populateTrackLine(ResultSet rs, TrackBlock[] line) throws SQLException{
		TrackBlock section = new TrackBlock();

		for (int i =0; i<line.length; i++) {
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
			section.status = BlockStatus.values()[(rs.getInt(16))];
			section.occupied = rs.getString(17);
			section.trainID = rs.getInt(18);
			section.speed = rs.getDouble(19);
			section.authority = rs.getInt(20);
			section.nextBlock = rs.getInt(21);
			section.destination = rs.getInt(22);

			line[i] = section;
			section = new TrackBlock();
			rs.next();
		}
		return line;
	}
}
