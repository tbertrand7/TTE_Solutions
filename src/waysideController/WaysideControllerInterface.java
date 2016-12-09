package waysideController;

import java.util.*;

/**
 * 
 * @author Alisha
 * This class handles communication between the wayside controllers and other components
 */
public class WaysideControllerInterface {
	private static WaysideControllerInterface instance = new WaysideControllerInterface();
	protected WaysideController[] WC = new WaysideController[4]; 
	
	/**
	 * starts all of the wayside controllers when instantiated
	 */
	private WaysideControllerInterface()
	{
		//blocks
		//insert in format: "block number - previous block <:previous switch block> - next block <:next switch block>"
		//if the block is uni-directional, previous block will be zero
		/*String[] blocks = new String[]{"1-16-2","2-1-3","3-2-4","4-3-5","5-4-6","6-5-7","7-1","8-1","9-1","10-1","11-1","12-1",
				"13-1","14-1","15-1","16-1:15-17","17-1","18-1","19-1","20-1","21-1","22-1","23-1","24-1",
				"25-1","26-1","27-1","28-1","29-1","30-1","31-1","32-1","33-1","34-1","35-1", "72-1","73-1","74-1","75-1","76-1","77-1"};
		String[] switches = new String[]{"6-0:15:16:1","12-0:9:10:77","7-0:27:28:76","8-0:32:33:72"};
		WC[0] = new WaysideController("Red",blocks,switches);*/
		
		String[] blocks = new String[]{"36-1","37-1","38-1","39-1","40-1","41-1","42-1"
				, "43-1","44-1","45-1","46-1","47-1","48-1","49-1","50-1",
				"51-1","52-1","53-1","54-1","55-1","56-1","57-1","58-1",
				"59-1","60-1","61-1","62-1","63-1","64-1","65-1","66-1","67-1",
				"68-1","69-1","70-1","71-1"};
		String[] switches = new String[]{"9-0:38:39:71","10-0:43:44:67","11-0:52:53:66"};
		WC[1] = new WaysideController("Red",blocks,switches);

		/*blocks = new String[]{ "1-0", "2-0", "3-0", "4-0","5-0","6-0","7-0",
				"8-0","9-0","10-0","11-0","12-0","13-1","14-1","15-1","16-1",
				"17-1","18-1","19-1","20-1","21-1","22-1","23-1","24-1","25-1",
				"26-1","27-1","28-1","29-2","30-2","31-2","32-2","33-2","34-2",
				"35-2","36-2","37-2","38-2","39-2","40-2","41-2","42-2","43-2",
				"44-2","45-2","46-2","47-2","48-2","49-2","50-2","51-2","52-2",
				"53-2","54-2","55-2","56-2","57-2","58-2","59-2","60-2","61-2",
				"62-2","63-2", "64-2", "65-2","148-2", "149-2", "150-0", "151-0", "152-0"};*/
		
	}
	
	/**
	 * Makes sure that this interface is only instantiated once
	 * is not disposed of until entire system exits
	 * @return the instance of the WaysideControllerInterface
	 */
	public static WaysideControllerInterface getInstance()
	{
		return instance;
	}
	
	/**
	 * In order to make sure that the CTC can route to Wayside
	 * their "location" (i.e. the reference to the running instance of the waysides) is part of this class
	 * CTC must pass the waysideID it wants to send the message to-- therefore it still has to be the component that
	 * routes to the correct wayside
	 * @param speed
	 * @param trainID
	 * @param waysideID
	 */
	public void setSpeed(double speed, int trainID, String waysideID)
	{
		switch(waysideID)
		{
			case "Red 1":
				WC[0].suggestSpeed(speed, trainID);
				break;
			case "Red 2":
				WC[1].suggestSpeed(speed, trainID);
				break;
			case "Green 1":
				WC[2].suggestSpeed(speed, trainID);
				break;
			case "Green 2":
				WC[3].suggestSpeed(speed, trainID);
				break;
		}
	}
	
	/**
	 * In order to make sure that the CTC can route to Wayside
	 * their "location" (i.e. the reference to the running instance of the waysides) is part of this class
	 * CTC must pass the waysideID it wants to send the message to-- therefore it still has to be the component that
	 * routes to the correct wayside
	 * @param destination
	 * @param trainID
	 * @param waysideID 
	 */
	public void setAuthority(int destination, int trainID, String waysideID)
	{
		switch(waysideID)
		{
			case "Red 1":
				WC[0].suggestAuthority(destination, trainID);
				break;
			case "Red 2":
				WC[1].suggestAuthority(destination, trainID);
				break;
			case "Green 1":
				WC[2].suggestAuthority(destination, trainID);
				break;
			case "Green 2":
				WC[3].suggestAuthority(destination, trainID);
				break;
		}
	}
}
