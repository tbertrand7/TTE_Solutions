package trainModel;

public class TrainState extends TrainSpecs {
	
	boolean rightDoorsOpen = false; 
	boolean leftDoorsOpen = false; 
	boolean lightsOn = false; 
	
	boolean serviceBrakeOn = false;
	boolean emergencyBrakeOn = false;
	
	int crewCount = 1; 
	int passengerCount = 0; 
	int temperature = 70; 
	
		
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
	int changePassengerCount(int delta){
		
		int tempCount = passengerCount + delta;	
		
		if(tempCount > maxPassengers){
			int extraPassengers = tempCount - maxPassengers;
			passengerCount = maxPassengers;
			return extraPassengers;
		}
		else if(tempCount < 0){
			passengerCount = 0;
			return 0;
		}
		else{
			passengerCount = passengerCount + delta;
			return 0;
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
	void serviceBrake(boolean sBrake){ 
		serviceBrakeOn = sBrake;
	}
	
	
	
	/**
	 * Changes status of Emergency Brakes (on -> off OR off -> on)
	 */
	void eBrake(boolean eBrake){
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
