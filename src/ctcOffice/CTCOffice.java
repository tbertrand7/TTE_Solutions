package ctcOffice;

import trackModel.*;
import waysideController.*;
import java.sql.*;

public class CTCOffice
{
	private DBInteraction dataBase;
	
	public enum Mode {MAUNAL, AUTOMATIC};
	
	private int simulationSpeed;
	private Mode mode = Mode.MAUNAL; //1=manual 2=auto

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice()
	{
		greenLine = new TrackBlock[152];
		redLine = new TrackBlock[77];

		try {
			dataBase = new DBInteraction();
			loadTrackData();
		} catch (Exception e) {

		}
	}
	
	public void setMode(Mode newMode)
	{
		mode = newMode;
	}
	
	/** Set new simulation speed */
	public void setSimulationSpeed(int newSpeed)
	{
		simulationSpeed = newSpeed;
	}
	
	/** Suggest speed to wayside controller */
	public void suggestSpeed(int newTrainSpeed)
	{
		//TODO: also need to send train info
	}
	
	/** Returns the current simulation speed */
	public int getSimulationSpeed()
	{
		return simulationSpeed;
	}
	
	/** Returns if system is in Manual or Auto mode */
	public Mode getMode()
	{
		return mode;
	}

	/** Load in initial track data */
	private void loadTrackData()
	{
		for (int i=0; i < greenLine.length; i++)
		{
			try {
				greenLine[i] = dataBase.getSection("Green", i + 1);
				if (i < redLine.length)
					redLine[i] = dataBase.getSection("Red", i + 1);
			} catch(SQLException e) {

			}
		}
	}
}
