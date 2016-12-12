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
	protected Hashtable<Integer, trainInfo> trains;
	protected Hashtable<Integer, String> brokenRails; //needed so that calculated routes don't include broken rails, if possible
	protected Hashtable<Integer, String[]> crossing; //railway crossings on the specified route
	
	public WaysideController(String line, String[] wBlocks, String[] wSwitches, int[] wCrossings, String plcPath)
	{
		blocks = new int[wBlocks.length];
		trackBlocks = new trackModel.TrackBlock[wBlocks.length];
		switches = new Hashtable<Integer,String[]>();
		trains = new Hashtable<Integer, trainInfo>();
		trackSetup = new Hashtable<Integer,blockPosition>();
		brokenRails = new Hashtable<Integer,String>();
		crossing = new Hashtable<Integer, String[]>();
		
		for(int i = 0; i < wBlocks.length; i++)
		{
			String[] split = wBlocks[i].split("-");
			
			Integer currentBlock;
			int[] next = new int[2];
			int[] previous = new int[2];
			
			currentBlock = Integer.parseInt(split[0]);
			blocks[i] = currentBlock;
			
			String[] blockSplit = split[2].split(":");
			if(blockSplit.length < 2)
			{
				if(!blockSplit[0].equals(".") && !blockSplit[0].equals("y"))
				{
					next[0] = Integer.parseInt(blockSplit[0]);
					next[1] = -1;
				}
				else if(blockSplit[0].equals("."))
				{
					next[0] = -1;
					next[1] = -1;
				}
				else
				{
					//System.out.println("here");
					next[0] = -2;
					next[1] = -2;
				}
			}
			else
			{
				next[0] = Integer.parseInt(blockSplit[0]);
				next[1] = Integer.parseInt(blockSplit[1]);
			}
			
			blockSplit = split[1].split(":");
			if(blockSplit.length < 2)
			{
				if(!blockSplit[0].equals(".") && !blockSplit[0].equals("y"))
				{
					previous[0] = Integer.parseInt(blockSplit[0]);
					previous[1] = -1;
				}
				else if(blockSplit[0].equals(".")) //border block
				{
					previous[0] = -1;
					previous[1] = -1;
				}
				else //yard
				{
					previous[0] = -2;
					previous[1] = -2;
				}
			}
			else
			{
				previous[0] = Integer.parseInt(blockSplit[0]);
				previous[1] = Integer.parseInt(blockSplit[1]);
			}
			
			//setting up the track connections
			blockPosition block;
			if(split.length == 4)
			{
				block = new blockPosition(next, previous, Integer.parseInt(split[3]));
			}
			else
			{
				block = new blockPosition(next, previous, -1);
			}
			
			trackSetup.put(currentBlock,block);
		}
		
		if(wSwitches != null)
		{
			for(String sw: wSwitches)
			{
				String[] split = sw.split("-");
				String[] switchInfo = split[1].split(":");
				switches.put(Integer.parseInt(split[0]),new String[]{"0",switchInfo[0],switchInfo[1],switchInfo[2]});
			}
		}
		
		this.line = line;
		updateLocalTrackInfo();
		
		//start a thread to make sure the track data is kept up to date
		//runs every .75 seconds real-time
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  updateLocalTrackInfo();
		  }
		}, 0, 75, TimeUnit.MILLISECONDS);
		
		if(wCrossings != null)
		{
			for(int i = 0; i < wCrossings.length; i++)
			{
				setInfrastructure(wCrossings[i],'g');
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
			//update switch positions
			if(!trackBlocks[i].switchBlock.id.equals("") && trackBlocks[i].infrastructure.contains("SWITCH"))
			{
				String id = trackBlocks[i].switchBlock.getID().replace("Switch ","");
				String position = trackBlocks[i].switchBlock.getPosition();
				updateLocalSwitchInfo(Integer.parseInt( id ) , position);
			}
			
			//update trains location (block occupancy)
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				trainInfo train = trains.get(trackBlocks[i].trainID);
				if(train != null)
				{
					if(train.currentBlock != trackBlocks[i].blockNumber)
					{
						train.currentBlock = trackBlocks[i].blockNumber;
						trains.put(trackBlocks[i].trainID, train);
					}
				}
				else
				{
					trains.put(trackBlocks[i].trainID, new trainInfo(trackBlocks[i].blockNumber));
				}
			}
			
			//update broken rail info
			if(trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.OCCUPIED && trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.UNOCCUPIED)
			{
				brokenRails.remove(trackBlocks[i].blockNumber);
				brokenRails.put(trackBlocks[i].blockNumber, trackBlocks[i].status.toString());
			}
			
			//update crossing info
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
		/**
		 * If the switch position changed
		 * 	-must recalculate the routes for the trains
		 * */
		if(!current[0].equals(position))
		{
			current[0] = position;
			switches.remove(switchNum);
			switches.put(switchNum, current);
			calculateAllRoutes();
		}
	}
	
	//------------------------COMMS FROM CTC OFFICE ---------------------------
	public void suggestSpeed(double speed, int train)
	{
		int block = trains.get(train).currentBlock;
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
		
		trainInfo t = trains.get(train);
		int currentBlock = t.currentBlock;
		ArrayList<Integer> path = calculateRoute(currentBlock, destination, t.direction);
		if(path != null)
		{
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
	}
	
	public ArrayList<Integer> calculateRoute(int start, int end, int currentDirection)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		boolean routing = true;
		int currentBlock = start;
		int count = 0;
		
		while(routing && count < 200) //just want to make sure it isn't stuck in while loop forever
		{
			blockPosition bp = trackSetup.get(currentBlock);
			if(currentDirection == 1) // the next block the train is going to is the previous block in the track setup
			{
				if(path.size() > 0 && (bp.previousBlock[0] == path.get(path.size()-1) || bp.previousBlock[1] == path.get(path.size()-1))) //change the direction
				{
					currentDirection = 0;
				}
				else if(bp.previousBlock[0] == -2 && currentBlock == end)
				{
					path.add(currentBlock);
					return path; //right now we can't send trains to the yard
				}
				else if(bp.previousBlock[0] == -2 && currentBlock != end)
				{
					return path;
				}
				else if(bp.switchNum == -1 || bp.previousBlock[0] == -1 || (bp.switchNum != trackSetup.get(bp.previousBlock[0]).switchNum))
				{
					if(bp.previousBlock[0] == -1) //border block or yard
					{
						path.add(currentBlock);
						return path;
					}
					else
					{
						if(currentBlock != end)
						{
							path.add(currentBlock);
							currentBlock = bp.previousBlock[0];
						}
						else
						{
							path.add(currentBlock);
							return path;
						}
					}
				}
				else //switch block
				{
					String[] switchInfo = switches.get(bp.switchNum);
					if(switchInfo[0].equals("0")) //switch position 0
					{
						if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+bp.previousBlock[0])) //switch is connected to current block
						{
							path.add(currentBlock);
							currentBlock = bp.previousBlock[0];
						}
						else if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+bp.previousBlock[0]))
						{
							path.add(currentBlock);
							currentBlock = bp.previousBlock[0];
						}
						else //switch is not connected, safe to stop at switch
						{
							path.add(currentBlock);
							return path;
						}
					}
					else //switch position 1
					{
						if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+bp.previousBlock[1])) //switch is connected to current block
						{
							path.add(currentBlock);
							currentBlock = bp.previousBlock[1];
						}
						else if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+bp.previousBlock[0]))
						{
							path.add(currentBlock);
							currentBlock = bp.previousBlock[0];
						}
						else //switch is not connected, safe to stop at switch
						{
							path.add(currentBlock);
							return path;
						}
					}
				}
			}
			else //direction = next block
			{
				if(path.size() > 0 && (bp.nextBlock[0] == path.get(path.size()-1) || bp.nextBlock[1] == path.get(path.size()-1))) //change the direction
				{
					currentDirection = 1;
				}
				if(bp.nextBlock[0] == -2 && currentBlock == end)
				{
					path.add(currentBlock);
					return path; //right now we can't send trains to the yard
				}
				else if(bp.nextBlock[0] == -2 && currentBlock != end)
				{
					return path;
				}
				else if(bp.switchNum == -1 || bp.nextBlock[0] == -1 || (bp.switchNum != trackSetup.get(bp.nextBlock[0]).switchNum))
				{
					if(bp.nextBlock[0] == -1) //border block or yard
					{
						path.add(currentBlock);
						return path;
					}
					else
					{
						if(currentBlock != end)
						{
							path.add(currentBlock);
							currentBlock = bp.nextBlock[0];
						}
						else
						{
							path.add(currentBlock);
							return path;
						}
					}
				}
				else //switch block
				{
					String[] switchInfo = switches.get(bp.switchNum);
					if(switchInfo[0].equals("0")) //switch position 0
					{
						if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+bp.nextBlock[0])) //switch is connected to current block
						{
							path.add(currentBlock);
							currentBlock = bp.nextBlock[0];
						}
						else if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+bp.nextBlock[0]))
						{
							path.add(currentBlock);
							currentBlock = bp.nextBlock[0];
						}
						else //switch is not connected, safe to stop at switch
						{
							path.add(currentBlock);
							return path;
						}
					}
					else //switch position 1
					{
						if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+bp.nextBlock[1])) //switch is connected to current block
						{
							path.add(currentBlock);
							currentBlock = bp.nextBlock[1];
						}
						else if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+bp.nextBlock[0]))
						{
							path.add(currentBlock);
							currentBlock = bp.nextBlock[0];
						}
						else //switch is not connected, safe to stop at switch
						{
							path.add(currentBlock);
							return path;
						}
					}
				}
			}
			count++;
		}
		
		if(count == 200)
		{
			return null;
		}
		
		return path;
	}
	
	public void calculateAllRoutes()
	{
		//TO DO: recalculate the paths
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
		if(lights == 'r') //if the lights are red
		{
			finalInfrastructure = finalInfrastructure+":r:d";
		}
		else if(lights == 'y') //if the lights are yellow
		{
			finalInfrastructure = finalInfrastructure+":y:d";
		}
		else //if the lights are green
		{
			finalInfrastructure = finalInfrastructure+":g:u";
		}
		temp.infrastructure = finalInfrastructure;
		track.setBlock(temp);
	}
	
	public static void main(String[] args)
	{
		String[] blocks = new String[]{"1-16-2-6","2-1-3","3-2-4","4-3-5","5-4-6","6-5-7","7-6-8","8-7-9","9-8-10:77-12","10-9-11-12","11-10-12","12-11-13",
				"13-12-14","14-13-15","15-14-16-6","16-15:1-17-6","17-16-18","18-17-19","19-18-20","20-19-21","21-20-22","22-21-23","23-22-24","24-23-25",
				"25-24-26","26-25-27","27-26-28:76-7","28-27-29-7","29-28-30","30-29-31","31-30-32","32-31-33-8","33-32:72-34-8","34-33-35","35-34-.", "72-73-33-8",
				"73-74-72","74-75-73","75-76-74","76-27-75-7","77-9-y-12"};
		String[] switches = new String[]{"6-15:15,16:1,16","12-9:9,10:9,77","7-27:27,28:27,76","8-32:32,33:72,33"};
		WaysideController WC = new WaysideController("Red",blocks,switches, null,"red1.txt");
		//System.out.println(WC.calculateRoute(2, 6, 0));
		System.out.println(WC.calculateRoute(1,20, 1));
		
	}
}

class blockPosition
{
	public int[] nextBlock;
	public int[] previousBlock;
	public int switchNum;
	
	public blockPosition(int[] nB, int[] pB, int s)
	{
		nextBlock = nB;
		previousBlock = pB;
		switchNum = s;
	}
}

class trainInfo
{
	public int currentBlock;
	public int destination;
	public int direction; //0 = go to next block, 1 = go to previous block
	
	public trainInfo(int currentBlock)
	{
		this.currentBlock = currentBlock;
		destination = -1;
		direction = 0; 
	}
}
