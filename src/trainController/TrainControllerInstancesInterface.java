package trainController;

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
	 * Count of all dispatched trains
	 */
	private int dispatched;
	
	/**
	 * The next unique id to assign to a train
	 */
	private int nextID;
	
	/**
	 * Checks that there are enough existing trains to dispatch a new train. If there are not,
	 * then a new train is created.
	 */
	public void createTrain() {
		
		if (trainList.size() < (dispatched + 1)) {
			trainList.put(nextID, new TrainController(nextID));
			++dispatched;
			++nextID;
		}
		
	}
	
	/**
	 * Deletes a train with the specified id.
	 * @param id - ID of the train to be deleted
	 */
	public void deleteTrain(int id) {
	
		trainList.get(id).delete();
		trainList.remove(id);
		--dispatched;
	
	}
	
	/**
	 * Creates a new instance of TrainControllerUI.
	 */
	public void newUI() {
	
		//TODO Create a new TrainControllerUI instance
		
	}

}
