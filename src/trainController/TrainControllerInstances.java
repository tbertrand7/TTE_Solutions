package trainController;

public class TrainControllerInstances extends TrainControllerInstancesInterface {

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
