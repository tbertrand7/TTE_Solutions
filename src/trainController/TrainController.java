package trainController;

import trainModel.TrainModel;

public class TrainController {
	
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
	 * Assigns train id and creates a TrainModel, assigns id and parent, creates TrainModel, creates PowerCalculators.
	 */
	public TrainController(TrainControllerInstances tci, int uniqueid, String line) {
		
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
		
		/* @Matt: This TrainModel constructor needs to be added to the TrainModel class! */
		//model = new TrainModel((TrainController)this, id, line);
		
		parent = tci;
		ui = null;
		
		speedRequest = 0;
		automatic = true;
		
		//TODO Create new array of PowerCalculators (for now it's just one)
		pc = new PowerCalculator(this);
		pc.start();
		
		stop = true;
		pc.tempStop(true);
		
	}
	
	/**
	 * Sets commanded speed and authority, and whether the track is underground.
	 * @param speed - speed passed from the wayside controller
	 * @param auth - authority passed from the wayside controller
	 * @param under - true if underground, false otherwise
	 */
	public void passInfo(double speed, double auth, boolean under) {
		
		if (authority > 0) authority -= 1;
		
		if (authority == 0 && auth > 0) {
			
			setStop(false);
			
			sBrakeOn = false;
			if (connectedToUI()) ui.setServiceBrake(false);
			//TODO @Matt model.setServiceBrake(false);
			
		}
		
		speedCommand = speed;
		if (auth >= 0) authority = auth;
		inTunnel = under;
		
		if (authority == 0) {
			setStop(true);
			
			sBrakeOn = true;
			if (connectedToUI()) ui.setServiceBrake(true);
			//TODO @Matt model.setServiceBrake(true);
			
			power = 0;
		}
		
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
			//TODO @Matt model.setServiceBrake(false);
			
		}
		
		if (auth >= 0) authority = auth;
		
		if (authority == 0) {
			
			setStop(true);
			
			sBrakeOn = true;
			if (connectedToUI()) ui.setServiceBrake(true);
			//TODO @Matt model.setServiceBrake(true);
			
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
	 */
	public synchronized void signal() {
		
		if (connectedToUI()) {
			ui.message("ERROR: FAILURE\n");
		}
		
		setStop(true);
		
		eBrakeOn = true;
		if (connectedToUI()) ui.setEmergencyBrake(true);
		//TODO @Matt model.setEmergencyBrake(true);
	}
	
	/**
	 * Signals that an error has been repaired.
	 */
	public synchronized void repair() {
		
		setStop(false);
		
		eBrakeOn = false;
		if (connectedToUI()) ui.setEmergencyBrake(false);
		//TODO @Matt model.setEmergencyBrake(false);
		
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
		//TODO @Matt model.setServiceBrake(true);
		
		if (connectedToUI()) ui.announceStation(name);
		
		WaitThread wt = new WaitThread(doors, 1000, 1);
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
			//TODO @Matt model.setRightDoorsOpen(true);
		} else {
			leftDoorsOpen = true;
			if (connectedToUI()) ui.setDoorsDirect(true, false);
			//TODO @Matt model.setLeftDoorsOpen(true);
		}
		
		WaitThread wt = new WaitThread(doors, 5000, 2);
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
			//TODO @Matt model.setRightDoorsOpen(false);
		} else {
			leftDoorsOpen = false;
			if (connectedToUI()) ui.setDoorsDirect(false, false);
			//TODO @Matt model.setLeftDoorsOpen(false);
		}
		
		sBrakeOn = false;
		if (connectedToUI()) ui.setServiceBrake(false);
		//TODO @Matt model.setServiceBrake(false);
		
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
	 * Sets speed limit.
	 * @param speed - speed limit in m/s
	 */
	public synchronized void setSpeedLimit(double speed) {
		
		speedLimit = speed;
		
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
				//TODO @Matt model.setRightDoorsOpen(open);
			} else {
				leftDoorsOpen = open;
				//TODO @Matt model.setLeftDoorsOpen(open);
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
			
			//TODO @Matt model.setTemperature(temperature);
			
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
			//TODO @Matt model.setServiceBrake(on);
			//TODO @Matt model.setEmergencyBrake(false);
			
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
			//TODO @Matt model.setEmergencyBrake(on);
			//TODO @Matt model.setServiceBrake(false);
			
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
		//TODO @Matt model.changeLightStatus(on);
		
	}
	
	/**
	 * Sets the train's temporarily stopped signal.
	 * @param dontgo - true if the train needs to stop, otherwise false
	 */
	public void setStop(boolean dontgo) {
		
		stop = dontgo;
		
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
		
		//@Matt: This TrainModel method needs to exist so garbage collection can pick up this TrainController:
		//model.disconnect();
		model = null;
		
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
	 * Setter for speed request.
	 * @param speed - speed requested by train controller, in mph
	 */
	public void setSpeedRequest(double speed) {
		
		speedRequest = speed * 1609.34 / 3600;
		
	}
	
	/**
	 * Setter for automatic mode.
	 * @param speed - speed requested by train controller, in mph
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
		
		if (!sBrakeOn && !eBrakeOn)
			power = pow;
		else
			power = 0;
		
		if (connectedToUI()) ui.setPower(power);
		
	}
	
	/**
	 * Waits for a specified amount of time.
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
		
		/**
		 * Calls a method after a certain amount of time has elapsed.
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
			else
				closeDoors(side);
			
		}
		
	}

}
