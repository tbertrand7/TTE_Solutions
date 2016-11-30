package ctcOffice;

import trackModel.*;
import trackModel.TrackBlock.*;
import waysideController.*;
import java.sql.*;

public class CTCOffice
{
	private TrackDBInteraction trackDB;
	private TrackModel track;
	
	public enum Mode {MANUAL, AUTOMATIC}
	
	private int simulationSpeed;
	private Mode mode = Mode.MANUAL;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice()
	{
        try {
            trackDB = new TrackDBInteraction();
			track = new TrackModel();
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

	public String closeBlock(String line, int block)
	{
		TrackBlock currBlock;
		String rtnStr = "";

		//Get block from appropriate line
		if (line.equals("Red"))
			currBlock = redLine[block - 1];
		else
			currBlock = greenLine[block-1];

		//If closed set to unoccupied, otherwise set to closed
		if (currBlock.status == BlockStatus.CLOSED) {
			currBlock.status = BlockStatus.UNOCCUPIED;
			rtnStr = line + " Line: Block " + block + " opened";
		}
		else {
			currBlock.status = BlockStatus.CLOSED;
			rtnStr = line + " Line: Block " + block + " closed for maintenance";
		}

		track.setBlock(currBlock); //Update block in DB
		return rtnStr;
	}

	/**
     * Load in track data
     */
	public void loadTrackData()
	{
		for (int i=0; i < greenLine.length; i++)
		{
			greenLine[i] = track.getBlock("Green", i + 1);
			if (i < redLine.length)
				redLine[i] = track.getBlock("Red", i + 1);
		}
//        try {
//            redLine = trackDB.getLine("Red");
//            greenLine = trackDB.getLine("Green");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
	}
}
