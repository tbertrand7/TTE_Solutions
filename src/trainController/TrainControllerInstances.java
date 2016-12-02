package trainController;

import java.util.HashMap;

import trainController.TrainController.Side;
import trainModel.TrainModel;
import trainModel.trainModelGUI;

public class TrainControllerInstances {
	
	/**
	 * Maps IDs to their trains (only lists existing trains)
	 */
	protected HashMap<Integer, TrainController> trainList;
	/**
	 * Maps instance IDs to their UIs (only lists existing UIs)
	 */
	protected HashMap<Integer, TrainControllerUI> uiList;
	
	/**
	 * The next unique instance id to assign to a UI (starts at 0).
	 * (Don't pay attention to this variable if you're not implementing the TrainController.)
	 */
	private int instanceID;
	
	public TrainControllerInstances() {
		trainList = new HashMap<Integer, TrainController>();
		uiList = new HashMap<Integer, TrainControllerUI>();
		
		//nextID = 1;
		instanceID = 0;
		
		UpdateThread ut = new UpdateThread();
		ut.start();
		
	}
	
	public void connectModelToUI(int newid, trainModelGUI gui) {
		trainList.get(newid).model.ui = gui;
	}
	
	/**
	 * Checks that there are enough existing trains to dispatch a new train. If there are not,
	 * then a new train is created.
	 * @param newid - the id of the new train
	 * @param line - "green" or "red" (case doesn't matter)
	 */
	public TrainModel createTrain(int newid, String line) {
		
		TrainModel tm = null;
		
		if (!trainList.containsKey(newid)) {
			TrainController tc = new TrainController(this, newid, line);
			trainList.put(newid, tc);
			tm = tc.getTrainModel();
			
			//trainList.put(newid, new TrainController(this, newid, line));
			
			for (TrainControllerUI ui : uiList.values()) {
				ui.addTrainID(newid);
			}
		}
		
		return tm;
		
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
	
	}
	
	/**
	 * Creates a new instance of TrainControllerUI.
	 */
	public void newUI() {
		
		TrainControllerUI temp = new TrainControllerUI(instanceID, this);
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
		
		public UpdateThread() {
			//nothing
		}
		
		/**
		 * Calls a method after a certain amount of time has elapsed.
		 */
		public void run() {
			
			//Sleep for specified time (not perfect)
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				//irrelevant
			}
			
			System.out.println("updating");
			
			for (TrainControllerUI ui : uiList.values()) {
				for (int newid : trainList.keySet()) {
					ui.addTrainID(newid);
				}
			}
		}
		
	}
	
}
