package trainModel;

public class TrainSpecs {	

	/**
	 * maximum number of passengers (both seated and standing) times 2 for 2 cars
	 */
	final int maxPassengers = 222 *2; 	
	
	/**
	 * crew of 1
	 */
	final int maxCrew = 1; 
	
	
	/**
	 * Average mass of 1 person 
	 */
	final double personMass = 71.17; // in Kg	
	
	/**
	 * mass of empty train (multiplied by 2 for 2 cars)
	 */
	final double emptyTrainMass = 40900.0 * 2; //in kg         //40.9T (metric) * 2cars
	
	
	/**
	 * dimensions of train
	 */
	final double length = 32.2 * 2; //dimensions in meters
	final double height = 3.42;
	final double width = 2.65;
	
	/**
	 * maximum acceleration rate
	 */
	final double maxAccRate = 0.5; //in m/s^2
	
	/**
	 * maximum power input for the engine
	 */
	final double maxPower = 120000 * 2; //120kW *2 cars
	
	
	/**
	 * deceleration rates for brakes
	 */
	final double serviceBrakeRate = -1.2; //m/s^2
	final double emergencyBrakeRate = -2.73;// m/s^2
	
}
