package trainController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This abstract class describes all variables and methods that may be of interest to 
 * other subsystems.
 * @author anna
 *
 */
public abstract class TrainControllerInstancesInterface {
	
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
	
	public TrainControllerInstancesInterface() {
		nextID = 1;
		instanceID = 0;
	}
	
	/**
	 * Checks that there are enough existing trains to dispatch a new train. If there are not,
	 * then a new train is created.
	 */
	public void createTrain(int newid) {
		
		for (TrainControllerUI ui : uiList.values()) {
			ui.addTrainID(newid);
		}
		
		if (!trainList.containsKey(newid)) {
			/* Not entirely sure if the cast below is okay - need to test! */
			trainList.put(newid, new TrainController((TrainControllerInstances) this, newid));
		}
		
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
	
		/* Not entirely sure if the cast below is okay - need to test! */
		uiList.put(instanceID, new TrainControllerUI(instanceID, (TrainControllerInstances)this));
		
		++instanceID;
		
	}
	
	/**
	 * Removes a TrainControllerUI from the list.
	 */
	public void deleteUI(int id) {
		uiList.remove(id);
	}

}
