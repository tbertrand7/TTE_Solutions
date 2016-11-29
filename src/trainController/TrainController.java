package trainController;

public class TrainController extends TrainControllerInterface {
	
	/**
	 * The TrainControllerInstances class this train controller belongs to.
	 */
	protected TrainControllerInstances parent;
	
	/**
	 * Speed requested by the train controller
	 */
	private double speedRequest;
	

	/**
	 * Assigns id and parent, creates TrainModel, creates PowerCalculators.
	 * @param uniqueid
	 */
	public TrainController(TrainControllerInstances tci, int uniqueid) {
		
		super(uniqueid);
		
		parent = tci;
		ui = null;
		
		speedRequest = 0;
		automatic = true;
		
		//TODO Create new array of PowerCalculators
		
	}
	
	/**
	 * Stops PowerCalculators, and disconnects from its train model and UI.
	 */
	public void delete() {
		
		//TODO Stop all power calculators
		
		//@Matt: This TrainModel method needs to exist so garbage collection can pick up this TrainController:
		//model.disconnect();
		model = null;
		
		ui.disconnect();
		ui = null;
		
	}
	
	/**
	 * Checks whether the train is connected to a UI.
	 * @return true if a UI is connected, false otherwise
	 */
	public boolean connectedToUI() {
	
		return !(ui == null);
		
	}
	
	/**
	 * Connects the train to the specified UI.
	 * @param tcui - the TrainControllerUI to connect to the train
	 */
	public void connectToUI(TrainControllerUI tcui) {
		
		ui = tcui;
		
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
	public void setPower(double pow) {
		
		power = pow;
		
		if (ui != null) ui.setPower(power);
		
	}

}
