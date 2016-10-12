package trainController;

import java.awt.EventQueue;
import java.util.HashMap;

public class TrainControllerInstances {
	
	private HashMap<Integer, trainControllerUI> UIList;	//maps instance IDs to trainControllerUI classes
	private HashMap<Integer, PowerCalculator> powerList; //maps instance IDs to PowerThread classes <-- this should be train IDs, not instance IDs
	private int nextID; //this is NOT the train ID, it's the instance ID!!!!!
	
	public TrainControllerInstances() {
		nextID = 0;
		UIList = new HashMap<Integer, trainControllerUI>();
		powerList = new HashMap<Integer, PowerCalculator>();
	}
	
	private synchronized void printKeySet() {
		for (int key : UIList.keySet()) {
			System.out.println(key);
		}
		System.out.println();
	}
	
	public synchronized void newTestPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel frame = new TestPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private synchronized void createNew(trainControllerUI tcui) {
		//Put into list
		UIList.put(nextID, tcui);
		
		//Create new power calculator
		PowerCalculator pc = new PowerCalculator(tcui);
		
		powerList.put(nextID, pc);
		
		nextID++;
	}
	
	public synchronized void newUI() {
		TrainControllerInstances instance = this;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trainControllerUI tcui = new trainControllerUI(nextID, instance);
					instance.createNew(tcui);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public synchronized void removeUI(int id) {
		UIList.remove(id);
		powerList.remove(id);
	}

}
