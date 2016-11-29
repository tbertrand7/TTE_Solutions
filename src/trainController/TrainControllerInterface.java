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
	 * The controller's personal power calculator.
	 */
	protected PowerCalculator pc;
	
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
	
	/**
	 * Used if the train needs to stop for a station or because of a failure.
	 */
	protected boolean stop;
	
	/**
	 * If true, train is in automatic mode. Otherwise, it's in manual mode.
	 */
	protected boolean automatic;
	
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
		//model = new TrainModel((TrainController)this, id);
		
	}
	
	/**
	 * Sets commanded speed and authority, and whether the track is underground.
	 * @param speed - speed passed from the wayside controller
	 * @param auth - authority passed from the wayside controller
	 * @param under - true if underground, false otherwise
	 */
	public void passInfo(double speed, double auth, boolean under) {
		
		if (authority == 0 && auth > 0) {
			
			setStop(false);
			
			sBrakeOn = false;
			if (connectedToUI()) ui.setServiceBrake(false);
			//TODO @Matt model.setServiceBrake(false);?
			
		}
		
		speedCommand = speed;
		authority = auth;
		inTunnel = under;
		
	}
	
	/**
	 * Sets commanded speed.
	 * @param speed - commanded speed in m/s
	 */
	public synchronized void setSpeedCommand(double speed) {
		
		speedCommand = speed;
		
	}
	
	/**
	 * Sets commanded authority.
	 * @param auth - authority in blocks
	 */
	public synchronized void setAuthority(int auth) {
		
		if (authority == 0 && auth > 0) {
			
			setStop(false);
			
			sBrakeOn = false;
			if (connectedToUI()) ui.setServiceBrake(false);
			//TODO @Matt model.setServiceBrake(false);?
			
		}
		
		authority = auth;
		
	}
	
	/**
	 * Sets whether the train is in a tunnel.
	 * @param in - true if 
	 */
	public synchronized void setInTunnel(boolean in) {
		
		inTunnel = in;
		
		if (automatic) {
			setLights(in);
		}
		
	}
	
	/**
	 * Signals that an error has occurred.
	 * @param signaltype - a Signal describing the error
	 */
	public synchronized void signal(Signal signaltype) {
		
		if (connectedToUI()) {
			String message = null;
			
			switch (signaltype) {
			case ENGINE_FAILURE:
				message = "ERROR: ENGINE FAILURE\n"; break;
			case RAIL_FAILURE:
				message = "ERROR: RAIL FAILURE\n"; break;
			case SIGNAL_PICKUP_FAILURE:
				message = "ERROR: SIGNAL PICKUP FAILURE\n"; break;
			case BRAKE_FAILURE:
				message = "ERROR: BRAKE FAILURE\n"; break;
			}
			
			if (message != null) ui.message(message);
		}
		
		setStop(true);
		
		eBrakeOn = true;
		if (connectedToUI()) ui.setEmergencyBrake(true);
		//TODO @Matt model.setEmergencyBrake(true);?
	}
	
	/**
	 * Signals that an error has been repaired.
	 */
	public synchronized void repair() {
		
		setStop(false);
		
		eBrakeOn = false;
		if (connectedToUI()) ui.setEmergencyBrake(false);
		//TODO @Matt model.setEmergencyBrake(false);?
		
	}
	
	/**
	 * Communicates that the train has entered a new block.
	 */
	public void newBlock() {
		
		authority -= 1;
		
		if (authority == 0) {
			setStop(true);
			
			sBrakeOn = true;
			if (connectedToUI()) ui.setServiceBrake(true);
			//TODO @Matt model.setServiceBrake(true);?
			
			power = 0;
		}
	}
	
	/**
	 * Signals that the train is approaching a station. The train will announce the upcoming station, 
	 * slow down and stop, open its doors for a bit, close its doors, and continue on its way.
	 * @param name - the station name
	 */
	public synchronized void approachStation(String name, Side doors) {
		
		setStop(true);
		
		sBrakeOn = true;
		if (connectedToUI()) ui.setServiceBrake(true);
		//TODO @Matt model.setServiceBrake(true);?
		
		if (connectedToUI()) ui.announceStation(name);
		
		while(speedCurrent != 0); //busy wait until the train stops
		
		if (doors == Side.RIGHT) {
			rightDoorsOpen = true;
			//TODO @Matt model.setRightDoorsOpen(true);?
		} else {
			leftDoorsOpen = true;
			//TODO @Matt model.setLeftDoorsOpen(true);?
		}
		
		long timestart = System.currentTimeMillis();
		while (System.currentTimeMillis() < (timestart + 5000)); //busy wait for 5 seconds (SPEEDY PASSENGERS)
		
		if (doors == Side.RIGHT) {
			rightDoorsOpen = false;
			//TODO @Matt model.setRightDoorsOpen(false);?
		} else {
			leftDoorsOpen = false;
			//TODO @Matt model.setLeftDoorsOpen(false);?
		}
		
		sBrakeOn = false;
		if (connectedToUI()) ui.setServiceBrake(false);
		//TODO @Matt model.setServiceBrake(false);?
		
		setStop(false);
		
	}
	
	/**
	 * Setter for current speed.
	 * @param speed - current speed, in m/s
	 */
	public synchronized void setSpeedCurrent(double speed) {
		
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
	 * Checks whether the train is connected to a UI.
	 * @return true if a UI is connected, false otherwise
	 */
	public synchronized boolean connectedToUI() {
	
		return !(ui == null);
		
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
	 * Setter for the service brake, if it is allowed.
	 * @param on - true if the brake is engaged, otherwise false
	 * @return true if it is allowed, otherwise false
	 */
	public boolean setServiceBrake(boolean on) {
		
		if (!stop) {
			sBrakeOn = on;
			eBrakeOn = !on;
			//TODO @Matt model.setServiceBrake(on);?
			//TODO @Matt model.setEmergencyBrake(!on);?
			
			return true;
		} else return false;
		
	}
	
	/**
	 * Setter for the emergency brake, if it is allowed.
	 * @param on - true if the brake is engaged, otherwise false
	 * @return true if it is allowed, otherwise false
	 */
	public boolean setEmergencyBrake(boolean on) {
		
		if (!stop) {
			eBrakeOn = on;
			sBrakeOn = !on;
			//TODO @Matt model.setEmergencyBrake(on);?
			//TODO @Matt model.setServiceBrake(!on);?
			
			return true;
		} else return false;
		
	}
	
	/**
	 * Getter for the service brake.
	 * @return true if the brake is engaged, otherwise false
	 */
	public boolean getServiceBrake() {
		
		return sBrakeOn;
		
	}
	
	/**
	 * Getter for the emergency brake.
	 * @return true if the brake is engaged, otherwise false
	 */
	public boolean getEmergencyBrake() {
		
		return eBrakeOn;
		
	}
	
	/**
	 * Setter for the lights.
	 * @param on - true if the light is on, otherwise false
	 */
	public void setLights(boolean on) {
		
		lightsOn = on;
		
		if (connectedToUI() && automatic) {
			ui.tglbtnLights.setSelected(on);
			ui.imgLight.setEnabled(on);
		}
		//TODO @Matt model.setLights(on);?
		
	}
	
	/**
	 * Sets the train's temporarily stopped signal.
	 * @param dontgo - true if the train needs to stop, otherwise false
	 */
	public void setStop(boolean dontgo) {
		
		stop = dontgo;
		
		pc.tempStop(stop);
		
	}

}
