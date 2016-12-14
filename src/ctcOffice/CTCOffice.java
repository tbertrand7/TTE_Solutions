package ctcOffice;

import java.io.*;
import java.util.*;
import javax.swing.table.*;

import TTEHome.SystemClock;
import trackModel.*;
import trackModel.TrackBlock.*;
import waysideController.*;
import trainController.*;

public class CTCOffice
{
	private SystemClock sysClock;
	private OfficeUI officeUI;
	private TrackModel track;
	private TrainControllerInstances trainCont;
	
	public enum Mode {MANUAL, AUTOMATIC}

	private Mode mode = Mode.MANUAL;
	private ScheduleItem[] redSchedule, greenSchedule;
	private String loggedInUser;
	long startTime;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice(SystemClock clk, TrainControllerInstances tci)
	{
        try {
        	startTime = System.currentTimeMillis(); //Get time for start of program, used for throughput calculation
        	sysClock = clk;
        	trainCont = tci;
			track = new TrackModel();
            greenLine = new TrackBlock[152];
            redLine = new TrackBlock[77];
            loadTrackData();
            loggedInUser = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * Display login screen
	 */
	public void initLogin()
	{
		new OfficeLogin(this);
	}

	/**
	 * Logout and display login screen
	 */
	public void logout()
	{
		loggedInUser = "";
		officeUI.dispose();
		initLogin();
	}

	/**
	 * Display office UI on successful login
	 * @param username logged in username
	 */
	public void loginSuccess(String username)
	{
		loggedInUser = username;
		officeUI = new OfficeUI(this);
		officeUI.logNotification("Logged in as " + loggedInUser);
		officeUI.setVisible(true);
	}

    /**
     * Changes mode between manual or automatic
     * @param newMode new mode
     */
	public void setMode(Mode newMode)
	{
		mode = newMode;

		if (newMode == Mode.AUTOMATIC)
			officeUI.logNotification("Auto Mode Set");
		else
			officeUI.logNotification("Manual Mode Set");
	}

	/**
	 * Change global system clock
	 * @param newSpeed new simulation speed
	 */
	public void setSimulationSpeed(int newSpeed)
	{
		sysClock.clock = newSpeed;
		officeUI.logNotification("New Simulation Speed is " + newSpeed + "X wall clock speed");
	}

	/**
	 * Get current simulation speed from sysClock
	 * @return Current simulation speed
	 */
	public int getSimulationSpeed()
	{
		return sysClock.clock;
	}

	public void suggestSpeed(double newTrainSpeed, int train, TrackBlock currBlock)
	{
		//TODO: suggest speed for a train to wayside controller
		String wayside = routeWaysideSuggestion(currBlock.line, currBlock.blockNumber);
		WaysideControllerInterface.getInstance().suggestSpeed(newTrainSpeed, train, wayside);
	}

	public void suggestDestination(TrackBlock dest, int train, TrackBlock currBlock)
    {
        //TODO: suggest new destination for a train to wayside controller
		String wayside = routeWaysideSuggestion(currBlock.line, currBlock.blockNumber);
		WaysideControllerInterface.getInstance().suggestAuthority(dest.blockNumber, train, wayside);
    }

	/**
	 * Routes suggestion to proper wayside controller
	 * @param line Line train is on
	 * @param block Block train is on
	 * @return String for wayside controller
	 */
    private String routeWaysideSuggestion(String line, int block)
	{
		String wayside = "";
		if (line.equals("Red")) {
			if (block >= 36 && block <= 71)
				wayside = "Red 2";
			else
				wayside = "Red 1";
		} else {
			if (block >= 66 && block <= 133)
				wayside = "Green 2";
			else
				wayside = "Green 1";
		}
		return wayside;
	}

    /**
     * Dispatch new train from yard
     * @param dest destination block of new train
     * @param speed speed for new train
     */
    public void dispatchNewTrain(TrackBlock dest, double speed)
	{
		//TODO: integrate with wayside controller
        //Create new train and get ID for wayside
        int newTrainID = trainCont.createTrain(dest.line);

        //Get starting block from yard for correct line
		TrackBlock currBlock;
		if (dest.line.equals("Red"))
			currBlock = redLine[76]; //Red Line is block 77
		else
			currBlock = greenLine[151]; //Green Line is block 152

        //Send destination and speed requests for new train
        suggestDestination(dest, newTrainID, currBlock);
        suggestSpeed(speed, newTrainID, currBlock);

		officeUI.logNotification("Train dispatched from yard to " + dest.toString() + " at " + speed + " mph");
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
	 */
	public void closeBlock(String line, int block)
	{
		TrackBlock currBlock;

		//Get block from appropriate line
		if (line.equals("Red"))
			currBlock = redLine[block - 1];
		else
			currBlock = greenLine[block-1];

		//If closed set to unoccupied, otherwise set to closed
		if (currBlock.status == BlockStatus.CLOSED) {
			currBlock.status = BlockStatus.UNOCCUPIED;
			officeUI.logNotification(line + " Line: Block " + block + " opened");
		}
		else {
			if (currBlock.trainID > 0) {
				officeUI.logNotification("ERROR: Block " + block + " cannot be closed because it is occupied by a train");
			} else {
				currBlock.status = BlockStatus.CLOSED;
				officeUI.logNotification(line + " Line: Block " + block + " closed for maintenance");
			}
		}

		track.setBlock(currBlock); //Update block in DB
	}

	/**
	 * Toggles position of switch located on selected block
	 * @param block block with switch to toggle
	 */
	public void toggleSwitch(TrackBlock block)
	{
		String wayside = routeWaysideSuggestion(block.line, block.blockNumber);
		int switchNum = Integer.parseInt(block.switchBlock.id.split(" ")[1]);
		WaysideControllerInterface.getInstance().toggleSwitch(switchNum, wayside);
	}

	/**
	 * Loads in schedule file and updates schedule grid
	 * @param f Schedule file to load in
	 * @param tblRedLine Red line table reference
     * @param tblGreenLine Green line table reference
	 */
	public void loadSchedule(File f, DefaultTableModel tblRedLine, DefaultTableModel tblGreenLine)
	{
		try {
			BufferedReader csv = new BufferedReader(new FileReader(f));

			String s;
			ArrayList<ScheduleItem> greenSched = new ArrayList<>();
			ArrayList<ScheduleItem> redSched = new ArrayList<>();
			int lastRed = 0, lastGreen = 0; //Needed to ensure schedules are in order
			while ((s = csv.readLine()) != null)
			{
				//Fix for first line of file not being properly parsed
				if (s.charAt(0) == '\uFEFF')
					s = s.substring(1);

				String[] data = s.split(",");
				String[] infr = data[1].split(";");
				String dest = "";
				TrackBlock tempBlock = null;
				TrackBlock[] line;

				if (infr.length > 1)
					dest = infr[1];
				else
					dest = infr[0];

				//Match dest string to appropriate track block
				int i;
				if (data[0].toLowerCase().equals("red")) {
					line = redLine;
					i = lastRed;
				}
				else {
					line = greenLine;
					i = lastGreen;
				}

				for ( ; i < line.length; i++)
				{
					if (line[i].infrastructure.toUpperCase().contains(dest.toUpperCase())) {
						tempBlock = line[i];
						if (tempBlock.line.equals("Red"))
							lastRed = i+1;
						else
							lastGreen = i+1;
						break;
					}
				}

				if (tempBlock != null) {
					if (data[0].toLowerCase().equals("red"))
						redSched.add(new ScheduleItem(data[0], tempBlock, Double.parseDouble(data[2])));
					else
						greenSched.add(new ScheduleItem(data[0], tempBlock, Double.parseDouble(data[2])));
				}
				else
					officeUI.logNotification("Destination: " + dest + " does not exist, schedule item not added");
			}
			csv.close();
			greenSchedule = greenSched.toArray(new ScheduleItem[1]);
			redSchedule = redSched.toArray(new ScheduleItem[1]);
			officeUI.logNotification("Schedule " + f.getName() + " successfully loaded");
		} catch (Exception e) {
			e.printStackTrace();
			officeUI.logNotification("Error loading schedule " + f.getName());
		}

		//Clear tables
		while (tblRedLine.getRowCount() != 0)
		{
			tblRedLine.removeRow(0);
		}
        while (tblGreenLine.getRowCount() != 0)
        {
            tblGreenLine.removeRow(0);
        }

		//Add new schedule to table
		for (int i=0; i < redSchedule.length; i++)
		{
			tblRedLine.addRow(new Object[] {redSchedule[i].destination.toString(), redSchedule[i].time});
		}
		for (int i=0; i < greenSchedule.length; i++)
		{
			tblGreenLine.addRow(new Object[] {greenSchedule[i].destination.toString(), greenSchedule[i].time});
		}
	}

    public void runSchedule()
	{
		//TODO: Implement run schedule
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
