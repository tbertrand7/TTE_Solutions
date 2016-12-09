package ctcOffice;

import java.io.*;
import java.util.*;
import javax.swing.table.*;

import TTEHome.SystemClock;
import trackModel.*;
import trackModel.TrackBlock.*;
import waysideController.*;

public class CTCOffice
{
	private SystemClock sysClock;
	private OfficeUI officeUI;
	private TrackModel track;
	
	public enum Mode {MANUAL, AUTOMATIC}

	private Mode mode = Mode.MANUAL;
	private ScheduleItem[] schedule;
	private String loggedInUser;

	public TrackBlock[] greenLine, redLine;
	
	public CTCOffice(SystemClock clk)
	{
        try {
        	sysClock = clk;
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

    public void dispatchNewTrain(TrackBlock dest, double speed)
	{
		//TODO: integrate with wayside controller
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
	 * @return Notification string for block close/open
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

	public void loadSchedule(File f, DefaultTableModel tbl)
    {
        try {
            BufferedReader csv = new BufferedReader(new FileReader(f));

            String s;
            ArrayList<ScheduleItem> sched = new ArrayList<>();
            while ((s = csv.readLine()) != null)
            {
                String[] data = s.split(",");
                String[] infr = data[1].split(";");
                String dest = "";
                if (infr.length > 1)
                	dest = infr[1];
                else
                	dest = "Unnamed";

                sched.add(new ScheduleItem(data[0], -1, dest, Double.parseDouble(data[2])));
            }
            csv.close();
            schedule = sched.toArray(new ScheduleItem[10]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Clear table
        while (tbl.getRowCount() != 0)
		{
			tbl.removeRow(0);
		}

		//Add new schedule to table
        for (int i=0; i < schedule.length; i++)
		{
			tbl.addRow(new Object[] {schedule[i].line, schedule[i].train, schedule[i].destination, schedule[i].time});
		}
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
