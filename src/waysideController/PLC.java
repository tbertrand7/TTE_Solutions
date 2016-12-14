package waysideController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class PLC 
{
	private static ArrayList<ArrayList<String>> conditions;
	private static ArrayList<String> results;
    
	public PLC()
	{
		conditions = new ArrayList<ArrayList<String>>();
		results = new ArrayList<String>();
	}
	
	public void replaceCondition(ArrayList<ArrayList<String>> condition)
	{
		conditions = condition;
	}
	
	public void replaceResult(ArrayList<String> result)
	{
		results = result;
	}
	
	public ArrayList<ArrayList<String>> getConditions()
	{
		return conditions;
	}
	
	public ArrayList<String> getResults()
	{
		return results;
	}
	
	
	public boolean load_plc(String path)
	{
		//if there is an error while loading, I do not want to overwrite the current program
		ArrayList<ArrayList<String>> temp_conditions = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp_results = new ArrayList<String>();
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
							if(i+1 > condition.length)
							{
								throw new Exception("Did not provide block number in plc code");
							}
							conditions.add("b"+condition[i+1]);
							break;
						case "!block":
							if(i+1 > condition.length)
							{
								throw new Exception("Did not provide block in plc code");
							}
							conditions.add("!b"+condition[i+1]);
							break;
						case "switch":
							if(i+1 > condition.length)
							{
								throw new Exception("Did not provide switch block number in plc code");
							}
							conditions.add("s"+condition[i+1]+","+condition[i+2]); //switch in position 0
							i += 2;
							break;
						case "!switch":
							if(i+1 > condition.length)
							{
								throw new Exception("Did not provide switch block number in plc code");
							}
							conditions.add("!s"+condition[i+1]+","+condition[i+2]); //switch is position 1
							i += 2;
							break;
						case "AND":
							conditions.add("AND");
							break;
						case "OR":
							conditions.add("OR");
					}
				}
				temp_conditions.add(conditions);
				//System.out.println("I AM HERE HELLLLOOOOO");
				
				if(line[1].toLowerCase().contains("red"))
				{
					temp_results.add("r");
				}
				else if(line[1].toLowerCase().contains("yellow"))
				{
					temp_results.add("y");
				}
				else if(line[1].toLowerCase().contains("green"))
				{
					temp_results.add("g");
				}
				else if(line[1].toLowerCase().contains("switch"))
				{
					String[] switchInfo = line[1].trim().split(" ");
					temp_results.add("s:"+switchInfo[1]+","+switchInfo[2]);
				}
				else
				{
					throw new Exception("Code is not properly formatted.\nDo not understand result: "+line[1]);
				}
			}
			
			br.close();
			this.replaceCondition(temp_conditions);
			this.replaceResult(temp_results);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static void main(String[] args)
	{
		PLC p = new PLC();
		p.load_plc("C:\\Users\\Alisha\\git\\TTE_Solutions\\bin\\waysideController\\red1.txt");
		System.out.println(conditions);
		System.out.println(results);
	}
	
	public void run_plc(ArrayList<Integer> occupancies, Hashtable<Integer, Integer> switchPositions)
	{
		ArrayList<ArrayList<String>> conditions = this.getConditions();
		ArrayList<String> results = this.getResults();
		
		
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
						satisfied = true;		
				}
				else if(s.contains("!s"))
				{
					//if()
							satisfied = true;
				}
				else if(s.contains("b"))
				{
					if(occupancies.contains(Integer.parseInt(s.replace("b", ""))))
							satisfied = true;
				}
				else if(s.contains("s"))
				{
					if(occupancies.contains(Integer.parseInt(s.replace("b", ""))))
						satisfied = true;
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
						//lights.put("red");
						System.out.println("RESULT: turn lights RED");
						break;
					case "y":
						//lights = "yellow";
						System.out.println("RESULT: turn lights YELLOW");
						break;
					case "g":
						//lights = "green";
						System.out.println("RESULT: turn lights GREEN");
						break;
					case "!t":
						//switch_position = false;
						System.out.println("RESULT: switch is not thrown");
						break;
					case "t":
						//switch_position = true;
						System.out.println("RESULT: switch is thrown");
						break;
				}
			}
			else
				System.out.println("CONDITION is NOT SATISFIED");
			
			satisfied = false;
		}
	}
}

