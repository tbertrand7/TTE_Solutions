package trainModel;

import java.util.ArrayList;
import TTEHome.SystemClock;
import java.util.HashMap;

import trainController.TrainController;


public class Trains {
	
	/**
	 * Maps IDs to their trains 
	 */
	protected HashMap<Integer, TrainModel> trainList;
	
	/**
	 * Maps instance IDs to their UIs 
	 */
	protected ArrayList<TrainModelGUI> uiList;
	
	public SystemClock sysClock;

	
	public Trains(SystemClock sysClock) {
		trainList = new HashMap<Integer, TrainModel>();
		uiList = new ArrayList<TrainModelGUI>();
	}
	
	public TrainModel addTrain(TrainController tc,int id ,String line){
		
		TrainModel tempModel = new TrainModel(this,tc, id, line, sysClock);
		
		trainList.put(id, tempModel);
		
		return tempModel;
	}
	
	
	public TrainModel addTestTrain(String line, int id){
		
		
		TrainModel tempModel = new TrainModel(line,id);
		
		trainList.put(id, tempModel);
		
		return tempModel;
	}
	
	
	public void deleteTrain(int id){
		if (trainList.containsKey(id)) {
			trainList.get(id).delete();
			trainList.remove(id);
		}
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

