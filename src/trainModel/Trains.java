package trainModel;

import java.util.HashMap;

public class Trains {
	
	/**
	 * Maps IDs to their trains (only lists existing trains)
	 */
	protected HashMap<Integer, TrainModel> trainList;
	/**
	 * Maps instance IDs to their UIs (only lists existing UIs)
	 */
	protected HashMap<Integer, trainModelGUI> uiList;
	
	
	public Trains() {
		trainList = new HashMap<Integer, TrainModel>();
		uiList = new HashMap<Integer, trainModelGUI>();
		
		//nextID = 1;
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
	 * @param tmui - the TrainModelGUI class to connect to the train
	 * @return valid TrainController if connection was successful, null otherwise
	 */
	public TrainModel connectUI(int id, trainModelGUI tmui) {
		
		if (trainList.containsKey(id)) {				//check that train ID exists
			if (trainList.get(id).connectedToUI()) {	//check that train is not connected to a UI
				return null;
			} else {
				trainList.get(id).connectToUI(tmui);
				return trainList.get(id);
			}
		} else {
			return null;
		}
		
	}
	
}

