package trainController;

import trainModel.TrainModel;

public class TrainController {
	
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
	 * Speed limit
	 */
	protected double speedLimit;
	
	/**
	 * The name of the next station.
	 */
	protected String station;
	
	/**
	 * Used if the train needs to stop.
	 */
	protected boolean stop;
	
	/**
	 * Used to indicate a current failure.
	 */
	protected boolean failure;
	
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
	 * Assigns train id and creates a TrainModel, assigns id and parent, creates PowerCalculators.
	 */
	public TrainController(TrainControllerInstances tci, int uniqueid) {
		
		id = uniqueid;
		
		speedCommand = 0;
		authority = 0;
		inTunnel = false;
		speedCurrent = 0;
		power = 0;
		speedLimit = 31.2928; //default to 70 mph
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		temperature = 68;
		sBrakeOn = false;
		eBrakeOn = false;
		failure = false;
		
		parent = tci;
		ui = null;
		
		speedRequest = 0;
		automatic = true;
		
		//TODO Create new array of PowerCalculators (for now it's just one)
		pc = new PowerCalculator(this);
		pc.start();
		
		setStop(false);
		
	}
	
	/**
	 * Sets commanded speed and authority, and whether the track is underground.
	 * @param speed - speed passed from the wayside controller
	 * @param auth - authority passed from the wayside controller
	 * @param under - true if underground, false otherwise
	 * @param newblock - true if this is the first time this block's info is being passed, false otherwise
	 */
	public void passInfo(double speed, double auth, boolean under, String nextstation, boolean newblock) {
		
		if (nextstation != null)
			if (newblock && nextstation.contains("STATION"))
				station = nextstation.split(";")[1].trim();
		
		if (authority > 0 && newblock) {
			authority -= 1; //decrement authority
		}
		
		if (auth >= 0 && newblock) authority = auth;
		
		if (authority > 0 && stop && !failure) { //authority is being changed from 0 to something valid
			
			setStop(false);
			
			sBrakeOn = false;
			if (connectedToUI()) ui.setServiceBrake(false);
			if (connectedToModel()) model.setServiceBrake(false);
			
		}
		
		if (speed >= 0) speedCommand = speed;
		inTunnel = under;
		
		if (authority == 0 && !stop) { //authority is 0, need to stop
			
			setStop(true);
			
			sBrakeOn = true;
			if (connectedToUI()) ui.setServiceBrake(true);
			if (connectedToModel()) model.setServiceBrake(true);
			
			power = 0;
		}
		
	}
	
	/**
	 * Sets commanded speed.
	 * @param speed - commanded speed in m/s
	 */
	public synchronized void setSpeedCommand(double speed) {
		
		if (speed >= 0) speedCommand = speed;
		
	}
	
	/**
	 * Sets commanded authority.
	 * @param auth - authority in blocks
	 */
	public synchronized void setAuthority(int auth) {
		
		if (auth >= 0) {
			authority = auth;
		}
		
		if (authority > 0 && stop && !failure) { //authority is being changed from 0 to something valid
			
			setStop(false);
			
			sBrakeOn = false;
			if (connectedToUI()) ui.setServiceBrake(false);
			if (connectedToModel()) model.setServiceBrake(false);
			
		}
		
		if (authority == 0 && !stop) { //authority is 0, need to stop
			
			setStop(true);
			
			sBrakeOn = true;
			if (connectedToUI()) ui.setServiceBrake(true);
			if (connectedToModel()) model.setServiceBrake(true);
			
			power = 0;
			
		}
		
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
		
		failure = true;
		
		setStop(true);
		
		eBrakeOn = true;
		if (connectedToUI()) ui.setEmergencyBrake(true);
		if (connectedToModel()) model.setEmergencyBrake(true);
	}
	
	/**
	 * Signals that an error has been repaired.
	 */
	public synchronized void repair() {
		
		if (connectedToUI()) {
			ui.message("Failure has been repaired.\n");
		}
		
		failure = false;
		
		setStop(false);
		
		eBrakeOn = false;
		if (connectedToUI()) ui.setEmergencyBrake(false);
		if (connectedToModel()) model.setEmergencyBrake(false);
		
	}
	
