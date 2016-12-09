package waysideController;

//IMPORTS
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import trackModel.*;

public class WaysideController 
{
	//GLOBAL VARIABLES:
	//private PLC plc = new PLC();
	protected String line;
	protected int[] blocks;
	protected trackModel.TrackBlock[] trackBlocks;
	protected Hashtable<Integer,Integer[]> controlledSwitches;
	protected Hashtable<Integer, Integer> trains;
	
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
		}
		
		if(s != null)
		{
			for(String sw: s)
			{
				String[] split = sw.split("-");
				String[] switchInfo = split[1].split(":");
				controlledSwitches.put(Integer.parseInt(split[0]),new Integer[]{Integer.parseInt(switchInfo[0]),Integer.parseInt(switchInfo[1]),Integer.parseInt(switchInfo[2]),Integer.parseInt(switchInfo[3])});
			}
		}
		this.line = line;
		
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  updateLocalTrackInfo();
		  }
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	
	//------------------------------MAINTAINING UP-TO-DATE TRACK---------------------------------------
	public void updateLocalTrackInfo()
	{
		trackModel.TrackModel track = new trackModel.TrackModel();

		trackBlocks = track.getBlock(line, blocks);
			
		for(int i = 0; i < blocks.length; i++)
		{
			if(!trackBlocks[i].switchBlock.id.equals("") && trackBlocks[i].infrastructure.contains("SWITCH"))
			{
				String id = trackBlocks[i].switchBlock.getID().replace("Switch ","");
				String position = trackBlocks[i].switchBlock.getPosition();
				updateLocalSwitchInfo(Integer.parseInt( id ) , Integer.parseInt(position));
			}
			
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				if(trains.contains(trackBlocks[i].trainID))
				{
					trains.remove(trackBlocks[i].trainID);
				}
				trains.put(trackBlocks[i].trainID, trackBlocks[i].blockNumber);
			}
		}
	}
	
	public void updateLocalSwitchInfo(int switchNum, int position)
	{
		Integer[] current = controlledSwitches.get(switchNum);
		current[0] = position;
		controlledSwitches.remove(switchNum);
		controlledSwitches.put(switchNum, current);
	}
	
	//------------------------COMMS FROM CTC OFFICE ---------------------------
	public void suggestSpeed(double speed, int train)
	{
		updateLocalTrackInfo();
		
		int block = trains.get(train);
		int tblock=0;
		for(int i = 0; i < blocks.length;i++)
		{
			if(blocks[i] == block)
				tblock = i;
		}
		
		trackModel.TrackModel track = new trackModel.TrackModel();
		//System.out.println(speed);
		//System.out.println(trackBlocks[tblock].speedLimit);
		if(checkSpeed(speed,trackBlocks[tblock].speedLimit))
		{
			//System.out.println("here");
			trackBlocks[tblock].speed = speed;
			track.setBlock(trackBlocks[tblock]);
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
		
		updateLocalTrackInfo();
		
		int currentBlock = trains.get(train);
		ArrayList<Integer> path = calculateRoute(currentBlock, destination);
		
		for(int i = 0; i < path.size(); i++)
		{
			if(blocks[i] == currentBlock)
			{
				System.out.println(trackBlocks[i].authority);
				trackBlocks[i].authority = path.size() - 2; //end at start of end block, therefore not in authority
			}
			else if(blocks[i] == currentBlock+1)
			{
				trackBlocks[i].authority = path.size() - 3; //just in case train was leaving block as authority was issued
			}
			else
				trackBlocks[i].authority = (-1);
			
			if(i+1 < path.size())
			{
				//System.out.println(blocks[path.get(i+1)]);
				trackBlocks[i].nextBlock = blocks[path.get(i+1)];
			}
			//System.out.println(trackBlocks[i].authority);
			track.setBlock(trackBlocks[i]);
		}
	}
	
	public ArrayList<Integer> calculateRoute(int start, int end)
	{
		//TO DO
		//get the start track block
		System.out.println("START: "+start+" , END: "+end);
		ArrayList<Integer> j = new ArrayList<Integer>();
		
		for(int i = 0; i < blocks.length; i++)
		{
			if(blocks[i] >= start)
			{
				if(blocks[i] == end)
				{
					j.add(i);
					break;
				}
				j.add(i);
			}
		}
		return j;
	}
	
	//------------------------COMMS TO TRACK------------------------------------
	protected void setSwitch(int switchNumber)
	{
		int switchBlock = controlledSwitches.get(switchNumber)[1];
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrackBlock temp = track.getBlock(line, switchBlock);
		String newPosition = changeSwitchPosition(switchBlock, temp.switchBlock.position);
		temp.switchBlock.position = newPosition;
		track.setBlock(temp);
	}
	
	protected String changeSwitchPosition(int switchNum, String Position)
	{
		if(Position.equals("0"))
			return "1";
		else
			return "0";
	}
	
	//set traffic lights, cross bar and its lights
	public void setInfrastructure(int trackBlock)
	{
		String finalInfrastructure = "";
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrackBlock temp = track.getBlock(line, trackBlock);
		finalInfrastructure = temp.infrastructure;
		
	}
	
	public static void main(String[] args)
	{
		//TEST CODE
		//String[] demoBlocks = new String[]{"102-0","103-0","104-0","105-0","106-0","107-0","108-0","108-0","109-0","110-0","111-0","112-0","113-0",
		//		"114-0","115-0","116-0","117-0","118-0","119-0","120-0","121-0","122-0","123-0","124-0","125-0",
		//		"126-0","127-0","128-0","129-0","130-0","131-0","132-0","133-0","134-0","135-0","136-0","137-0",
		//		"138-0","139-0","140-0","141-0","142-0","143-0","144-0","145-0","146-0","147-0","148-0","149-0"};
		//WaysideController wc = new WaysideController("Green",demoBlocks,null);
	}
}
