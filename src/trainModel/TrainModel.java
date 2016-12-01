package trainModel;

import trackModel.*; //for track block manipulation
import trackModel.TrackBlock.BlockStatus;
import trainController.*; //for train controller


public class TrainModel extends TrainState implements Runnable{

	Thread t;
	
	TrackModel tm = new TrackModel();
	TrackBlock trackBlock;
	
	int trainID; //unique train ID
	String trainLine;
	protected TrainController trainCon;
	
	protected trainModelGUI ui;
	
	double speedLimit;
	double elevation;
	double grade;
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
	
	int curBlockNum;
	int nextBlockNum;
	
	double accRate;
	
	
	/**
	 * Null constructor
	 * sets all boolean variables to false and all numerical variables to 0
	 */
	public TrainModel(TrainController tc, int id, String line) {	
		
		trainCon = tc;
		trainID = id;
		trainLine = line;
		
		if(trainLine.compareToIgnoreCase("Green") == 0)
		{
			nextBlockNum = 152;
		}
		else
		{
			nextBlockNum = 77;
		}
		
		endOfBlock = 0;
		currentPos = 0;
		
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
		
		t.start();
				
	}
	
	public void setServiceBrake(boolean sBrake){
		this.serviceBrake(sBrake);
		ui.sBrake(sBrake);
	}
	
	public void setEmergencyBrake(boolean eBrake){
		this.eBrake(eBrake);
		ui.eBrake(eBrake);
	}
	
	public void setTemperature (int temp){
		this.setTemp(temp);
		ui.setTemp(temp);
	}
	
	public void setLeftDoorsOpen(boolean lDoors){
		this.setLeftDoors(lDoors);
		ui.lDoors(lDoors);
	}
	
	public void setRightDoorsOpen(boolean rDoors){
		this.setRightDoors(rDoors);
		ui.rDoors(rDoors);
	}
	
	public void changeLightsStatus(boolean lights){
		this.changeLights(lights);
		ui.lights(lights);
	}
	
	public void pause(){
		stop = true;
	}
	
	public void resume(){
		stop = false;
	}
	
	
	public void run(){
		
		this.resume(); //set stop to false to allow while loop to proceed
		time2 = System.currentTimeMillis() / 1000; //initialize time2
		
		while(proceed){
			
			while(stop){ //busy wait here while stop is true 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(currentPos == endOfBlock){ 
			//end of block reached by train	
				
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
				curBlockNum = nextBlockNum;
				nextBlockNum = trackBlock.nextBlock; //set the next block equal to the nextBlock in trackBlock
				currentPos = 0; //reset current position to zero (start of new block)					
				endOfBlock = trackBlock.blockLength; //set the new end of block
				elevation = trackBlock.elevation;
				grade = trackBlock.blockGrade;
				speedLimit = trackBlock.speedLimit;
				trainLine = trackBlock.line;
				
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
			}
			
			
			
				//calc new V every 1 second (for now)
	
				power = trainCon.getPower();
			
				mass = this.emptyTrainMass + (this.personMass * this.passengerCount); //calculate the mass of the train plus load of passengers
				
				brakingDistance = (velocity * velocity) / (2 * this.serviceBrakeRate); //calculate braking distance
				
	
				time1 = time2;
				time2 = System.currentTimeMillis()/1000;
				deltaTime = time2 - time1;
				
				
				velocity = Math.sqrt((2 * power * deltaTime) / mass);
				accRate = velocity / deltaTime;
				
				currentPos = currentPos + velocity * deltaTime + ( .5 * accRate * deltaTime * deltaTime);
				
				try {
					Thread.sleep(1000);
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
				setEmergencyBrake(true);
				changeLightsStatus(true);
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
	
	
	/**
	 * Connects the train to the specified UI and initialize its display.
	 * @param tcui - the TrainControllerUI to connect to the train
	 */
	public void connectToUI(trainModelGUI gui) {
		
		ui = gui;	
		
		ui.sBrake(serviceBrakeOn);
		ui.eBrake(emergencyBrakeOn);
		ui.lights(lightsOn);
		ui.rDoors(rightDoorsOpen);
		ui.lDoors(leftDoorsOpen);
		ui.setTemp(temperature);	
	}
	
	/**
	 * Disconnects the train from its UI.
	 */
	public void disconnectFromUI() {
		
		ui = null;
		
	}	
	
	/**
	 * Checks whether the train is connected to a UI.
	 * @return true if a UI is connected, false otherwise
	 */
	public synchronized boolean connectedToUI() {
	
		return !(ui == null);
		
	}
	
	
	
}//end of TrainModel class
