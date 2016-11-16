package trainController;

public class TrainController extends TrainControllerInterface {
	
	/**
	 * The UI monitoring this train controller
	 */
	private TrainControllerUI ui;

	/**
	 * Assigns id, creates TrainModel, creates PowerCalculators.
	 * @param uniqueid
	 */
	public TrainController(int uniqueid) {
		
		super(uniqueid);
		
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
		
		//TODO Create TrainControllerUI method:
		//ui.disconnect();
		ui = null;
		
	}

}
