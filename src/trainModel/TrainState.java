package trainModel;

public class TrainState {
	
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

}
