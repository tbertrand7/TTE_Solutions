package ctcOffice;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
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
	private ArrayList<Integer> returnTrains; //Needed for runSchedule, keeps track of trains that reached last station
	private ScheduledExecutorService exec;
	private ScheduledFuture<?> schedUpdate;
	boolean scheduleRunning;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice(SystemClock clk, TrainControllerInstances tci)
	{
        try {
        	startTime = System.currentTimeMillis(); //Get time for start of program, used for throughput calculation
            scheduleRunning = false;
        	sysClock = clk;
        	trainCont = tci;
			track = new TrackModel();
            greenLine = new TrackBlock[152];
            redLine = new TrackBlock[77];
            greenSchedule = new ScheduleItem[0];
			redSchedule = new ScheduleItem[0];
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

	/**
	 * Suggest speed for train to wayside controller
	 * @param newTrainSpeed new speed in mph
	 * @param train id of train for the suggested speed
	 * @param currBlock current block train is on. Needed for wayside routing
	 */
	public void suggestSpeed(double newTrainSpeed, int train, TrackBlock currBlock)
	{
		String wayside = routeWaysideSuggestion(currBlock.line, currBlock.blockNumber);
		WaysideControllerInterface.getInstance().suggestSpeed(newTrainSpeed, train, wayside);
	}

	/**
	 * Suggest destination for train to wayside controller
	 * @param dest new destination for train
	 * @param train id of train being rerouted
	 * @param currBlock current block train is on. Needed for wayside routing
	 */
	public void suggestDestination(TrackBlock dest, int train, TrackBlock currBlock)
    {
		String wayside = routeWaysideSuggestion(currBlock.line, currBlock.blockNumber);
		currBlock.destination = dest.blockNumber;
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
        //Create new train and get ID for wayside
        int newTrainID = trainCont.createTrain(dest.line);

        //Wait to ensure train is created in db
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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

	/**
	 * Returns if system is in Manual or Auto mode
	 * @return Mode of system
	 */
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
		officeUI.logNotification(block.switchBlock.getID() + " position toggled");
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
		returnTrains = new ArrayList<>();

		//TODO: Test runSchedule logic
		/* Set initial authorities for schedule */
		for (int i=0; i < greenLine.length; i++)
		{
			//Red Line
			if (i < redLine.length && redLine[i].trainID > 0)
			{
				int currBlock = redLine[i].blockNumber;
				int train = redLine[i].trainID;
				for (int j=0; j < redSchedule.length; j++)
				{
					//Train is located before first dest on schedule
					if (j==0 && redSchedule[j].destination.blockNumber > currBlock)
					{
						suggestDestination(redSchedule[j].destination, train, redLine[i]);
						suggestSpeed(redLine[i].speedLimit, train, redLine[i]);
					}
					//Train is after last dest on schedule
					else if (j == redSchedule.length-1 && redSchedule[j].destination.blockNumber < currBlock)
					{
						suggestDestination(redSchedule[j].destination, train, redLine[i]);
						suggestSpeed(redLine[i].speedLimit, train, redLine[i]);
					}
					//Train is between destinations on schedule
					else if (redSchedule[j].destination.blockNumber < currBlock &&
							redSchedule[j+1].destination.blockNumber > currBlock)
					{
						suggestDestination(redSchedule[j+1].destination, train, redLine[i]);
						suggestSpeed(redLine[i].speedLimit, train, redLine[i]);
					}

				}
			}

			//Green Line
			if (greenLine[i].trainID > 0)
			{
				int currBlock = greenLine[i].blockNumber;
				int train = greenLine[i].trainID;
				for (int j=0; j < greenSchedule.length; j++)
				{
					//Train is located before first dest on schedule
					if (j==0 && redSchedule[j].destination.blockNumber > currBlock)
					{
						suggestDestination(greenSchedule[j].destination, train, greenLine[i]);
						suggestSpeed(greenLine[i].speedLimit, train, greenLine[i]);
					}
					//Train is after last dest on schedule
					else if (j == greenSchedule.length-1 && greenSchedule[j].destination.blockNumber < currBlock)
					{
						suggestDestination(greenSchedule[j].destination, train, greenLine[i]);
						suggestSpeed(greenLine[i].speedLimit, train, greenLine[i]);
					}
					//Train is between destinations on schedule
					else if (greenSchedule[j].destination.blockNumber < currBlock &&
							greenSchedule[j+1].destination.blockNumber > currBlock)
					{
						suggestDestination(greenSchedule[j+1].destination, train, greenLine[i]);
						suggestSpeed(greenLine[i].speedLimit, train, greenLine[i]);
					}
				}
			}
		}

		/* Run thread to check for trains at stations to suggest next authority in schedule */
		exec = Executors.newSingleThreadScheduledExecutor();
		schedUpdate = exec.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
			    //Green Line
			    for (int i=0; i < greenSchedule.length; i++)
                {
                    //Get up to date block info
                    TrackBlock block = greenLine[greenSchedule[i].destination.blockNumber-1];
                    int train = block.trainID;

                    //Train is at stop
                    if (train > 0)
                    {
                        //Train is at last station
                        if (i == greenSchedule.length-1) //Last stop
                        {
                            suggestDestination(greenSchedule[i-1].destination, train, block);
                            suggestSpeed(block.speedLimit, train, block);
                            returnTrains.add(train);
                        }
                        else
                        {
                            suggestDestination(greenSchedule[i+1].destination, train, block);
                            suggestSpeed(block.speedLimit, train, block);

                            //Train reached first station
                            if (i == 0 && returnTrains.contains(train))
                            {
                                returnTrains.remove(returnTrains.indexOf(train));
                            }
                        }
                    }
                }
                //Red Line
                for (int i=0; i < redSchedule.length; i++)
                {
                    //Get up to date block info
                    TrackBlock block = redLine[redSchedule[i].destination.blockNumber-1];
                    int train = block.trainID;

                    //Train is at stop
                    if (train > 0)
                    {
                        //Train is at last station
                        if (i == redSchedule.length-1) //Last stop
                        {
                            suggestDestination(redSchedule[i-1].destination, train, block);
                            suggestSpeed(block.speedLimit, train, block);
                            returnTrains.add(train);
                        }
                        else
                        {
                            suggestDestination(redSchedule[i+1].destination, train, block);
                            suggestSpeed(block.speedLimit, train, block);

                            //Train reached first station
                            if (i == 0 && returnTrains.contains(train))
                            {
                                returnTrains.remove(returnTrains.indexOf(train));
                            }
                        }
                    }
                }
			}
		}, 0, 1, TimeUnit.SECONDS);
		scheduleRunning = true;
		officeUI.logNotification("Schedule Running...");
	}

    /**
     * Stops schedule from running
     */
	public void stopSchedule()
    {
        schedUpdate.cancel(true);
        scheduleRunning = false;
        officeUI.logNotification("Schedule Stopped");
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
