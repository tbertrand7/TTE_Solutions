package trainModel;

import trackModel.*; //for track block manipulation
import trackModel.TrackBlock.BlockStatus;
import trainController.*; //for train controller


public class TrainModel extends TrainState implements Runnable{
	
	final double MU = .003;
	
	protected TrackModel tm = new TrackModel();
	TrackBlock trackBlock = new TrackBlock(); //= tm.getBlock("Green", 102); //First block for Demo
	
	int trainID; //unique train ID
	String trainLine;
	
	protected TrainController trainCon;
	public trainModelGUI ui;
	
	double speedLimit;
	double elevation;
	double grade;
	double power; 
	double velocity; 
	double mass = 40900.0;
	
	double normalForce;
	double friction;
	double resistivePower;
	
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
	double tempPos;
	
	int curBlockNum;
	int nextBlockNum;
	
	double accRate;
	
	//TODO: @Matt Fix and add gui as parameter
	public TrainModel(TrainController tc){
		
		
		//DATA FOR SYSTEM PROTOTYPE ONLY
		curBlockNum = 102; //starting on block 102 of green line for demo
		currentPos=0;
		
		trackBlock = tm.getBlock("Green",102);
		nextBlockNum = trackBlock.blockNumber + 1;
		endOfBlock = trackBlock.blockLength * .3048; //convert to meters
		elevation = trackBlock.elevation;
		grade = trackBlock.blockGrade;
		speedLimit = trackBlock.speedLimit;
		
		trainCon = tc;
		trainID = 1;
		trainLine = trackBlock.line;
		
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		lightsOn = false;		
		serviceBrakeOn = false;
		emergencyBrakeOn = false;
		crewCount = 1;
		passengerCount = 0;
		temperature = 68;

		
		power = 0.0;
		resistivePower=0.0;
		velocity = 0.0;		
		
		trackBlock.trainID = trainID;
		trackBlock.status=BlockStatus.OCCUPIED;

		start();
		
		
	}
	
	/**
	 * constructor
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
	}
	
	public void setServiceBrake(boolean sBrake){
		this.serviceBrake(sBrake);
		if(ui != null){
			ui.sBrake(sBrake);
		}
		pause();
		if(sBrake){
			setPower(0);
		}
		else{
			setPower(power);
		}
		accRate = serviceBrakeRate;
		resume();
	}
	
	public void setEmergencyBrake(boolean eBrake){
		this.eBrake(eBrake);
		if(ui != null){
			ui.eBrake(eBrake);
		}
		pause();
		if(eBrake){
			setPower(0);
		}
		else{
			setPower(power);
		}
		setPower(0);
		accRate = emergencyBrakeRate;
		resume();
	}
	
	public void setTemperature (int temp){
		this.setTemp(temp);
		if(ui != null){
			ui.setTemp(temp);
		}
	}
	
	public void setLeftDoorsOpen(boolean lDoors){
		this.setLeftDoors(lDoors);
		if(ui != null){
			ui.lDoors(lDoors);
		}
	}
	
	public void setRightDoorsOpen(boolean rDoors){
		this.setRightDoors(rDoors);
		if(ui != null){
			ui.rDoors(rDoors);
		}
	}
	
	public void changeLightsStatus(boolean lights){
		this.changeLights(lights);
		if(ui != null){
			ui.lights(lights);
		}
	}
	
	public void pause(){
		stop = true;
	}
	
	public void resume(){
		stop = false;
	}
	
	
	public void run(){		
		resume(); //set stop to false to allow while loop to proceed	
		
		time2 = System.currentTimeMillis() / 1000; //initialize time2
		
		while(proceed){
			
			while(stop){ //busy wait here while stop is true 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(currentPos >= endOfBlock){ 
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
				nextBlockNum = trackBlock.blockNumber + 1; //set the next block equal to the nextBlock in trackBlock
				currentPos = 0; //reset current position to zero (start of new block)
				deltaTime = 0; //reset delta time
				endOfBlock = trackBlock.blockLength * .3048; //set the new end of block
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
				
			}
			
				
				//calc new V every 1 second (for now)
	
				if(trainCon != null){
					setPower(trainCon.getPower());
				}
				
				/*
				 * Pass block info to train controller
				 */
				if(trainCon != null){
					trainCon.passInfo(trackBlock.speed, trackBlock.authority, underground); //pass the train controller the new block info
				}
			
				mass = emptyTrainMass + (personMass * passengerCount); //calculate the mass of the train plus load of passengers
				
				normalForce = 9.8 * mass;
				friction = MU * normalForce *-1;
				
				brakingDistance = (velocity * velocity) / (2 * serviceBrakeRate); //calculate braking distance
				
	
				time1 = time2;
				time2 = System.currentTimeMillis()/1000;
				deltaTime = deltaTime + (time2 - time1);
				
				if(power == 0 && velocity == 0 && !serviceBrakeOn && !emergencyBrakeOn){
					accRate = 0;
				}
				else if(power == 0 && velocity != 0 && !serviceBrakeOn && !emergencyBrakeOn){
					accRate = Math.sqrt(Math.abs((resistivePower) / (2 * mass *deltaTime))) * -1;
				}
				else if(!serviceBrakeOn && !emergencyBrakeOn){
					accRate = Math.sqrt( Math.abs((power + resistivePower) / (2 * mass *deltaTime)));
				}

				
				velocity = velocity + (accRate*deltaTime);	
				resistivePower = friction * velocity;
				
				if(velocity<0){
					velocity =0;
				}
				
				if(trainCon != null){
					trainCon.setSpeedCurrent(velocity);
				}
				
				currentPos = currentPos + (velocity * deltaTime) + ( .5 * accRate * deltaTime * deltaTime);				
				
				if(ui != null){
					ui.displayVelocity(velocity);
					ui.displayBlockInfo(curBlockNum, nextBlockNum, elevation, trainLine, speedLimit, temperature);
				}
			
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
				
		}
	}
	
	/**
	 * Used to start a thread for the train model
	 */
	private Thread t;
	public void start () {
		 //System.out.println("Starting train");
		      if (t == null) {
		         t = new Thread (this, "train");
		         t.start ();
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
	 * @param powerSetPoint - power input in watts
	 */
	public void setPower(double powerSetPoint){
			pause();
			if(powerSetPoint > 0){
				power = powerSetPoint;
			}
			else{
				power = 0;
			}
			if(ui != null){
				ui.displayPower();
			}
			resume();
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
