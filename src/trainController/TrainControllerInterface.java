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
	 * Speed commanded by the wayside controller
	 */
	protected double speedCommand;
	/**
	 * Authority commanded by the wayside controller
	 */
	protected double authority;
	/**
	 * Answers the question "Is the train currently in a tunnel?"
	 */
	protected boolean inTunnel;
	
	
	/**
	 * Assigns train id and creates a TrainModel.
	 */
	public TrainControllerInterface(int uniqueid) {
		
		id = uniqueid;
		
		/* @Matt: This TrainModel constructor needs to be added to the TrainModel class! */
		//model = new TrainModel(this, id);
		
	}
	
	/**
	 * Sets commanded speed and authority.
	 * @param speed - speed passed from the wayside controller
	 * @param auth - authority passed from the wayside controller
	 */
	public void setSpeedAndAuthority(double speed, double auth) {
		
		speedCommand = speed;
		authority = auth;
		
	}
	
	/**
	 * Communicates a change in the train controller's tunnel status.
	 * @param state - true if in a tunnel, false otherwise
	 */
	public void setTunnel(boolean state) {
		
		inTunnel = state;
		
	}
	
	/**
	 * Signals that an error has occurred.
	 * @param signaltype - a TrainSignal describing the error
	 */
	public void signal(int signaltype) {
		
		//TODO handle errors
		
	}
	
	/**
	 * Communicates that the train has entered a new block.
	 */
	public void newBlock() {
		
		authority -= 1;
		
	}
	
	/**
	 * Signals that the train is approaching a station. The train will announce the upcoming station, 
	 * slow down and stop, open its doors for a bit, close its doors, and continue on its way.
	 * @param name - the station name
	 */
	public void approachStation(String name) {
		
		//TODO write this method
		
	}

}