	/**
	 * Signals that the train is approaching a station. The train will announce the upcoming station, 
	 * slow down and stop, open its doors for a bit, close its doors, and continue on its way.
	 */
	public synchronized void approachStation() {
		
		setStop(true);
		
		sBrakeOn = true;
		if (connectedToUI()) ui.setServiceBrake(true);
		if (connectedToModel()) model.setServiceBrake(true);
		
		if (connectedToUI()) ui.announceStation(station);
		
		WaitThread wt = new WaitThread(Side.RIGHT, 1000*parent.sysClock.clock, 1); //default to right doors
		wt.start();
		
	}
	
	/**
	 * Second phase in the approach station protocol.
	 * @param doors - which side of the train the doors will open on
	 */
	private synchronized void openDoors(Side doors) {
		
		if (doors == Side.RIGHT) {
			rightDoorsOpen = true;
			if (connectedToUI()) ui.setDoorsDirect(false, true);
			if (connectedToModel()) model.setRightDoorsOpen(true);
		} else {
			leftDoorsOpen = true;
			if (connectedToUI()) ui.setDoorsDirect(true, false);
			if (connectedToModel()) model.setLeftDoorsOpen(true);
		}
		
		WaitThread wt = new WaitThread(doors, 5000*parent.sysClock.clock, 2);
		wt.start();
		
	}
	
	/**
	 * Last phase in the approach station protocol.
	 * @param doors - which side of the train the doors will close on
	 */
	private synchronized void closeDoors(Side doors) {
		
		if (doors == Side.RIGHT) {
			rightDoorsOpen = false;
			if (connectedToUI()) ui.setDoorsDirect(false, false);
			if (connectedToModel()) model.setRightDoorsOpen(false);
		} else {
			leftDoorsOpen = false;
			if (connectedToUI()) ui.setDoorsDirect(false, false);
			if (connectedToModel()) model.setLeftDoorsOpen(false);
		}
		
		sBrakeOn = false;
		if (connectedToUI()) ui.setServiceBrake(false);
		if (connectedToModel()) model.setServiceBrake(false);
		
		setStop(false);
		
	}
	
	/**
	 * Setter for current speed.
	 * @param speed - current speed, in m/s
	 */
	public synchronized void setSpeedCurrent(double speed) {
		
		speedCurrent = speed;
		
		if (connectedToUI()) ui.setSpeedCurrent(speedCurrent);
		
	}
	
	/**
	 * Getter for current power output.
	 * @return current power, in watts
	 */
	public double getPower() {
		
		return power;
		
	}
	
	/**
	 * Sets speed limit.
	 * @param speed - speed limit in m/s
	 */
	public synchronized void setSpeedLimit(double speed) {
		
		speedLimit = speed;
		
		if (connectedToUI()) ui.setSpeedLimit(speed);
		
	}
	
	/**
	 * Checks whether the train is connected to a UI.
	 * @return true if a UI is connected, false otherwise
	 */
	public synchronized boolean connectedToUI() {
	
		return !(ui == null);
		
	}
	
