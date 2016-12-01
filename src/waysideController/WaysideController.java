package waysideController;

//IMPORTS
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import trackModel.*;

public class WaysideController 
{
	//GLOBAL VARIABLES:
	private PLCProgram plc = new PLCProgram();
	private String line;
	
	private int[] blocks;
	private trackModel.TrackBlock[] trackBlocks;
	private Hashtable<Integer,Integer[]> controlledSwitches;
	private Hashtable<Integer, Integer> trains;
	
	public WaysideController(String line, int[] b, String[] s)
	{
		blocks = b;
		trackBlocks = new trackModel.TrackBlock[b.length];
		controlledSwitches = new Hashtable<Integer,Integer[]>();
		trains = new Hashtable<Integer, Integer>();
		
		for(String sw: s)
		{
			System.out.println(sw);
			String[] split = sw.split("-");
			System.out.println(split[0]);
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
			System.out.println(trackBlocks[i].switchBlock.id);
			
			if( trackBlocks[i].switchBlock != null)
			{
				updateLocalSwitchInfo(Integer.parseInt( trackBlocks[i].switchBlock.getID()) , trackBlocks[i].switchBlock.getPosition() );
			}
			
			if(trackBlocks[i].status == trackModel.TrackBlock.BlockStatus.OCCUPIED)
			{
				trains.put(Integer.parseInt(trackBlocks[i].occupied), trackBlocks[i].blockNumber);
			}
		}
	}
	
	public void updateLocalSwitchInfo(int switchNum, String position)
	{
		Integer[] current = controlledSwitches.get(switchNum);
		current[0] = Integer.parseInt(position);
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
		
		//should update first?
		
		
		for(int i = 0; i < path.size(); i++)
		{
			if(i == 0)
				trackBlocks[i].authority = path.size() - 1; //end at start of end block, therefore not in authority
			else if(i == 1)
				trackBlocks[i].authority = path.size() - 2; //just in case train was leaving block as authority was issued
			else
				trackBlocks[i].authority = (-1);
			
			trackBlocks[i].nextBlock = path.get(i+1);
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
	

	
	
	//---------------SAFETY--------------------
	public void load_plc(String path)
	{
		plc.clearConditions();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line[] = {" "," "};
			
			while(br.ready())
			{
				line = br.readLine().split("THEN");
				String condition[] = line[0].split(" ");
				ArrayList<String> conditions = new ArrayList<String>();
				for(int i = 0; i < condition.length; i++)
				{
					//System.out.println(condition[i]);
					switch(condition[i])
					{
						case "IF":
							break;
						case "block":
							conditions.add("b"+condition[i+1]);
							break;
						case "!block":
							conditions.add("!b"+condition[i+1]);
							break;
						case "switch":
							conditions.add("s");
							break;
						case "!switch":
							conditions.add("!s");
							break;
						case "AND":
							conditions.add("AND");
							break;
						case "OR":
							conditions.add("OR");
							
					}
				}
				plc.addCondition(conditions);
				
				if(line[1].toLowerCase().contains("red"))
				{
					plc.addResult("r");
				}
				else if(line[1].toLowerCase().contains("yellow"))
				{
					plc.addResult("y");
				}
				else if(line[1].toLowerCase().contains("green"))
				{
					plc.addResult("g");
				}
				else if(line[1].toLowerCase().contains("!throw"))
				{
					plc.addResult("!t");
				}
				else if(line[1].toLowerCase().contains("throw"))
				{
					plc.addResult("t");
				}
			}
			
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void run_plc()
	{
		/*ArrayList<ArrayList<String>> conditions = plc.getConditions();
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
					
					temp = !blocks[Character.getNumericValue(s.charAt(2))];
				}
				else if(s.contains("!s"))
				{
					temp = controlledSwitches.get(Character.getNumericValue(s.charAt(2)));
				}
				else if(s.contains("b"))
				{
					temp = blocks[Character.getNumericValue(s.charAt(1))];
				}
				else if(s.contains("s"))
				{
					temp = switch_position;
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
			
			if(satisfied)
			{
				//System.out.println("CONDITION is SATISFIED");
				switch(results.get(i))
				{
					case "r":
						lights.put("red");
						System.out.println("RESULT: turn lights RED");
						break;
					case "y":
						lights = "yellow";
						System.out.println("RESULT: turn lights YELLOW");
						break;
					case "g":
						lights = "green";
						System.out.println("RESULT: turn lights GREEN");
						break;
					case "!t":
						switch_position = false;
						System.out.println("RESULT: switch is not thrown");
						break;
					case "t":
						switch_position = true;
						System.out.println("RESULT: switch is thrown");
						break;
				}
			}
			else
				System.out.println("CONDITION is NOT SATISFIED");
			
			satisfied = false;
		}*/
	}


	
	
	public static void main(String[] args)
	{
		WaysideController wc = new WaysideController("Red",new int[]{1},new String[]{"1-0:1:2:1"});
		Integer[] i = wc.controlledSwitches.get(1);
		System.out.println(i[0]+" , "+i[1]+" , "+i[2]+" , "+i[3]);
	}
}

class Switch
{
	public int number;
	public String route1;
	public String route2;
	public int position;
	
	public Switch(int number, String route1, String route2, int position)
	{
		this.number = number;
		this.route1 = route1;
		this.route2 = route2;
		this.position = position;
	}
	
}

class PLCProgram
{
	private ArrayList<ArrayList<String>> conditions;
	private ArrayList<String> results;
    
	public PLCProgram()
	{
		conditions = new ArrayList<ArrayList<String>>();
		results = new ArrayList<String>();
	}
	
	public void addCondition(ArrayList<String> condition)
	{
		conditions.add(condition);
	}
	
	public void addResult(String result)
	{
		results.add(result);
	}
	
	public ArrayList<ArrayList<String>> getConditions()
	{
		return conditions;
	}
	
	public ArrayList<String> getResults()
	{
		return results;
	}
	
	public void clearConditions()
	{
		conditions.clear();
		results.clear();
	}
}
