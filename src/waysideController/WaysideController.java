package waysideController;

//IMPORTS
import java.util.*;
import trackModel.*;

public class WaysideController 
{
	//GLOBAL VARIABLES:
	//private PLC plc = new PLC();
	private String line;
	
	private int[] blocks;
	private String[] direction;
	private trackModel.TrackBlock[] trackBlocks;
	private Hashtable<Integer,Integer[]> controlledSwitches;
	private Hashtable<Integer, Integer> trains;
	
	public WaysideController(String line, String[] b, String[] s)
	{
		blocks = new int[b.length];
		trackBlocks = new trackModel.TrackBlock[b.length];
		controlledSwitches = new Hashtable<Integer,Integer[]>();
		trains = new Hashtable<Integer, Integer>();
		
		for(int i = 0; i < b.length; i++)
		{
			String[] split = b[i].split("-");
			blocks[i] = Integer.parseInt(split[0]);
			direction[i] = split[1];
		}
		
		for(String sw: s)
		{
			String[] split = sw.split("-");
			String[] switchInfo = split[1].split(":");
			
			controlledSwitches.put(Integer.parseInt(split[0]),new Integer[]{Integer.parseInt(switchInfo[0]),Integer.parseInt(switchInfo[1]),Integer.parseInt(switchInfo[2]),Integer.parseInt(switchInfo[3])});
		}
		this.line = line;
		
		updateLocalTrackInfo();
	}
	
	
	//------------------------------MAINTAINING UP-TO-DATE TRACK---------------------------------------
	private void updateLocalTrackInfo()
	{
		trackModel.TrackModel track = new trackModel.TrackModel();
		for(int i = 0; i < blocks.length; i++)
		{
			trackBlocks[i] = track.getBlock(line, blocks[i]);
			
			if( trackBlocks[i].switchBlock != null)
			{
				String id = trackBlocks[i].switchBlock.getID().substring(trackBlocks[i].switchBlock.getID().length()-1);
				String position = trackBlocks[i].switchBlock.getPosition();
				updateLocalSwitchInfo(Integer.parseInt( id ) , Integer.parseInt(position));
			}
			
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				trains.put(Integer.parseInt(trackBlocks[i].occupied), trackBlocks[i].blockNumber);
			}
		}
	}
	
	public void updateLocalSwitchInfo(int switchNum, int position)
	{
		Integer[] current = controlledSwitches.get(switchNum);
		current[0] = position;
		controlledSwitches.put(switchNum, current);
	}
	
	//------------------------COMMS FROM CTC OFFICE ---------------------------
	public void suggestSpeed(double speed, int train)
	{
		int block = trains.get(train);
		trackModel.TrackModel track = new trackModel.TrackModel();
		
		if(checkSpeed(speed,trackBlocks[block].speedLimit))
		{
			track.setBlock(trackBlocks[block]);
		}
	}
	
	public boolean checkSpeed(double speed, double speedLimit)
	{
		if(speed <= speedLimit)
			return true;
		else
			return false;
	}

	public void suggestAuthority(int destination, int train)
	{
		trackModel.TrackModel track = new trackModel.TrackModel();
		int currentBlock = trains.get(train);
		ArrayList<Integer> path = calculateRoute(currentBlock, destination);
		
		for(int i = 0; i < path.size(); i++)
		{
			if(i == 0)
				trackBlocks[i].authority = path.size() - 1; //end at start of end block, therefore not in authority
			else if(i == 1)
				trackBlocks[i].authority = path.size() - 2; //just in case train was leaving block as authority was issued
			else
				trackBlocks[i].authority = (-1);
			
			trackBlocks[i].nextBlock = path.get(i+1);
			track.setBlock(trackBlocks[i]);
		}
	}
	
	public ArrayList<Integer> calculateRoute(int start, int end)
	{
		//TO DO
		//get the start track block
		//search forwards
		
		return null;
	}
	
	//------------------------COMMS TO TRACK------------------------------------
	public void setSwitch(int switchBlock, String position)
	{
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrackBlock temp = track.getBlock(line, switchBlock);
		String newPosition = changeSwitchPosition(switchBlock, temp.switchBlock.position);
		trackBlocks[switchBlock].switchBlock.position = newPosition;
		track.setBlock(trackBlocks[switchBlock]);
	}
	
	public String changeSwitchPosition(int switchNum, String Position)
	{
		if(Position.equals("0"))
			return "1";
		else
			return "0";
	}
	
	//set traffic lights, cross bar and its lights
	public void setInfrastructure()
	{
		//TO DO
	}
	
	public static void main(String[] args)
	{
		//TEST CODE
		/*WaysideController wc = new WaysideController("Red",new int[]{1},new String[]{"6-0:1:2:1"});
		Integer[] i = wc.controlledSwitches.get(6);
		System.out.println(i[0]+" , "+i[1]+" , "+i[2]+" , "+i[3]);*/
	}
}
