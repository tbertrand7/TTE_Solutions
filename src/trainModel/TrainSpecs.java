package trainModel;

public class TrainSpecs {	
	/*Static variables for Train specific stats*/
	final int maxPassengers = 222; 	
	final int maxCrew = 20; //don't know where to find this, "20" is dummy data for now
	final double personMass = 71.17; // in Kg	
	final double maxPower = 480000; //in W
	final double maxAcc = 0.5; //in m/s^2
	final double emptyTrainMass = 40900.0; //in kg         //56.7T or 40.9T (metric)
	double trainMass; 
	
	public TrainSpecs(){
		
	}

}
