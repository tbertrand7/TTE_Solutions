package trainModel;

public class TrainModel {
	
	/*Static variables for Train specific stats*/
	static int maxPassengers;
	static int maxCrew;
	static double trainMass;
	static double maxPower;
	
	boolean rightDoorsOpen;
	boolean leftDoorsOpen;
	boolean lightsOn;
	
	boolean serviceBrakeOn;
	boolean emergencyBrakeOn;
	
	int crewCount;
	int passengerCount;
	int temperature;
	int elevation;
	
	double power;
	double velocity;
	
	
	/**
	 * Null constructor
	 * sets all boolean variables to false and all numerical variables to 0
	 */
	public TrainModel() {		
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		lightsOn = false;
		
		serviceBrakeOn = false;
		emergencyBrakeOn = false;
		
		crewCount = 0;
		passengerCount = 0;
		temperature = 0;
		elevation = 0;
		
		power = 0.0;
		velocity = 0.0;	
	}
	
	/**
	 * Gets the velocity of the train
	 * @return velocity of the train
	 */
	double getVelocity(){
		return velocity;
	}
	
	
	/**
	 * Sets the power of the train based on a power input
	 * Calculates the velocity given the power and sets velocity of train
	 * @param powerSetPoint - power input
	 * @return true if the power input is valid
	 */
	boolean setPower(double powerSetPoint){
		double tempPower;
		tempPower = powerSetPoint;
		
		if(tempPower > maxPower || tempPower < 0){
			return false;
		}
		else{
			velocity = calculateVelocity(power);
			return true;
		}
	}
	
		/**
		 * Calculates the velocity of the train given a power input
		 * @param power - the power input for the train
		 * @return the velocity of the train
		 */
		private double calculateVelocity (double power){
			
			/*Physics equations for velocity-power calculations here*/
			
			return velocity;
		}
			
	/**
	 * Allows for the number of passengers to be changed
	 * @param delta - the number of passengers entering/exiting the train
	 * @return true if passenger count change is valie
	 */
	boolean changePassengerCount(int delta){
		
		int tempCount = passengerCount;
		tempCount = tempCount + delta;
		
		if(tempCount > maxPassengers || tempCount < 0){
			return false;
		}
		else{
			passengerCount = passengerCount + delta;
			return true;
		}
	}

	
}//end of TrainModel class
