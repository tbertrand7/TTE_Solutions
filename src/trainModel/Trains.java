package trainModel;

import java.util.ArrayList;
import java.util.HashMap;

import trainController.TrainController;


public class Trains {
	
	/**
	 * Maps IDs to their trains (only lists existing trains)
	 */
	protected HashMap<Integer, TrainModel> trainList;
	
	/**
	 * Maps instance IDs to their UIs (only lists existing UIs)
	 */
	protected ArrayList<TrainModelGUI> uiList;

	
	public Trains() {
		trainList = new HashMap<Integer, TrainModel>();
		uiList = new ArrayList<TrainModelGUI>();
	}
	
	public TrainModel addTrain(TrainController tc,int id ,String line){
		
		TrainModel tempModel = new TrainModel(this,tc, id, line);
		
		trainList.put(id, tempModel);
		
		//TODO: @anna change constructor in TrainController.java
		return tempModel;
	}
	
	
	public TrainModel addTestTrain(String line, int id){
		
		
		TrainModel tempModel = new TrainModel(line,id);
		
		trainList.put(id, tempModel);
		
		return tempModel;
	}
	
	
	public void deleteTrain(int id){
		
		trainList.remove(id);
	}
	
	/**
	 * Creates a new instance of TrainModelUI.
	 */
	public void newUI() {
		
		uiList.add(new TrainModelGUI(this));
	}
	
	/**
	 * Removes a TrainModelUI from the list.
	 */
	public void deleteUI(TrainModelGUI t) {
		if (uiList.contains(t))
			uiList.remove(t);
	}

	/**
	 * Connects the specified TrainModelUI to the train with the specified train ID.
	 * @param trainid - the ID of the train to connect to
	 * @param tmui - the TrainModelGUI class to connect to the train
	 * @return valid TrainModel if connection was successful, null otherwise
	 */
	public TrainModel connectGUI(int id, TrainModelGUI tmui) {
		
		if (trainList.containsKey(id)) {				//check that train ID exists
			if (trainList.get(id).connectedToUI()) {	//check that train is not connected to a UI
				return null;
			} else {
				trainList.get(id).connectToUI(tmui);
				return trainList.get(id);
			}
		} 
		else {
			return null;
		}
		
	}
	
}

