package trackModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TrackModel {
	
	//Frame and DB Connection for displaying errors and running queries 
	final JFrame parent = new JFrame();
	TrackDBInteraction db = null;

	//********************************************************************
	//Method calls TrackDBInteraction to return an array of blocks
	//The input array of integers will determine the output of trackBlocks
	//********************************************************************
	public TrackBlock[] getBlock(String line, int[] blocks){
		
		TrackBlock theBlock [] = null;
		
		try {
			db = new TrackDBInteraction();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			theBlock = db.getBlocks(line, blocks);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return theBlock;
	}

	//********************************************************************
	//Method calls TrackDBInteraction to return an array of blocks
	//The input string of "Red" or "Green" will determine line information
	//********************************************************************
	public TrackBlock[] getBlock(String line)
	{
		TrackBlock theBlock [] = null;
		
		try {
			db = new TrackDBInteraction();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			theBlock = db.getLine(line);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return theBlock;
	}

	//********************************************************************
	//Method calls TrackDBInteraction to return a single trackBlock
	//The input of line and block number will determine the returned block
	//********************************************************************
	public TrackBlock getBlock(String line, int blockNumber)
	{
		TrackBlock theBlock = null;
		
		try {
			db = new TrackDBInteraction();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			theBlock = db.getSection(line,blockNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return theBlock;
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to set a block in the database
	//The boolean that is returns validates the data was added correctly
	//********************************************************************
	public boolean setBlock(TrackBlock theBlock)
{
		
		boolean result = false;
		
		try {
			db = new TrackDBInteraction();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			result = db.setSection(theBlock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to set a block in the database
	//The boolean that is returns validates the data was added correctly
	//********************************************************************
	public boolean setBlockPassive(TrackBlock theBlock)
{
		
		boolean result = false;
		
		try {
			db = new TrackDBInteraction();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			result = db.setSectionPassive(theBlock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//********************************************************************
	//Method calls TrackDBInteraction to break a random track block
	//********************************************************************
	public boolean breakRail()
	{

		String[] lines = { "Red", "Green" };
		boolean sucess = true;
		Random rand = new Random();
		int n = rand.nextInt(77) + 1;
		int i = rand.nextInt(2);
		String breakRailConfirm = JOptionPane.showInputDialog(parent,
				"Are you sure you want break block: " + lines[i] + " " + n + "? (y/n)", null);

		if (breakRailConfirm.equalsIgnoreCase("y")) {
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e) {
				e.printStackTrace();
			}
			try {
				sucess = db.breakSection(lines[i], n);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!sucess) {
			JOptionPane.showMessageDialog(parent, "Block was broken!");
			return true;
		} else {
			JOptionPane.showMessageDialog(parent, "Block was not broken!");
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to break a determined rail
	//********************************************************************
	public boolean breakRail(String line, int block)
	{
		boolean sucess=false;
		
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e) {
				e.printStackTrace();
			}
			try {
				sucess = db.breakSection(line, block);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

		if (!sucess) {
			return true;
		} else {
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to cut a random rail
	//********************************************************************
	public boolean cutRail()
	{
		
		String[] lines = { "Red", "Green" };
		boolean sucess = true; 
		Random rand = new Random();
		int n = rand.nextInt(77) + 1;
		int i = rand.nextInt(2);
		String breakRailConfirm = JOptionPane.showInputDialog(parent,
				"Are you sure you want cut the power to block: " + lines[i] + " " + n + "? (y/n)", null);

		if (breakRailConfirm.equalsIgnoreCase("y")) {
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e) {
				e.printStackTrace();
			}
			try {
				sucess = db.cutSection(lines[i], n);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!sucess) {
			JOptionPane.showMessageDialog(parent, "Power was cut!");
			return true;
		} else {
			JOptionPane.showMessageDialog(parent, "Power was not cut!");
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to cut a determined rail
	//********************************************************************
	public boolean cutRail(String line, int block)
	{
		boolean sucess=false;
		
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e) {
				e.printStackTrace();
			}
			try {
				sucess = db.cutSection(line, block);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

		if (!sucess) {
			return true;
		} else {
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to break the circuit randomly
	//********************************************************************
	public boolean breakRailCircuit()
	{
		String[] lines = { "Red", "Green" };
		boolean sucess = true;
		Random rand = new Random();
		int n = rand.nextInt(77) + 1;
		int i = rand.nextInt(2);
		String breakRailConfirm = JOptionPane.showInputDialog(parent,
				"Are you sure you want break the circuit on block: " + lines[i] + " " + n + "? (y/n)", null);

		if (breakRailConfirm.equalsIgnoreCase("y")) {
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e1) {
				e1.printStackTrace();
			}
			try {
				sucess = db.breakSectionCircuit(lines[i], n);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (!sucess) {
			JOptionPane.showMessageDialog(parent, "Circuit was broken!");
			return true;
		} else {
			JOptionPane.showMessageDialog(parent, "Circuit was not broken!");
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to break the circuit at a set block
	//********************************************************************
	public boolean breakRailCircuit(String line, int block)
	{
		boolean sucess=false;
		
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e) {
				e.printStackTrace();
			}
			try {
				sucess = db.breakSectionCircuit(line, block);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

		if (!sucess) {
			return true;
		} else {
			return false;
		}
	}
	
	//********************************************************************
	//Method calls TrackDBInteraction to reset the DB to the initial state
	//********************************************************************
	public boolean resetDB() throws IOException
	{
		
			try {
				db = new TrackDBInteraction();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException e1) {
				e1.printStackTrace();
			}
			try {
				 db.resetDB();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
	}

}
