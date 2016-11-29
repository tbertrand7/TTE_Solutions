package trainController;

import java.util.HashMap;

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
	 * The next unique id to assign to a train (starts at 1)
	 */
	private int nextID;
	
	/**
	 * The next unique instance id to assign to a UI (starts at 0).
	 * (Don't pay attention to this variable if you're not implementing the TrainController.)
	 */
	private int instanceID;
	
	public TrainControllerInstances() {
		trainList = new HashMap<Integer, TrainController>();
		uiList = new HashMap<Integer, TrainControllerUI>();
		
		nextID = 1;
		instanceID = 0;
	}
	
	/**
	 * Checks that there are enough existing trains to dispatch a new train. If there are not,
	 * then a new train is created.
	 */
	public void createTrain(int newid) {
		
		for (Integer i : uiList.keySet()) {
			uiList.get(i).addTrainID(newid);
		}
		
		if (!trainList.containsKey(newid)) {
			trainList.put(newid, new TrainController(this, newid));
		}
		
	}
	
	/**
	 * Deletes a train with the specified id.
	 * @param id - ID of the train to be deleted
	 */
	public void deleteTrain(int id) {
		
		for (Integer i : uiList.keySet()) {
			uiList.get(i).deleteTrainID(id);
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
		
		uiList.put(new Integer(instanceID), new TrainControllerUI(instanceID, this));
		
		++instanceID;
		
		//DELETE LATER
		createTrain(1);
		
	}
	
	/**
	 * Removes a TrainControllerUI from the list.
	 */
	public void deleteUI(int id) {
		uiList.remove(new Integer(id));
	}

	/**
	 * Connects the specified TrainControllerUI to the train with the specified train ID.
	 * @param trainid - the ID of the train to connect to
	 * @param tcui - the TrainControllerUI class to connect to the train
	 * @return vlid if connection was successful, null otherwise
	 */
	public TrainController connectUI(int trainid, TrainControllerUI tcui) {
		
		if (trainList.containsKey(trainid)) {				//check that train ID exists
			if (trainList.get(trainid).connectedToUI()) {	//check that train is not connected to a UI
				return null;
			} else {
				trainList.get(trainid).connectToUI(tcui);
				return trainList.get(trainid);
			}
		} else {
			return null;
		}
		
	}
	
}
