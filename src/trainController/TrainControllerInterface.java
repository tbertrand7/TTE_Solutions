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
	 * All error signals that can occur.
	 * @author anna
	 *
	 */
	public enum Signal {
		ENGINE_FAILURE,
		RAIL_FAILURE,
		SIGNAL_PICKUP_FAILURE,
		BRAKE_FAILURE
	}
	
	/**
	 * Door sides, because I believe this is the cleanest way
	 * @author anna
	 *
	 */
	public enum Side {
		RIGHT,
		LEFT
	}
	
	/**
	 * The train controller's personal train model
	 */
	protected TrainModel model;
	/**
	 * The train's unique id
	 */
	protected int id;
	
	/**
	 * The UI monitoring this train controller
	 */
	protected TrainControllerUI ui;
	
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
	 * Current speed
	 */
	protected double speedCurrent;
	/**
	 * Current power output
	 */
	public double power;
	
	//Door status
	protected boolean rightDoorsOpen;
	protected boolean leftDoorsOpen;
	
	//Temperature
	protected int temperature;
	
	//Brakes
	protected boolean sBrakeOn;
	protected boolean eBrakeOn;
	
	//Lights
	protected boolean lightsOn;
	
	/**
	 * Assigns train id and creates a TrainModel.
	 */
	public TrainControllerInterface(int uniqueid) {
		
		id = uniqueid;
		
		speedCommand = 0;
		authority = 0;
		inTunnel = false;
		speedCurrent = 0;
		power = 0;
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		temperature = 68;
		sBrakeOn = false;
		eBrakeOn = false;
		
		/* @Matt: This TrainModel constructor needs to be added to the TrainModel class! */
		//model = new TrainModel(this, id);
		
	}
	
	/**
	 * Sets commanded speed and authority, and whether the track is underground.
	 * @param speed - speed passed from the wayside controller
	 * @param auth - authority passed from the wayside controller
	 * @param under - true if underground, false otherwise
	 */
	public void passInfo(double speed, double auth, boolean under) {
		
		speedCommand = speed;
		authority = auth;
		inTunnel = under;
		
	}
	
	/**
	 * Signals that an error has occurred.
	 * @param signaltype - a Signal describing the error
	 */
	public void signal(Signal signaltype) {
		
		//TODO handle errors
		
	}
	
	/**
	 * Communicates that the train has entered a new block.
	 */
	public void newBlock() {
		
		authority -= 1;
		
		//TODO write check for authority == 0
		
	}
	
	/**
	 * Signals that the train is approaching a station. The train will announce the upcoming station, 
	 * slow down and stop, open its doors for a bit, close its doors, and continue on its way.
	 * @param name - the station name
	 */
	public void approachStation(String name) {
		
		//TODO write this method
		
	}
	
	/**
	 * Setter for current speed.
	 * @param speed - current speed, in m/s
	 */
	public void setSpeedCurrent(double speed) {
		
		speedCurrent = speed;
		
		if (ui != null) ui.setSpeedCurrent(speedCurrent);
		
	}
	
	/**
	 * Getter for current power output.
	 * @return current power, in watts
	 */
	public double getPower() {
		
		return power;
		
	}
	
	/**
	 * Sets the doors on the specified side to the specified position if it is safe.
	 * @param side - which side the requested doors are on
	 * @param open - what state to set the doors to
	 * @return true if it is safe to change door position, otherwise false
	 */
	public boolean requestDoors(Side side, boolean open) {
		
		if (speedCurrent == 0 && power == 0) {
			if (side == Side.RIGHT) {
				rightDoorsOpen = open;
				//TODO @Matt model.setRightDoorsOpen(open);?
			} else {
				leftDoorsOpen = open;
				//TODO @Matt model.setLeftDoorsOpen(open);?
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Sets temperature to specified value if it is comfortable.
	 * @param temp - temperature in Fahrenheit
	 * @return true if the temperature is safe, otherwise false
	 */
	public boolean requestTemperature(int temp) {
		
		if (temp < 75 && temp > 65) { //absolutely arbitrary numbers
			temperature = temp;
			//TODO @Matt model.setTemperature(temperature);?
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Setter for the service brake.
	 * @param on - true if the brake is engaged, otherwise false
	 */
	public void setServiceBrake(boolean on) {
		
		sBrakeOn = on;
		//TODO @Matt model.setServiceBrake(on);?
		
	}
	
	/**
	 * Setter for the emergency brake.
	 * @param on - true if the brake is engaged, otherwise false
	 */
	public void setEmergencyBrake(boolean on) {
		
		eBrakeOn = on;
		//TODO @Matt model.setEmergencyBrake(on);?
		
	}
	
	/**
	 * Setter for the lights.
	 * @param on - true if the light is on, otherwise false
	 */
	public void setLights(boolean on) {
		
		lightsOn = on;
		//TODO @Matt model.setLights(on);?
		
	}

}
