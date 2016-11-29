package ctcOffice;

import trackModel.*;
import waysideController.*;
import java.sql.*;

public class CTCOffice
{
	private TrackDBInteraction trackDB;
	
	public enum Mode {MANUAL, AUTOMATIC}
	
	private int simulationSpeed;
	private Mode mode = Mode.MANUAL;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice()
	{
        try {
            trackDB = new TrackDBInteraction();
            greenLine = new TrackBlock[152];
            redLine = new TrackBlock[77];
            loadTrackData();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**
     * Changes mode between manual or automatic
     * @param newMode new mode
     */
	public void setMode(Mode newMode)
	{
		mode = newMode;
	}
	
	/** Set new simulation speed */
	//TODO: Replace method with global system clock
	public void setSimulationSpeed(int newSpeed)
	{
		simulationSpeed = newSpeed;
	}

	public void suggestSpeed(int newTrainSpeed)
	{
		//TODO: suggest speed for a train to wayside controller
	}

	public void suggestDestination()
    {
        //TODO: suggest new destination for a train to wayside controller
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

	/**
     * Load in track data
     */
	public void loadTrackData()
	{
		for (int i=0; i < greenLine.length; i++)
		{
			try {
				greenLine[i] = trackDB.getSection("Green", i + 1);
				if (i < redLine.length)
					redLine[i] = trackDB.getSection("Red", i + 1);
			} catch(SQLException e) {
                e.printStackTrace();
			}
		}
//        try {
//            redLine = trackDB.getLine("Red");
//            greenLine = trackDB.getLine("Green");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
	}
}
