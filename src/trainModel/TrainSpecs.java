package trainModel;

public class TrainSpecs {	
	/*Final variables for Train specific specifications*/
	
	
	final int maxPassengers = 222; 	
	
			final int maxCrew = 5; //don't know where to find this, "5" is dummy data for now
	
	final double personMass = 71.17; // in Kg	
	final double emptyTrainMass = 40900.0; //in kg         //40.9T (metric)
	
	final double length = 32.2; //dimensions in meters
	final double height = 3.42;
	final double width = 2.65;
	
	final double maxAccRate = 0.5; //in m/s^2
	
	final double serviceBrakeRate = -1.2; //m/s^2
	final double emergencyBrakeRate = -2.73;// m/s^2
	
}
