package trainModel;

public class TrainSpecs {	
	/*Final variables for Train specific specifications*/
	
	
	final int maxPassengers = 444; 	
	
			final int maxCrew = 1; 
	
	final double personMass = 71.17; // in Kg	
	final double emptyTrainMass = 40900.0 * 2; //in kg         //40.9T (metric) * 2cars
	
	final double length = 32.2 * 2; //dimensions in meters
	final double height = 3.42;
	final double width = 2.65;
	
	final double maxAccRate = 0.5; //in m/s^2
	
	final double maxPower = 120000 * 2; //120kW *2 cars
	
	final double serviceBrakeRate = -1.2; //m/s^2
	final double emergencyBrakeRate = -2.73;// m/s^2
	
}
