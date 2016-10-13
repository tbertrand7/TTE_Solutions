package trainModel;

public class TrainModel {
	
	/*Static variables for Train specific stats*/
	static int maxPassengers = 222; 	
		static int maxCrew = 20; //don't know where to find this, "20" is dummy data for now
	static double trainMass = 40900; //in kg         //56.7T or 40.9T (metric)
	static double maxPower = 397629.8; //in J/s (N*m/s)
	static double maxAcc = 0.5; //in m/s^2
	
	boolean rightDoorsOpen; //method made
	boolean leftDoorsOpen; //method made
	boolean lightsOn; //method made
	
	boolean serviceBrakeOn;
	boolean emergencyBrakeOn;
	
	int crewCount; //method made
	int passengerCount; //method made
	int temperature; //method made
	int elevation;
	
	double power; //method made
	double velocity; //method made
	
	
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
		else{ //velocity calculations here or in getVelocity() method???
			velocity = calculateVelocity(power);
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
					
					/*REVISIT!!!!!     physics equations for velocity-power calculations*/
					velocity = power / (trainMass * maxAcc); 
					
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
	
	
	/**
	 * Gets the number of passengers on the train
	 * @return passenger count
	 */
	int getPassengerCount(){
		return passengerCount;
	}
	
	
	/**
	 * Changes the status of the lights (off -> on OR on -> off)
	 */
	void changeLightsStatus(){
		
		if(lightsOn == true){
			lightsOn = false;
		}
		else{
			lightsOn = true;
		}	
	}
	
	/**
	 * Gets the status of the lights
	 * @return true if the lights are on, else false
	 */
	boolean getLightStatus(){
		return lightsOn;
	}
	
	
	/**
	 * Changes the status of the right doors (open -> closed OR closed -> open)
	 */
	void changeRightDoors(){
		
		if(rightDoorsOpen == true){
			rightDoorsOpen = false;
		}
		else{
			rightDoorsOpen = true;
		}
	}
	
	/**
	 * Gets the status of the right doors
	 * @return true if the right doors are open, else false
	 */
	boolean getRightDoorStatus(){
		return rightDoorsOpen;
	}
	
	
	/**
	 * Changes the status of the left doors (open -> closed OR closed -> open)
	 */
	void changeLeftDoors(){
		
		if(leftDoorsOpen == true){
			leftDoorsOpen = false;
		}
		else{
			leftDoorsOpen = true;
		}
	}
	
	/**
	 * Gets the status of the left doors
	 * @return true if the left doors are open, else false
	 */
	boolean getLeftDoorStatus(){
		return leftDoorsOpen;
	}
	
	//service brake
	
	
	//emergency brake
	
	
	/**
	 * Changes the temperature of the train
	 * @param setTemp - the setpoint temperature for the train
	 */
	void setTemperature(int setTemp){
		temperature = setTemp;
	}
	
	
	/**
	 * Gets the current temperature of the train
	 * @return the temperature of the train
	 */
	int getTemperature(){
		return temperature;
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