	/**
	 * Checks whether the train is connected to a train model.
	 * @return true if a train model is connected, false otherwise
	 */
	public synchronized boolean connectedToModel() {
	
		return !(model == null);
		
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
				if (connectedToModel()) model.setRightDoorsOpen(open);
			} else {
				leftDoorsOpen = open;
				if (connectedToModel()) model.setLeftDoorsOpen(open);
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
			if (connectedToUI()) ui.setTemperatureDirect(temperature);
			
			if (connectedToModel()) model.setTemperature(temperature);
			
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
			eBrakeOn = false;
			if (connectedToModel()) model.setServiceBrake(on);
			if (connectedToModel()) model.setEmergencyBrake(false);
			
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
			sBrakeOn = false;
			if (connectedToModel()) model.setEmergencyBrake(on);
			if (connectedToModel()) model.setServiceBrake(false);
			
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
			ui.tunnel(on);
		}
		if (connectedToModel()) model.changeLightsStatus(on);
		
	}
	
	/**
	 * Sets the train's temporarily stopped signal.
	 * @param dontgo - true if the train needs to stop, otherwise false
	 */
	public void setStop(boolean dontgo) {
		
		stop = dontgo;
		
		while (pc == null) {
			WaitThread wt = new WaitThread(500);
			wt.start();
		}
		pc.tempStop(stop);
		
	}
	
	/**
	 * The TrainControllerInstances class this train controller belongs to.
	 */
	protected TrainControllerInstances parent;
	
	/**
	 * Speed requested by the train controller
	 */
	private double speedRequest;
	
	/**
	 * Stops PowerCalculators, and disconnects from its train model and UI.
	 */
	public void delete() {
		
		//TODO Stop all power calculators (just one for now)
		pc.stopRun();
		
		if (connectedToModel()) disconnectFromModel();
		
		ui.disconnect();
		disconnectFromUI();
		
	}
	
	/**
	 * Connects the train to the specified UI and initialize its display.
	 * @param tcui - the TrainControllerUI to connect to the train
	 */
	public void connectToUI(TrainControllerUI tcui) {
		
		ui = tcui;
		
		ui.setSpeedCurrent(speedCurrent);
		ui.setPower(power);
		ui.setSpeedLimit(speedLimit);
		ui.tunnel(lightsOn);
		ui.setDoorsDirect(leftDoorsOpen, rightDoorsOpen);
		ui.setTemperatureDirect(temperature);
		ui.setPassengerEmergencyBrake();
		ui.setEmergencyBrake(eBrakeOn);
		ui.setServiceBrake(sBrakeOn);
		ui.setAutomatic();
		
	}
	
	/**
	 * Disconnects the train from its UI.
	 */
	public void disconnectFromUI() {
		
		ui = null;
		
	}
	
	/**
	 * Connects the train to the specified train model.
	 * @param tm - the TrainModel to connect to the TrainController
	 */
	public void connectToModel(TrainModel tm) {
		
		model = tm;
	}
	
	/**
	 * Disconnects the train from its model.
	 */
	public void disconnectFromModel() {
		
		model = null;
		
	}
	
	/**
	 * Setter for speed request.
	 * @param speed - speed requested by train controller, in mph
	 */
	public void setSpeedRequest(double speed) {
		
		speedRequest = speed * 1609.34 / 3600;
		
	}
	
	/**
	 * Setter for automatic mode.
	 * @param auto - true if automatic mode, false if manual mode
	 */
	public void setAutomatic(boolean auto) {
		
		automatic = auto;
		
	}
	
	/**
	 * Returns the speed command or speed request, depending on automatic/manual mode.
	 * @return target speed of the train
	 */
	public synchronized double getSpeed() {
		
		if (automatic) {
			return speedCommand;
		} else {
			return speedRequest;
		}
		
	}
	
	/**
	 * Getter for current speed.
	 * @return current speed, in m/s
	 */
	public synchronized double getSpeedCurrent() {
		
		return speedCurrent;
		
	}
	
	/**
	 * Setter for current power output.
	 * @param pow - current power, in watts
	 */
	public synchronized void setPower(double pow) {
		
		if (!sBrakeOn && !eBrakeOn) { //only assign power if the brakes are not engaged
			power = pow;
			
			if (rightDoorsOpen || leftDoorsOpen) { //if doors are open, close the doors!
				rightDoorsOpen = false;
				leftDoorsOpen = false;
				if (connectedToUI()) ui.setDoorsDirect(false, false);
			}
		} else
			power = 0;
		
		if (connectedToUI()) ui.setPower(power);
		
	}
	
	/**
	 * Waits for a specified amount of time, then calls a specified method.
	 * (Used for the "approach station" function.)
	 * @author anna
	 *
	 */
	private class WaitThread extends Thread {
		
		private final Side side;
		private final long timer;
		private final int method;
		
		public WaitThread(Side s, long timetowait, int meth) {
			side = s;
			timer = timetowait;
			method = meth;
		}
		
		public WaitThread(long timetowait) {
			side = null;
			timer = timetowait;
			method = 3;
		}
		
		/**
		 * Calls a method (maybe) after a certain amount of time has elapsed.
		 */
		public void run() {
			
			//Sleep for specified time (not perfect)
			try {
				do {
					sleep(timer);
				} while (method == 1 && speedCurrent != 0);
			} catch (InterruptedException e) {
				//irrelevant
			}
			
			if (method == 1)
				openDoors(side);
			else if (method == 2)
				closeDoors(side);
			
		}
		
	}

}
