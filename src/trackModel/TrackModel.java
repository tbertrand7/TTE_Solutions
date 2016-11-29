package trackModel;

import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TrackModel {
	
	final JFrame parent = new JFrame();
	TrackDBInteraction db = null;

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


}
