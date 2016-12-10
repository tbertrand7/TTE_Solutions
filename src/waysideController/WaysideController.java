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
	
	protected String line; //needed in order to fetch 
	protected int[] blocks; //used for fetching all the blocks from the track model
	protected trackModel.TrackBlock[] trackBlocks; //all the track blocks from the track model
	protected Hashtable<Integer, blockPosition> trackSetup; //used for finding the route -- shows all the connections between blocks
	protected Hashtable<Integer,String[]> switches; 
	protected Hashtable<Integer, Integer> trains;
	protected Hashtable<Integer, String> brokenRails;
	protected Hashtable<Integer, String[]> crossing;
	
	public WaysideController(String line, String[] b, String[] s, int[] c, String plcPath)
	{
		blocks = new int[b.length];
		trackBlocks = new trackModel.TrackBlock[b.length];
		switches = new Hashtable<Integer,String[]>();
		trains = new Hashtable<Integer, Integer>();
		trackSetup = new Hashtable<Integer,blockPosition>();
		brokenRails = new Hashtable<Integer,String>();
		crossing = new Hashtable<Integer, String[]>();
		
		for(int i = 0; i < b.length; i++)
		{
			String[] split = b[i].split("-");
			
			Integer currentBlock;
			int[] next = new int[2];
			int[] previous = new int[2];
			
			currentBlock = Integer.parseInt(split[0]);
			blocks[i] = currentBlock;
			
			String[] blockSplit = split[1].split(":");
			if(blockSplit.length < 2)
			{
				if(!blockSplit[0].equals(".") && !blockSplit[0].equals("y"))
					next[0] = Integer.parseInt(blockSplit[0]);
			}
			else
			{
				next[0] = Integer.parseInt(blockSplit[0]);
				next[1] = Integer.parseInt(blockSplit[1]);
			}
			
			blockSplit = split[2].split(":");
			if(blockSplit.length < 2)
			{
				if(!blockSplit[0].equals(".") && !blockSplit[0].equals("y"))
					previous[0] = Integer.parseInt(blockSplit[0]);
			}
			else
			{
				previous[0] = Integer.parseInt(blockSplit[0]);
				previous[1] = Integer.parseInt(blockSplit[1]);
			}
			blockPosition block = new blockPosition(next, previous);
			trackSetup.put(currentBlock,block);
		}
		
		if(s != null)
		{
			for(String sw: s)
			{
				String[] split = sw.split("-");
				String[] switchInfo = split[1].split(":");
				switches.put(Integer.parseInt(split[0]),new String[]{"0",switchInfo[0],switchInfo[1],switchInfo[2]});
			}
		}
		
		this.line = line;
		
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  updateLocalTrackInfo();
		  }
		}, 0, 50, TimeUnit.MILLISECONDS);
		
		if(c != null)
		{
			for(int i = 0; i < c.length; i++)
			{
				setInfrastructure(c[i],'g');
			}
		}
	}
	
	
	//------------------------------MAINTAINING UP-TO-DATE TRACK---------------------------------------
	public void updateLocalTrackInfo()
	{
		trackModel.TrackModel track = new trackModel.TrackModel();

		trackBlocks = track.getBlock(line, blocks);
			
		for(int i = 0; i < trackBlocks.length; i++)
		{
			if(!trackBlocks[i].switchBlock.id.equals("") && trackBlocks[i].infrastructure.contains("SWITCH"))
			{
				String id = trackBlocks[i].switchBlock.getID().replace("Switch ","");
				String position = trackBlocks[i].switchBlock.getPosition();
				updateLocalSwitchInfo(Integer.parseInt( id ) , position);
			}
			
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				trains.remove(trackBlocks[i].trainID);
				trains.put(trackBlocks[i].trainID, trackBlocks[i].blockNumber);
			}
			
			if(trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.OCCUPIED && trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.UNOCCUPIED)
			{
				brokenRails.remove(trackBlocks[i].blockNumber);
				brokenRails.put(trackBlocks[i].blockNumber, trackBlocks[i].status.toString());
			}
			
			if(trackBlocks[i].infrastructure.contains("RAILWAY CROSSING"))
			{
				crossing.remove(trackBlocks[i].blockNumber);
				String[] s = trackBlocks[i].infrastructure.split(":");
				if(s.length > 1)
					crossing.put(trackBlocks[i].blockNumber, new String[] {s[1],s[2]});
			}
		}
	}
	
	public void updateLocalSwitchInfo(int switchNum, String position)
	{
		String[] current = switches.get(switchNum);
		current[0] = position;
		switches.remove(switchNum);
		switches.put(switchNum, current);
	}
	
	//------------------------COMMS FROM CTC OFFICE ---------------------------
	public void suggestSpeed(double speed, int train)
	{
		int block = trains.get(train);
		int tblock = -1;
		for(int i = 0; i < blocks.length; i++)
		{
			if(blocks[i] == block)
				tblock = i;
		}
		
		if(tblock != -1)
		{
			trackModel.TrackModel track = new trackModel.TrackModel();
			if(checkSpeed(speed,trackBlocks[tblock].speedLimit))
			{
				trackBlocks[tblock].speed = speed;
				track.setBlock(trackBlocks[tblock]);
			}
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
		return new ArrayList<Integer>();
	}
	
	//------------------------COMMS TO TRACK------------------------------------
	protected void setSwitch(int switchNumber)
	{
		int switchBlock = Integer.parseInt(switches.get(switchNumber)[1]);
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
	public void setInfrastructure(int trackBlock, char lights)
	{
		String finalInfrastructure = "";
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrackBlock temp = track.getBlock(line, trackBlock);
		finalInfrastructure = temp.infrastructure;
		if(lights == 'r')
		{
			finalInfrastructure = finalInfrastructure+":r:d";
		}
		else if(lights == 'y') 
		{
			finalInfrastructure = finalInfrastructure+":y:d";
		}
		else
		{
			finalInfrastructure = finalInfrastructure+":g:u";
		}
		temp.infrastructure = finalInfrastructure;
		track.setBlock(temp);
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

class blockPosition
{
	public int[] nextBlock;
	public int[] previousBlock;
	
	public blockPosition(int[] nB, int[] pB)
	{
		nextBlock = nB;
		previousBlock = pB;
	}
}
