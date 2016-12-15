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
		
		//switches
		//format: switch number - block switch is on : blocks connected position 1 : blocks connected in position 2
		
		/*		String[] blocks = new String[]{"1-16-2-6","2-1-3","3-2-4","4-3-5","5-4-6","6-5-7","7-6-8","8-7-9","9-8-10:77-12","10-9-11-12","11-10-12","12-11-13",
				"13-12-14","14-13-15","15-14-16-6","16-15:1-17-6","17-16-18","18-17-19","19-18-20","20-19-21","21-20-22","22-21-23","23-22-24","24-23-25",
				"25-24-26","26-25-27","27-26-28:76-7","28-27-29-7","29-28-30","30-29-31","31-30-32","32-31-33-8","33-32:72-34-8","34-33-35","35-34-.", "72-73-33-8",
				"73-74-72","74-75-73","75-76-74","76-27-75-7","77-9-y-12"};
		String[] switches = new String[]{"6-15:15,16:1,16","12-9:9,10:9,77","7-27:27,28:27,76","8-32:32,33:72,33"};
		WC[0] = new WaysideController("Red",blocks,switches, null,"C:\\Users\\Alisha\\git\\TTE_Solutions\\bin\\waysideController\\red1.txt");*/
		
		String[] blocks = new String[]{"1-2-16-6","2-3-1","3-4-2","4-5-3","5-6-4","6-7-5","7-8-6","8-9-7","9-10:77-8-12","10-11-9-12","11-12-10","12-13-11",
				"13-14-12","14-15-13","15-16-14-6","16-17-15:1-6","17-18-16","18-19-17","19-20-18","20-21-19","21-22-20","22-23-21","23-24-22","24-25-23",
				"25-26-24","26-27-25","27-28:76-26-7","28-29-27-7","29-30-28","30-31-29","31-32-30","32-33-31-8","33-34-32:72-8","34-35-33","35-.-34", "72-33-73-8",
				"73-72-74","74-73-75","75-74-76","76-75-27-7","77-y-9-12"};
		String[] switches = new String[]{"6-15:15,16:1,16","12-9:9,10:9,77","7-27:27,28:27,76","8-32:32,33:72,33"};
		WC[0] = new WaysideController("Red",blocks,switches, null,null);
		
		blocks = new String[]{"36-.-37","37-36-38","38-37-39:71-9","39-38-40-9","40-39-41","41-40-42","42-41-43",
				"43-42-44-10","44-43:67-45-10","45-44-46","46-45-47","47-46-48","48-47-49","49-48-50","50-49-51",
				"51-50-52","52-51-53:66-11","53-52-54-11","54-53-55","55-54-56","56-55-57","57-56-58","58-57-59",
				"59-58-60","60-59-61","61-60-62","62-61-63","63-62-64","64-63-65","65-64-66","66-65-52-11","67-68-44-10",
				"68-69-67","69-70-68","70-71-69","71-38-70-9"};
		switches = new String[]{"9-38:38,39:38,71","10-43:43,44:67,44","11-52:52,53:66,52"};
		WC[1] = new WaysideController("Red",blocks,switches, new int[]{47},null);

		blocks = new String[]{ "1-0-13-1", "2-0-1", "3-0-2", "4-0-3","5-0-4","6-0-5","7-0-6",
				"8-0-7","9-0-8","10-0-9","11-0-10","12-0-11-1","13-12-14-1","14-13-15","15-14-16","16-15-14",
				"17-16-18","18-17-19","19-18-20","20-19-21","21-20-22","22-21-23","23-22-24","24-23-25","25-24-26",
				"26-25-27","27-26-28","28-27-29-2","29-0-30-2","30-0-31","31-0-32","32-0-33","33-0-34","34-0-35",
				"35-0-36","36-0-37","37-0-38","38-0-39","39-0-40","40-0-41","41-0-42","42-0-43","43-0-44",
				"44-0-45","45-0-46","46-0-47","47-0-48","48-0-49","49-0-50","50-0-51","51-0-52","52-0-53",
				"53-0-54","54-0-55","55-0-56","56-0-57","57-0-58:151-3","58-0-59-3","59-0-60","60-0-61","61-0-62-0",
				"62-0-63-0","63-0-64", "64-0-65", "65-0-.", "134-0-135","135-0-136","136-0-137","137-0-138", 
				"138-0-139","139-0-140","140-0-141","141-0-142","142-0-143","143-0-144","144-0-145",
				"145-0-146","146-0-147","147-0-148", "148-0-149", "149-0-150", "150-0-28-2", "151-0-y-3", "152-0-63-0"};
		switches = new String[]{"1-12:1,13:12,13","2-29:28,29:28,150","3-58:57,58:57,151","0-62:61,62:152,62"};
		WC[2] = new WaysideController("Green",blocks,switches, new int[]{19},null);
		
		blocks = new String[]{"66-0-67","67-0-68","68-0-69","69-0-70","70-0-71","71-0-72","72-0-73",
				"73-0-74","74-0-75","75-0-76","76-0-77-4","77-76:101-78-4","78-77-79","79-78-80","80-79-81",
				"81-80-82","82-81-83","83-82-84","84-83-85","85-84-86-5","86-85-87-5","87-86-88","88-87-89",
				"89-88-90","90-89-91","91-90-92","92-91-93","93-92-94","94-93-95","95-94-96","96-95-97",
				"97-96-98","98-97-99","99-98-100","100-99-85-5","101-77-102-4", "102-0-103","103-0-104","104-0-105",
				"105-0-106","106-0-107","107-0-108","108-0-109","109-0-110","110-0-111","111-0-112","112-0-113","113-0-114",
				"114-0-115","115-0-116","116-0-117","117-0-118","118-0-119","119-0-120","120-0-121","121-0-122","122-0-123",
				"123-0-124","124-0-125","125-0-126", "126-0-127","127-0-128","128-0-129","129-0-130","130-0-131","131-0-132",
				"132-0-133","133-0-."};
		switches = new String[]{"4-76:76,77:77,101","5-86:85,86:100,85"};
		WC[3] = new WaysideController("Green",blocks,switches,null, null);
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
	public void suggestSpeed(double speed, int trainID, String waysideID)
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
	public void suggestAuthority(int destination, int trainID, String waysideID)
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
	
	/**
	 * Toggle a switch by entering the wayside and switch number
	 * Used by CTC Office
	 * @param switchNumber
	 * @param waysideID 
	 */
	public void toggleSwitch(int switchNumber, String waysideID)
	{
		switch(waysideID)
		{
			case "Red 1":
				WC[0].setSwitch(switchNumber);
				break;
			case "Red 2":
				WC[1].setSwitch(switchNumber);
				break;
			case "Green 1":
				WC[2].setSwitch(switchNumber);
				break;
			case "Green 2":
				WC[3].setSwitch(switchNumber);
				break;
		}
	}
	
	/**
	 * Get the current switch position by entering the wayside and switch number
	 * Used by CTC Office
	 * @param switchNumber
	 * @param waysideID 
	 */
	public String getSwitchPosition(int switchNumber, String waysideID)
	{
		switch(waysideID)
		{
			case "Red 1":
				return (WC[0].switches.get(switchNumber))[0];
			case "Red 2":
				return (WC[1].switches.get(switchNumber))[0];
			case "Green 1":
				return (WC[2].switches.get(switchNumber))[0];
			case "Green 2":
				return (WC[3].switches.get(switchNumber))[0];
		}
		return null;
	}
	
	/**
	 * Given a wayside ID and path to the PLC, the interface calls load plc for the specified wayside
	 * Used by LoadPLCUI
	 * @param path
	 * @param waysideID
	 * @return boolean that indicates whether the plc was successfully loaded
	 */
	public boolean loadPLC(String path, String waysideID)
	{
		switch(waysideID)
		{
			case "Red 1":
				return WC[0].plc.load_plc(path);
			case "Red 2":
				return WC[1].plc.load_plc(path);
			case "Green 1":
				return WC[2].plc.load_plc(path);
			case "Green 2":
				return WC[3].plc.load_plc(path);
		}
		return false;
	}
}
