package trainModel;

public class TrainModel extends TrainState {
	
	TrainState trainState = new TrainState();  //State of train (lights, brakes, etc)
	TrainSpecs trainSpecs = new TrainSpecs();  //info from Train Info Sheet

	double elevation;
	double power; 
	double velocity; 
	
	/**
	 * Null constructor
	 * sets all boolean variables to false and all numerical variables to 0
	 */
	public TrainModel() {		
		trainState.rightDoorsOpen = false;
		trainState.leftDoorsOpen = false;
		trainState.lightsOn = false;
		
		trainState.serviceBrakeOn = false;
		trainState.emergencyBrakeOn = false;
		
		trainState.crewCount = 0;
		trainState.passengerCount = 0;
		trainState.temperature = 0;
		elevation = 0;
		
		power = 0.0;
		velocity = 0.0;	
	}
	
	
	/**
	 * calls private method for failure protocol
	 */
	public void initFailureProtocol(){
		initFailurePrivate();
	}
	
	
			/**
			 * failure protocol
			 */
			private void initFailurePrivate(){
				setPower(0); //set power to zero
				trainState.serviceBrakeOn = true;   //<< Turn on brakes 
				trainState.emergencyBrakeOn = true; //<<
				trainState.lightsOn = true; // Turn on lights
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
		
		if(tempPower > trainSpecs.maxPower || tempPower < 0){
			return false;
		}
		else{ //velocity calculations here or in getVelocity() method???
			velocity = calculateVelocity(powerSetPoint);
			return true;
		}
	}

	
				/**
				 * Private Method
				 * Calculates the velocity of the train given a power input
				 * @param power - the power input for the train
				 * @return the velocity of the train
				 */
				private double calculateVelocity (double power){
					
					/*REVISIT!!!!!     physics equations for velocity-power calculations
					 * 
					velocity = power / (trainMass * maxAcc); 
					*/
					
					velocity = .625 * power;
					
					return velocity;
				}

	
	/*
	 * Elevation method here
	 * 
	 * Not sure how this method should work...
	 * 
	 * Could have getElevation() and setElevation(), but I don't know how that would work in real time
	 * Need to ask for help on this...
	 * 
	 */

	
	
	
}//end of TrainModel class
