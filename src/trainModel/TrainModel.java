package trainModel;

public class TrainModel extends TrainState implements Runnable{

	Thread t;
	
	int trainID; //unique train ID
	
	double elevation;
	double power; 
	double velocity; 
	double mass;
	
	private final double accRate = 0.5; // m/s^2
	
	double speed;
	int authority;
	
	
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
		
		crewCount = 1;
		passengerCount = 0;
		temperature = 70;
		elevation = 0;
		
		power = 0.0;
		velocity = 0.0;	
				
	}
	
	
	public void run(){
		
			mass = this.emptyTrainMass + (this.personMass * this.passengerCount); //calculate the mass of the train plus load of passengers
			velocity = power / (mass * accRate);
	
			try {
				t.sleep(1000); //sleep for 1 second
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
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
	public double getVelocity(){
		return velocity;
	}			
			
	
	/**
	 * Sets the power of the train based on a power input
	 * Calculates the velocity given the power and sets velocity of train
	 * @param powerSetPoint - power input in KiloWatts
	 */
	public void setPower(double powerSetPoint){
			power = powerSetPoint;			
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
