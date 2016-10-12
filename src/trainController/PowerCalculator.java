package trainController;

public class PowerCalculator {

	private final trainControllerUI ui;
	
	private final double Kp = 1;
	private final double Ki = 0.2;
	
	public PowerCalculator(trainControllerUI tcui) {
		ui = tcui;
	}
	
	/**
	 * Calculates power output based on current speed and requested speed.
	 * (Should be called on every change of current or requested speed.)
	 */
	public void run() {
		
	}
	
}
