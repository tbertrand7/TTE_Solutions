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
	protected PLC plc; 
	protected String line; //needed in order to fetch from track
	protected int[] blocks; //used for fetching all the blocks from the track model
	protected trackModel.TrackBlock[] trackBlocks; //all the track blocks from the track model
	protected Hashtable<Integer,Integer> blockToTrackBlock; //relationship between the track block number and it's index in the track block array
	protected Hashtable<Integer, BlockPosition> trackSetup; //used for finding the route -- shows all the connections between blocks
	protected Hashtable<Integer,String[]> switches; //switch, it's position and info
	protected Hashtable<Integer, TrainInfo> trains; // trains currently in this wayside
	protected Hashtable<Integer, String> brokenRails; //needed so that calculated routes don't include broken rails, if possible
	protected Hashtable<Integer, String[]> crossing; //railway crossings on the specified route
	protected ArrayList<Integer> occupied; //used to pass to plc blocks that are occupied
	
	public WaysideController(String line, String[] wBlocks, String[] wSwitches, int[] wCrossings, String plcPath)
	{
		blocks = new int[wBlocks.length];
		trackBlocks = new trackModel.TrackBlock[wBlocks.length];
		switches = new Hashtable<Integer,String[]>();
		trains = new Hashtable<Integer, TrainInfo>();
		trackSetup = new Hashtable<Integer,BlockPosition>();
		brokenRails = new Hashtable<Integer,String>();
		crossing = new Hashtable<Integer, String[]>();
		blockToTrackBlock = new Hashtable<Integer, Integer>();
		occupied = new ArrayList<Integer>();
		plc = new PLC();
		
		//LOAD THE PLC
		if(plcPath != null)
			plc.load_plc(plcPath);
		
		//EXTRACT THE TRACK SETUP FROM wBlocks 
		for(int i = 0; i < wBlocks.length; i++)
		{
			//Block input
			//	current block - previous block <: previous switch block> - next block <:next switch block> - switch number
			//	current block = current block
			//	previous block 
			//		-if zero --> block is uni-directional
			//		-if it is a switch arm --> previous switch block will be other block current block can connect to
			//		-if 'y' = previous block was the yard
			//		-if '.' = previous block is border with another wayside
			//	next block
			//		-if it is a switch arm --> next switch block will be other block current block can connect to
			//		-if 'y' = next block is the yard
			//		-if '.' = next block is border with another wayside
			//	switch number = the number of the switch that the block is involved in
			
			String[] split = wBlocks[i].split("-"); 
			Integer currentBlock;
			int[] next = new int[2];
			int[] previous = new int[2];
			
			currentBlock = Integer.parseInt(split[0]);
			blocks[i] = currentBlock; // create an array of the blocks --> is used to fetch the track blocks from the DB
			
			//NEXT BLOCK
			String[] blockSplit = split[2].split(":");
			if(blockSplit.length < 2) // if it is not a switch block
			{
				if(!blockSplit[0].equals(".") && !blockSplit[0].equals("y")) //not yard or border
				{
					next[0] = Integer.parseInt(blockSplit[0]);
					next[1] = -1;
				}
				else if(blockSplit[0].equals(".")) //border
				{
					next[0] = -1;
					next[1] = -1;
				}
				else //yard
				{
					next[0] = -2;
					next[1] = -2;
				}
			}
			else //switch block
			{
				next[0] = Integer.parseInt(blockSplit[0]);
				next[1] = Integer.parseInt(blockSplit[1]);
			}
			
			//PREVIOUS BLOCK
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
			BlockPosition block;
			if(split.length == 4) //block is involved in a switch
			{
				block = new BlockPosition(next, previous, Integer.parseInt(split[3])); 
			}
			else
			{
				block = new BlockPosition(next, previous, -1);
			}
			
			trackSetup.put(currentBlock,block); //track setup
			blockToTrackBlock.put(currentBlock, i);
		}
		
		
		//LOAD wSwitches
		//INPUT: switch number - block with switch infrastructure : position 0 : position 1
		//	position 0 --> block 1, block 2
		//	position 1 --> block 2, block 3
		
		if(wSwitches != null)
		{
			for(String sw: wSwitches)
			{
				String[] split = sw.split("-"); 
				String[] switchInfo = split[1].split(":");
				switches.put(Integer.parseInt(split[0]),new String[]{"0",switchInfo[0],switchInfo[1],switchInfo[2]});
			}
		}
		
		if(wCrossings != null)
		{
			for(int i = 0; i < wCrossings.length; i++)
			{
				crossing.put(wCrossings[i], new String[]{"g","u"}); 
			}
		}
		
		//SET WHETHER LINE IS RED OR GREEN
		this.line = line;
		//UPDATE INFO
		updateLocalTrackInfo();
		if(plcPath != null)run_plc(occupied, switches);
		//run_plc(occupied, switches);
		//start a thread to make sure the track data is kept up to date
		//runs every .90 seconds real-time
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  run_plc(occupied, switches);
			  updateLocalTrackInfo();
		  }
		}, 0, 75, TimeUnit.MILLISECONDS);
	}
	
	
	//------------------------------MAINTAINING UP-TO-DATE TRACK---------------------------------------
	public synchronized void updateLocalTrackInfo()
	{
		trackModel.TrackModel track = new trackModel.TrackModel();
		trackBlocks = track.getBlock(line, blocks); //get the blocks controlled by this wayside
		occupied.clear();
		for(int i = 0; i < trackBlocks.length; i++)
		{
			//update switch positions
			if(!trackBlocks[i].switchBlock.id.equals("") && trackBlocks[i].infrastructure.contains("SWITCH"))
			{
				//System.out.println("Block that switch is on "+trackBlocks[i].blockNumber);
				String id = trackBlocks[i].switchBlock.getID().replace("Switch ","");
				String position = trackBlocks[i].switchBlock.getPosition();
				//System.out.println("POS:"+position+"ID:"+id);
				updateLocalSwitchInfo(Integer.parseInt( id ) , position);
			}
			
			//update trains location (block occupancy)
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				occupied.add(trackBlocks[i].blockNumber);
				TrainInfo train = trains.get(trackBlocks[i].trainID);
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
					trains.put(trackBlocks[i].trainID, new TrainInfo(trackBlocks[i].blockNumber));
					train = trains.get(trackBlocks[i].trainID);
				}
				
				//SET THE NEXT BLOCK FIELD --> So the train knows where to go
				if(trackSetup.get(train.currentBlock).switchNum == -1)
				{
					trackBlocks[i].nextBlock = findNextBlock(train.direction, trackSetup.get(train.currentBlock), null);
				}
				else
				{
					trackBlocks[i].nextBlock = findNextBlock(train.direction, trackSetup.get(train.currentBlock), switches.get(trackSetup.get(train.currentBlock).switchNum));
				}
				
				//If the next block is not -1 then set the next block
				if(trackBlocks[i].nextBlock != -1)
				{
					track.setBlock(trackBlocks[i]);
				}
				else
				{
					trains.remove(trackBlocks[i].trainID);
				}
			}
			
			//update broken rail info
			if(trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.OCCUPIED && trackBlocks[i].status != trackModel.TrackBlock.BlockStatus.UNOCCUPIED)
			{
				brokenRails.put(trackBlocks[i].blockNumber, trackBlocks[i].status.toString());
			}
		}
	}
	
	//change the direction of the train 
	public synchronized void changeDirection(int previous, int current, int direction, int trainID)
	{
		TrainInfo train;
		int next = findNextBlock(direction, trackSetup.get(current), switches.get(trackSetup.get(current).switchNum));
		if(next == previous || next == -2)
		{
			train = trains.get(trainID);
			if(train.direction == 1)
				train.direction = 0;
			else
				train.direction = 1;
			trains.put(trainID, train);
		}
	}
	
	//determines what the next block should be based on the train's direction and the current block information
	public synchronized int findNextBlock(int direction, BlockPosition current, String[] switchInformation)
	{
		if(direction == 0)
		{
			if(current.nextBlock[1] == -1)
			{
				return current.nextBlock[0];
			}
			else
			{
				if(switchInformation[0].equals("0"))
				{
					return current.nextBlock[0];
				}
				else
				{
					return current.nextBlock[1];
				}
			}
		}
		else
		{
			if(current.previousBlock[1] == -1)
			{
				return current.previousBlock[0];
			}
			else
			{
				if(switchInformation[0].equals("0"))
				{
					return current.previousBlock[0];
				}
				else
				{
					return current.previousBlock[1];
				}
			}
		}
	}
	
	/**
	 * Updates the positions of the switches I have stored locally
	 * If the switch position has changed then the trains' routes need to be recalculated
	 * @param switchNum
	 * @param position
	 */
	public synchronized void updateLocalSwitchInfo(int switchNum, String position)
	{
		String[] current = switches.get(switchNum);
		if(!current[0].equals(position)) //switch position changed
		{
			current[0] = position; //update position
			switches.put(switchNum, current);
			calculateAllRoutes(); //recalculate the routes for the trains
		}
	}
	
	//------------------------COMMS FROM CTC OFFICE ---------------------------
	public synchronized void suggestSpeed(double speed, int train)
	{
		int block = trains.get(train).currentBlock;
		int tblock = blockToTrackBlock.get(block);
		
		if(tblock != -1)
		{
			trackModel.TrackModel track = new trackModel.TrackModel();
			//trackBlocks[tblock] = track.getBlock(line, tblock);
			if(checkSpeed(speed,trackBlocks[tblock].speedLimit)) //check to make sure speed < speed limit of the block
			{
				trackBlocks[tblock].speed = speed;
				track.setBlock(trackBlocks[tblock]);
			}
		}
	}
	
	//checks that the speed is less than limit 
	public boolean checkSpeed(double speed, double speedLimit)
	{
		if(speed <= speedLimit)
			return true;
		else
			return false;
	}

	public synchronized void suggestAuthority(int destination, int train)
	{
		//updateLocalTrackInfo(); //make sure info is up to date
		
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrainInfo t = trains.get(train);
		//System.out.println(t.direction);
		if(t != null) //if I can't find the train then I cannot update it
		{
			int currentBlock = t.currentBlock;
			ArrayList<Integer> path = calculateRoute(currentBlock, destination, t.direction); //calculate route of train
			System.out.println(path);
			if(path != null)
			{
				boolean notBroken = true;
				for(Integer i: path)
				{
					//if the path contains a broken block, it is not safe to travel
					if(brokenRails.contains(i))
						notBroken = false;
				}
			
				if(notBroken)
				{
					//trackBlocks[blockToTrackBlock.get(currentBlock)] = track.getBlock(line, blockToTrackBlock.get(currentBlock));
					trackBlocks[blockToTrackBlock.get(currentBlock)].authority = path.size()-1; //if path is size 1 then it is the current block, authority is zero
					trackBlocks[blockToTrackBlock.get(currentBlock)].destination = destination;
					track.setBlock(trackBlocks[blockToTrackBlock.get(currentBlock)]); //set authority of block
				}
			}
		}
	}
	
	/**
	 * Calculates the route from the start point to the end point based on the current direction
	 * @param start
	 * @param end
	 * @param currentDirection
	 * @return ArrayList<Integer> the path
	 */
	public synchronized ArrayList<Integer> calculateRoute(int start, int end, int currentDirection)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		boolean routing = true;
		int currentBlock = start;
		int count = 0;
		
		while(routing && count < 200) //just want to make sure it isn't stuck in while loop forever
		{
			BlockPosition bp = trackSetup.get(currentBlock);
			
			int[] nextBlock;
			if(currentDirection == 1)
			{
				nextBlock = bp.previousBlock;
			}
			else
			{
				nextBlock = bp.nextBlock;
			}
			
			//System.out.println(nextBlock[0]);
			
			if(currentBlock == end)//if the destination is found
			{
				path.add(currentBlock);
				return path;
			}
			else if(path.size() > 0 && (nextBlock[0] == path.get(path.size()-1) || nextBlock[1] == path.get(path.size()-1))) //change the direction of the train
			{
				currentDirection = 0;
			}
			else if(nextBlock[0] == -2 && currentBlock == end)
			{
				path.add(currentBlock);
				return path; //right now we can't send trains to the yard
			}
			else if(nextBlock[0] == -2 && currentBlock != end)
			{
				path.add(currentBlock);
				return path; //right now we can't send trains to the yard
			}
			else if(bp.switchNum == -1 || nextBlock[0] == -1 || (bp.switchNum != trackSetup.get(nextBlock[0]).switchNum)) //not a switch block
			{
				if(nextBlock[0] == -1) //border block or yard
				{
					path.add(currentBlock);
					return path;
				}
				else
				{
					if(currentBlock != end)
					{
						path.add(currentBlock);
						currentBlock = nextBlock[0];
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
					if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+nextBlock[0])) //switch is connected to current block
					{
						path.add(currentBlock);
						currentBlock = nextBlock[0];
					}
					else if(switchInfo[2].contains(""+currentBlock) && switchInfo[2].contains(""+nextBlock[0]))
					{
						path.add(currentBlock);
						currentBlock = nextBlock[0];
					}
					else //switch is not connected, safe to stop at switch
					{
						path.add(currentBlock);
						return path;
					}
				}
				else //switch position 1
				{
					if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+nextBlock[1])) //switch is connected to current block
					{
						path.add(currentBlock);
						currentBlock = nextBlock[1];
					}
					else if(switchInfo[3].contains(""+currentBlock) && switchInfo[3].contains(""+nextBlock[0]))
					{
						path.add(currentBlock);
						currentBlock = nextBlock[0];
					}
					else //switch is not connected, safe to stop at switch
					{
						path.add(currentBlock);
						return path;
					}
				}
			}
			count++;
		}
		
		if(count == 200) //if it hit my check for looping forever, then it is not a valid path
		{
			return null;
		}
		
		return path;
		
	}
	
	//Need to recalculate routes when the switch position changes
	public synchronized void calculateAllRoutes()
	{
		for(Integer i: trains.keySet())
		{
			this.suggestAuthority(trains.get(i).destination, i);
		}
	}
	
	//------------------------COMMS TO TRACK------------------------------------
	protected synchronized void setSwitch(int switchNumber)
	{
		int switchBlock = Integer.parseInt(switches.get(switchNumber)[1]);
		trackModel.TrackModel track = new trackModel.TrackModel();
		TrackBlock temp = track.getBlock(line, switchBlock);
		String newPosition = changeSwitchPosition(temp.switchBlock.position);
		//temp.switchBlock.position = newPosition;
		temp.switchBlock.setPosition(newPosition);
		//System.out.println("Setting switch: "+temp.switchBlock.position+"Switch block "+switches.get(switchNumber)[1]);
		track.setBlock(temp);
		//System.out.println("Updated "+track.getBlock(line, temp.blockNumber).switchBlock.position);
	}
	
	protected synchronized String changeSwitchPosition(String Position)
	{
		if(Position.equals("0"))
			return "1";
		else
			return "0";
	}
	
	//set traffic lights, cross bar and its lights
	public synchronized void setInfrastructure(int trackBlock, char lights)
	{
		String[] info = crossing.get(trackBlock);
		if(info != null)
		{
			if(lights == 'r') //if the lights are red
			{
				info[0] = "r";
				info[1] = "d";
			}
			else if(lights == 'y') //if the lights are yellow
			{
				info[0] = "y";
				info[1] = "d";
			}
			else //if the lights are green
			{
				info[0] = "g";
				info[1] = "u";
			}
			crossing.put(trackBlock, info);
		}
	}
	
	public synchronized void run_plc(ArrayList<Integer> occupancies, Hashtable<Integer, String[]> switchPositions)
	{
		ArrayList<ArrayList<String>> conditions = plc.getConditions();
		ArrayList<String> results = plc.getResults();
		
		for(int i = 0; i < conditions.size(); i++)
		{
			boolean satisfied = false;
			ArrayList<String> current = conditions.get(i);

			Boolean temp = false, arg1 = false, arg2 = false; 
			String operator = "";
			for(int j = 0; j < current.size(); j++)
			{
				String s = current.get(j);
				
				if(s.contains("!b"))
				{
					if(!occupancies.contains(Integer.parseInt(s.replace("!b", ""))))
						temp = true;		
				}
				else if(s.contains("b"))
				{
					if(occupancies.contains(Integer.parseInt(s.replace("b", ""))))
						temp = true;
				}
				else if(s.contains("s"))
				{
					String[] con = s.split(":");
					String[] switchInfo = con[1].split(",");
					String[] info = switchPositions.get(Integer.parseInt(switchInfo[0]));
					if(info[0].equals(switchInfo[1]))
						temp = true;
				}
				else
				{
					operator = s;
				}
					
				if(j <= 2)
				{
					if(j % 3 == 2)
					{
						arg2 = temp;
						//System.out.println("ARG1: "+arg1+" ARG2: "+arg2);
						switch(operator)
						{
							case "AND":
								if(arg1 && arg2)
									satisfied = true;
								else
									satisfied = false;
								break;
							case "OR":
								if(arg1 || arg2)
									satisfied = true;
								else
									satisfied = false;
								break;
						}
					}
					if(j % 3 == 0)
						arg1 = temp;
				}
				else
				{
					arg1 = satisfied;
					if(j % 2 == 0)
					{
						arg2 = temp;
						//System.out.println("ARG1: "+arg1+" ARG2: "+arg2);
						switch(operator)
						{
							case "AND":
								if(arg1 && arg2)
									satisfied = true;
								else
									satisfied = false;
								break;
							case "OR":
								if(arg1 || arg2)
									satisfied = true;
								else
									satisfied = false;
								break;
						}
					}
				}
			}
			
			if(satisfied) //Result of boolean operations is true
			{
				String result = results.get(i);
				if(result.contains("r")) //set block lights to red
				{
					result = result.replace("r","");
					this.setInfrastructure(Integer.parseInt(result), 'r');
				}
				if(result.contains("g")) //set blocks lights to green
				{
					result = result.replace("g","");
					this.setInfrastructure(Integer.parseInt(result), 'g');
				}
				if(result.contains("y")) //set block lights to yellow
				{
					result = result.replace("y","");
					this.setInfrastructure(Integer.parseInt(result), 'y');
				}
				if(result.contains("s"))
				{
					//format of result --> s:<switch number>,<position>
					String[] con = result.split(":");
					String[] switchInfo = con[1].split(",");
					String[] info = switchPositions.get(Integer.parseInt(switchInfo[0]));
					if(!switchInfo[1].equals(info[0]))
					{
						//If switch is not in that position already, flip switch
						this.setSwitch(Integer.parseInt(switchInfo[0])); 
					}
				}
			}
			else
				//System.out.println("CONDITION is NOT SATISFIED");
			
			satisfied = false;
		}
	}
	
	public static void main(String[] args)
	{
		String[] blocks = new String[]{"1-16-2-6","2-1-3","3-2-4","4-3-5","5-4-6","6-5-7","7-6-8","8-7-9","9-8-10:77-12","10-9-11-12","11-10-12","12-11-13",
				"13-12-14","14-13-15","15-14-16-6","16-15:1-17-6","17-16-18","18-17-19","19-18-20","20-19-21","21-20-22","22-21-23","23-22-24","24-23-25",
				"25-24-26","26-25-27","27-26-28:76-7","28-27-29-7","29-28-30","30-29-31","31-30-32","32-31-33-8","33-32:72-34-8","34-33-35","35-34-.", "72-73-33-8",
				"73-74-72","74-75-73","75-76-74","76-27-75-7","77-9-y-12"};
		String[] cswitches = new String[]{"6-15:15,16:1,16","12-9:9,10:9,77","7-27:27,28:27,76","8-32:32,33:72,33"};
		WaysideController WC = new WaysideController("Red",blocks,cswitches, null,"C:\\Users\\Alisha\\git\\TTE_Solutions\\bin\\waysideController\\red1.txt");
		System.out.println("Here");
		//WC.run_plc(occupied, switches);
		//System.out.println(WC.calculateRoute(2, 6, 0));
		//System.out.println(WC.calculateRoute(1,20, 1));
		
	}
}

class BlockPosition
{
	public int[] nextBlock;
	public int[] previousBlock;
	public int switchNum;
	
	public BlockPosition(int[] nB, int[] pB, int s)
	{
		nextBlock = nB;
		previousBlock = pB;
		switchNum = s;
	}
}

class TrainInfo
{
	public int currentBlock; //where train currently is
	public int destination; //destination of train
	public int direction; //0 = go to next block, 1 = go to previous block
	
	public TrainInfo(int currentBlock)
	{
		this.currentBlock = currentBlock;
		destination = -1;
		direction = 0; 
	}
}
