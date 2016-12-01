package ctcOffice;

import java.io.*;

import trackModel.*;
import trackModel.TrackBlock.*;
import waysideController.*;

public class CTCOffice
{
	private TrackModel track;
	
	public enum Mode {MANUAL, AUTOMATIC}
	
	private int simulationSpeed;
	private Mode mode = Mode.MANUAL;
	private ScheduleItem[] schedule;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice()
	{
        try {
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

	public void suggestSpeed(double newTrainSpeed, int train)
	{
		//TODO: suggest speed for a train to wayside controller
		TTEHome.TTEHomeGUI.wc.suggestSpeed(newTrainSpeed, train);
	}

	public void suggestDestination(TrackBlock dest, int train)
    {
        //TODO: suggest new destination for a train to wayside controller
		//System.out.println("HERE");

		TTEHome.TTEHomeGUI.wc.suggestAuthority(dest.blockNumber, train);
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
	 * Closes/Opens a selected block
	 * @param line line the block is on
	 * @param block block number
	 * @return Notification string for block close/open
	 */
	public String closeBlock(String line, int block)
	{
		TrackBlock currBlock;
		String rtnStr;

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

	public void loadSchedule(File file)
    {
        //TODO: Process loaded csv
    }

	public int calcThroughput(TrackBlock block)
	{
		//TODO: Calculate throughput
		return 0;
	}

	/**
     * Load in track data
     */
	public void loadTrackData()
	{
        greenLine = track.getBlock("Green");
        redLine = track.getBlock("Red");
	}
}
