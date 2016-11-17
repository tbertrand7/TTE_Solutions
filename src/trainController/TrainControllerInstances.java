package trainController;

public class TrainControllerInstances extends TrainControllerInstancesInterface {

	/**
	 * Connects the specified TrainControllerUI to the train with the specified train ID.
	 * @param trainid - the ID of the train to connect to
	 * @param tcui - the TrainControllerUI class to connect 
	 * @return
	 */
	public boolean connectUI(int trainid, TrainControllerUI tcui) {
		
		if (trainList.get(trainid).connectedToUI()) {
			return false;
		} else {
			trainList.get(trainid).connectToUI(tcui);
			return true;
		}
		
	}
	
}
