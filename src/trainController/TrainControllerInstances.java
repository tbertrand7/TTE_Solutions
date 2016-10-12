package trainController;

import java.awt.EventQueue;
import java.util.HashMap;

public class TrainControllerInstances {
	
	private HashMap<Integer, trainControllerUI> UIList;	//maps instance IDs to trainControllerUI classes
	private int nextID; //this is NOT the train ID, it's the instance ID!!!!!
	
	public TrainControllerInstances() {
		nextID = 0;
		UIList = new HashMap<Integer, trainControllerUI>();
	}
	
	private synchronized void printKeySet() {
		for (int key : UIList.keySet()) {
			System.out.println(key);
		}
		System.out.println();
	}
	
	public synchronized void newUI() {
		TrainControllerInstances tci = this;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIList.put(nextID, new trainControllerUI(nextID, tci));
					nextID++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public synchronized void removeUI(int id) {
		UIList.remove(id);
	}

}
