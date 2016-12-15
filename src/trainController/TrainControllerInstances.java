package trainController;

import java.util.HashMap;

import TTEHome.SystemClock;
import trainModel.TrainModel;
import trainModel.TrainModelGUI;
import trainModel.Trains;

public class TrainControllerInstances {
	
	/**
	 * Maps IDs to their trains (only lists existing trains)
	 */
	protected HashMap<Integer, TrainController> trainList;
	/**
	 * Maps instance IDs to their UIs (only lists existing UIs)
	 */
	protected HashMap<Integer, TrainControllerUI> uiList;
	
	protected Trains trainmodels;
	
	protected SystemClock sysClock;
	
	/**
	 * The next unique instance id to assign to a UI (starts at 0).
	 * (Don't pay attention to this variable if you're not implementing the TrainController.)
	 */
	private int instanceID;
	
	/**
	 * The next unique train ID to assign to a train (starts at 1).
	 */
	private int nextID;
	
	public TrainControllerInstances(SystemClock sys, Trains trains) {
		
		sysClock = sys;
		
		trainList = new HashMap<Integer, TrainController>();
		uiList = new HashMap<Integer, TrainControllerUI>();
		trainmodels = trains;
		
		nextID = 1;
		instanceID = 0;
		
		UpdateThread ut = new UpdateThread();
		ut.start();
		
	}
	
	public void connectModelToUI(int newid, TrainModelGUI gui) {
		trainList.get(newid).model.ui = gui;
	}
	
	/**
	 * Checks that there are enough existing trains to dispatch a new train. If there are not,
	 * then a new train is created.
	 * @param line - "green" or "red" (case doesn't matter)
	 * @return id of train created
	 */
	public int createTrain(String line) {
		
		if (!trainList.containsKey(nextID)) {
			TrainModel passtm = null;
			TrainController passtc = new TrainController(this, nextID);
			
			trainList.put(nextID, passtc);
			if (trainmodels != null) passtm = trainmodels.addTrain(passtc, nextID, line);
			
			passtc.connectToModel(passtm);
			
			for (TrainControllerUI ui : uiList.values()) {
				ui.addTrainID(nextID);
			}
		}
		
		++nextID;
		
		return (nextID - 1);
		
	}
	
	/**
	 * Deletes a train with the specified id.
	 * @param id - ID of the train to be deleted
	 */
	public void deleteTrain(int id) {
		
		for (TrainControllerUI ui : uiList.values()) {
			ui.deleteTrainID(id);
		}
	
		if (trainList.containsKey(id)) {
			trainList.get(id).delete();
			trainList.remove(id);
		}
		
		trainmodels.deleteTrain(id);
	
	}
	
	/**
	 * Deletes an ARBITRARY train. (Used only for test purposes.)
	 */
	public void deleteTrain() {
		
		if (trainList.isEmpty()) return;
		
		int id = trainList.keySet().iterator().next(); //I think this is how iterators work?
		
		for (TrainControllerUI ui : uiList.values()) {
			ui.deleteTrainID(id);
		}
	
		if (trainList.containsKey(id)) {
			trainList.get(id).delete();
			trainList.remove(id);
		}
	
	}
	
	/**
	 * Creates a new instance of TrainControllerUI.
	 */
	public void newUI() {
		
		TrainControllerUI temp = new TrainControllerUI(instanceID, this, (trainmodels == null));
		uiList.put(instanceID, temp);
		
		++instanceID;
		
		for (int newid : trainList.keySet()) {
			temp.addTrainID(newid);
		}
		
	}
	
	/**
	 * Removes a TrainControllerUI from the list.
	 */
	public void deleteUI(int id) {
		if (uiList.containsKey(id))
			uiList.remove(id);
	}

	/**
	 * Connects the specified TrainControllerUI to the train with the specified train ID.
	 * @param trainid - the ID of the train to connect to
	 * @param tcui - the TrainControllerUI class to connect to the train
	 * @return valid TrainController if connection was successful, null otherwise
	 */
	public TrainController connectUI(int id, TrainControllerUI tcui) {
		
		if (trainList.containsKey(id)) {				//check that train ID exists
			if (trainList.get(id).connectedToUI()) {	//check that train is not connected to a UI
				return null;
			} else {
				trainList.get(id).connectToUI(tcui);
				return trainList.get(id);
			}
		} else {
			return null;
		}
		
	}
	
	private class UpdateThread extends Thread {
		
		/**
		 * Calls a method after a certain amount of time has elapsed.
		 */
		public void run() {
			
			//Sleep for specified time (not perfect)
			try {
				sleep(2000*sysClock.clock);
			} catch (InterruptedException e) {
				//irrelevant
			}
			
			for (TrainControllerUI ui : uiList.values()) {
				for (int newid : trainList.keySet()) {
					ui.deleteTrainID(newid); //just in case
					ui.addTrainID(newid);
				}
			}
		}
		
	}
	
}
