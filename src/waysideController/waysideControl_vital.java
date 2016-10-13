package waysideController;
import java.io.*;
import java.util.*;

public class waysideControl_vital {
	
	public static plc_program plc;
	public static boolean blocks[] = {false, true, false};
	public static boolean switch_position = true;
	public static String lights = "green";
	

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("The current state of the track is:\nBLOCKS--");
		for(int i = 0; i < blocks.length; i++)
		{
			System.out.print("\tBLOCK "+i);
			if(blocks[i])
				System.out.println(" is OCCUPIED");
			else
				System.out.println(" is NOT OCCUPIED");
		}
		System.out.println("SWITCHES--");
		if(switch_position)
			System.out.println("\tSWITCH is THROWN");
		else
			System.out.println("\tSWITCH is NOT THROWN");
		System.out.println("LIGHTS-- "+lights);
		
		System.out.print("Enter the path to PLC program: ");
		String path = keyboard.nextLine();
		System.out.println("\nLOADING PLC PROGRAM...");
		plc = new plc_program();
		load_plc(path);
		System.out.println("CONDITION1: "+plc.getConditions().get(0));
		System.out.println("CONDITION2: "+plc.getConditions().get(1));
		System.out.println("CONDITION3: "+plc.getConditions().get(2));
		System.out.println("\n\nPress enter to continue...");
		keyboard.nextLine();
		System.out.println("\nRUNNING PLC PROGRAM...");
		
		run_plc();
		
		System.out.println("\n\nPress enter to continue...");
		keyboard.nextLine();
		System.out.println("\nThe current state of the track is:\nBLOCKS--");
		for(int i = 0; i < blocks.length; i++)
		{
			System.out.print("\tBLOCK "+i);
			if(blocks[i])
				System.out.println(" is OCCUPIED");
			else
				System.out.println(" is NOT OCCUPIED");
		}
		System.out.println("SWITCHES--");
		if(switch_position)
			System.out.println("\tSWITCH is THROWN");
		else
			System.out.println("\tSWITCH is NOT THROWN");
		System.out.println("LIGHTS-- "+lights);
		
		keyboard.close();
	}
	
	public static void load_plc(String path)
	{
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
			}
			
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void run_plc()
	{
		ArrayList<ArrayList<String>> conditions = plc.getConditions();
		ArrayList<String> results = plc.getResults();
		
		
		for(int i = 0; i < conditions.size(); i++)
		{
			boolean satisfied = false;
			ArrayList<String> current = conditions.get(i);
			System.out.println("\nTESTING THE CONDITION: "+current);
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
					temp = !switch_position;
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
						System.out.println("ARG1: "+arg1+" ARG2: "+arg2);
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
						System.out.println("ARG1: "+arg1+" ARG2: "+arg2);
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
				System.out.println("CONDITION is SATISFIED");
				switch(results.get(i))
				{
					case "r":
						lights = "red";
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
				}
			}
			else
				System.out.println("CONDITION is NOT SATISFIED");
			
			satisfied = false;
		}
	}
}

class plc_program
{
	private ArrayList<ArrayList<String>> conditions;
	private ArrayList<String> results;
    
	public plc_program()
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
}
