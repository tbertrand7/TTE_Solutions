package trainModel;

import java.util.ArrayList;
import java.util.HashMap;

import trainController.TrainControllerUI;

public class Trains {
	
	/**
	 * Maps IDs to their trains (only lists existing trains)
	 */
	protected HashMap<Integer, TrainModel> trainList;
	
	/**
	 * Maps instance IDs to their UIs (only lists existing UIs)
	 */
	protected ArrayList<trainModelGUI> uiList;

	
	public Trains() {
		trainList = new HashMap<Integer, TrainModel>();
		uiList = new ArrayList<trainModelGUI>();
	}
	
	
	/**
	 * Creates a new instance of TrainControllerUI.
	 */
	public void newUI() {
		
	//	uiList.add(new trainModelGUI(this));
	}
	
	/**
	 * Removes a TrainControllerUI from the list.
	 */
	public void deleteUI(trainModelGUI t) {
		if (uiList.contains(t))
			uiList.remove(t);
	}

	/**
	 * Connects the specified TrainControllerUI to the train with the specified train ID.
	 * @param trainid - the ID of the train to connect to
	 * @param tmui - the TrainModelGUI class to connect to the train
	 * @return valid TrainModel if connection was successful, null otherwise
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

