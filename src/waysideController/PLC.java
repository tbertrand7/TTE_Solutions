package waysideController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class PLC 
{
	private ArrayList<ArrayList<String>> conditions;
	private ArrayList<String> results;
    
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
							conditions.add("s:"+condition[i+1]+","+condition[i+2]); //switch in position 0
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
				
				if(line[1].toLowerCase().contains("red"))
				{
					String[] lightInfo = line[1].trim().split(" ");
					temp_results.add("r"+lightInfo[1]);
				}
				else if(line[1].toLowerCase().contains("yellow"))
				{
					String[] lightInfo = line[1].trim().split(" ");
					temp_results.add("y"+lightInfo[1]);
				}
				else if(line[1].toLowerCase().contains("green"))
				{
					String[] lightInfo = line[1].trim().split(" ");
					temp_results.add("g"+lightInfo[1]);
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
		//System.out.println(conditions);
		//System.out.println(results);
	}
}

