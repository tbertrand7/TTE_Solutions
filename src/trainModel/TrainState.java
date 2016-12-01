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
	void changeLights(boolean status){
		lightsOn = status;
	}
	
	
	
	public void setRightDoors(boolean rDoors){
		rightDoorsOpen = rDoors;
	}
	
	
	/**
	 * Changes the status of the left doors (open -> closed OR closed -> open)
	 */
	public void setLeftDoors(boolean lDoors){
		leftDoorsOpen = lDoors;
	}
	
	
	
	/**
	 * Changes status of Service Brakes (on -> off OR off -> on)
	 */
	void serviceBrake(boolean sBrake){ //REVISIT!!
		serviceBrakeOn = sBrake;
	}
	
	
	
	/**
	 * Changes status of Emergency Brakes (on -> off OR off -> on)
	 */
	void eBrake(boolean eBrake){ //REVIST!!!!!!
		emergencyBrakeOn = eBrake;
	}
	
	
	/**
	 * Changes the temperature of the train
	 * @param setTemp - the setpoint temperature for the train
	 */
	void setTemp(int setTemp){	
		temperature = setTemp;
	}
	

}
