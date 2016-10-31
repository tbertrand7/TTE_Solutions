package trainModel;

public class TrainModel {
	
	TrainState trainState = new TrainState();
	TrainSpecs trainSpecs = new TrainSpecs();
	Train trainDynamics = new Train();
	
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
		trainDynamics.elevation = 0;
		
		trainDynamics.power = 0.0;
		trainDynamics.velocity = 0.0;	
	}
	
	
	/**
	 * calls private method for failure protocol
	 */
	public void initFailureProto(){
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
					velocity = power / (trainMass * maxAcc); ??????????
					
					*/
					
					velocity = .625 * power;
					
					return velocity;
				}
		
			
	/**
	 * Allows for the number of passengers to be changed
	 * @param delta - the number of passengers entering/exiting the train
	 * @return true if passenger count change is valid
	 */
	boolean changePassengerCount(int delta){
		
		int tempCount = passengerCount;
		tempCount = tempCount + delta;
		passengerCount = tempCount;
		
		trainMass =(passengerCount * personMass) + emptyTrainMass;
		
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
	 * Gets the number of crew members on the train
	 * @return crew count
	 */
	int getCrewCount(){
		return crewCount;
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
		
		if(rightDoorsOpen){
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
		
		if(leftDoorsOpen){
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
	
	
	/**
	 * Changes status of Service Brakes (on -> off OR off -> on)
	 */
	void serviceBrake(){
		if(serviceBrakeOn){
			serviceBrakeOn = false;
		}
		else{
			serviceBrakeOn = true;
		}
	}
	
	
	/**
	 * Get the status of the Service Brakes
	 * @return true if service brakes are on
	 */
	boolean getServiceBrakeStatus(){
		return serviceBrakeOn;
	}
	
	
	/**
	 * Changes status of Emergency Brakes (on -> off OR off -> on)
	 */
	void emergencyBrake(){
		if(emergencyBrakeOn){
			emergencyBrakeOn = false;
		}
		else{
			emergencyBrakeOn = true;
		}
	}
	
	/**
	 * Get the status of the Emergency Brakes
	 * @return true if the emergency brakes are on
	 */
	boolean getEmergencyBrakeStatus(){
		return emergencyBrakeOn;
	}
	
	
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
