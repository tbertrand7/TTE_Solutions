package trainController;

import trainModel.TrainModel;

/**
 * The class that implements this abstract class will be the only class accessed by the train model. 
 * This interface enumerates each and every method that may be of interest to the train model subsystem.
 * @author anna
 *
 */
public abstract class TrainControllerInterface {
	
	/**
	 * The train controller's personal train model
	 */
	protected TrainModel model;
	
	/**
	 * The train's unique id
	 */
	protected int id;
	
	/**
	 * Assigns train id and creates a TrainModel.
	 */
	public TrainControllerInterface(int uniqueid) {
		
		id = uniqueid;
		
		/* @Matt: This TrainModel constructor needs to be added to the TrainModel class! */
		//model = new TrainModel(this, id);
		
	}

}
