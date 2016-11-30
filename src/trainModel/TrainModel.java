package trainModel;

import trackModel.*; //for track block manipulation
import trackModel.TrackBlock.BlockStatus;
import trainController.*; //for train controller
import TTEHome.*; //for clock

public class TrainModel extends TrainState implements Runnable{

	Thread t;
	
	TrackModel tm;
	TrackBlock trackBlock;
	
	
	int trainID; //unique train ID
	String trainLine;
	TrainController trainCon;
	
	double elevation;
	double power; 
	double velocity; 
	double mass;
	
	long time1;
	long time2;
	long deltaTime;
	
	/* Look at TrainControllerUI for thread to run in background to update speed and authority
	 * 
	 * Not for System Prototype
	 * */
	boolean underground;
	
	
	boolean stop;
	boolean proceed = true;
		
	double brakingDistance;
	
	double endOfBlock;
	double currentPos;
	
	int nextBlockNum;
	
	
	/**
	 * Null constructor
	 * sets all boolean variables to false and all numerical variables to 0
	 */
	public TrainModel(TrainController tc, int id, String line) {	
		
		trainCon = tc;
		trainID = id;
		trainLine = line;
		
		t.start();
		
		if(trainLine.compareToIgnoreCase("Green") == 0)
		{
			nextBlockNum = 152;
		}
		else
		{
			nextBlockNum = 77;
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
		time2 = System.currentTimeMillis() / 1000; //initialize time1
		
		while(proceed){
			
			//while(stop); //wait here while stop is true 
			
				//calc new V every 1 second (for now)
	
				power = trainCon.getPower();
			
				mass = this.emptyTrainMass + (this.personMass * this.passengerCount); //calculate the mass of the train plus load of passengers
				velocity = power / (mass * this.accRate);
				
				brakingDistance = (velocity * velocity) / (2 * this.serviceBrakeRate); //calculate braking distance
				
				
				time1 = time2;
				time2 = System.currentTimeMillis()/1000;
				deltaTime = time2 - time1;
				
				currentPos = currentPos + velocity * deltaTime + ( .5 * accRate * deltaTime * deltaTime);
				
				
				if(currentPos == endOfBlock){
					//this.pause();
					
					/*
					 *Update the block we're leaving 
					 */
					trackBlock.status = BlockStatus.UNOCCUPIED; //set the block we are leaving to be unoccupied
					trackBlock.trainID = -1; //set the train ID -1					
					tm.setBlock(trackBlock);
				
					/*
					 * Get the next block
					 */
					trackBlock = tm.getBlock(trainLine, nextBlockNum); //get the next trackBlock 
					trackBlock.status = BlockStatus.OCCUPIED; //set the block to be occupied
					trackBlock.trainID = trainID; //set the train ID to the train ID	
					tm.setBlock(trackBlock); //update the Track DB with new trackBlock info
					
					/*
					 * Update position tracking info	
					 */
					nextBlockNum = trackBlock.nextBlock; //set the next block equal to the nextBlock in trackBlock
					currentPos = 0; //reset current position to zero (start of new block)					
					endOfBlock = trackBlock.blockLength; //set the new end of block
					
					if(trackBlock.infrastructure.compareToIgnoreCase("underground") == 0){
						underground = true;
					}
					else{
						underground = false;
					}
					
					
					/*
					 * Pass info to train controller
					 */
					trainCon.passInfo(trackBlock.speed, trackBlock.authority, underground); //pass the train controller the new block info
					trainCon.newBlock(); //communicates that we are in a new block
				}
				
				try {
					t.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
				
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
			this.pause();
			power = powerSetPoint;			
			this.resume();
		}
	
	
}//end of TrainModel class
