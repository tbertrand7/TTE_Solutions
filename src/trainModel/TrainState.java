package trainModel;

public class TrainState extends TrainSpecs {
	
	boolean rightDoorsOpen; 
	boolean leftDoorsOpen; 
	boolean lightsOn; 
	
	boolean serviceBrakeOn;
	boolean emergencyBrakeOn;
	
	int crewCount; 
	int passengerCount; 
	int temperature; 
		
	public TrainState(){
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		lightsOn = false;
		serviceBrakeOn = false;
		emergencyBrakeOn = false;
		crewCount = 1;
		passengerCount = 0;
		temperature = 70; //*F	
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
		
		//acceptable range of temps 65-75 *F
		if(setTemp>65 && setTemp<75){
			temperature = setTemp;
		}
	}
	
	
	/**
	 * Gets the current temperature of the train
	 * @return the temperature of the train
	 */
	int getTemperature(){
		return temperature;
	}

}
