package trainModel;

public class TrainModel extends TrainState {

	int trainID; //unique train ID
	
	double elevation;
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
				serviceBrakeOn = true;   //<< Turn on brakes 
				emergencyBrakeOn = true; //<<
				lightsOn = true; // Turn on lights
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
	 * @param powerSetPoint - power input in KiloWatts
	 * @return true if the power input is valid
	 */
	boolean setPower(double powerSetPoint){
		double tempPower;
		tempPower = powerSetPoint;
		
		if(tempPower > maxPower || tempPower < 0){
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
				 * @param power - the power input for the train in KiloWatts
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
