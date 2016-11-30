package trainModel;

import trackModel.*; //for track block manipulation
import trainController.*; //for train controller
import TTEHome.*; //for clock

public class TrainModel extends TrainState implements Runnable{

	Thread t;
	
	int trainID; //unique train ID
	String trainLine;
	TrainController trainCon;
	
	double elevation;
	double power; 
	double velocity; 
	double mass;
	
	double speed;
	int authority;
	
	boolean stop;
	boolean proceed = true;
	
	TrackBlock trackBlock;
	
	double brakingDistance;
	
	double endOfBlock;
	double currentPos;
	
	double nextBlock;
	
	
	/**
	 * Null constructor
	 * sets all boolean variables to false and all numerical variables to 0
	 */
	public TrainModel(TrainController tc, int id, String line) {	
		
		trainCon = tc;
		trainID = id;
		trainLine = line;
		
		if(trainLine.compareTo("Green") == 0)
		{
			nextBlock = 152;
		}
		else
		{
			nextBlock = 77;
		}
		
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
	
	
	public void pause(){
		stop = true;
	}
	
	public void resume(){
		stop = false;
	}
	
	
	public void run(){
		
		this.resume(); //set stop to false to allow while loop to proceed
		
		while(proceed){
			
			while(stop); //wait here while stop is true 
			
				
	
				mass = this.emptyTrainMass + (this.personMass * this.passengerCount); //calculate the mass of the train plus load of passengers
				velocity = power / (mass * this.accRate);
				
				brakingDistance = (velocity * velocity) / (2 * this.serviceBrakeRate); //calculate braking distance
				
				
				if(currentPos == endOfBlock){
					this.pause();
					currentPos = 0; //reset current position to zero (start of new block)
				}
				
				
				//switch case for braking??
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
	
	
}//end of TrainModel class
