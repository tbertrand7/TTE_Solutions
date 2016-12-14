package trainModel;

import trackModel.*; //for track block manipulation
import trackModel.TrackBlock.BlockStatus;
import trainController.*; //for train controller
import trainController.TrainController.Signal;
import java.util.*;

import TTEHome.SystemClock;

public class TrainModel extends TrainState implements Runnable{
	
	/**
	 * Clock factor
	 */
	SystemClock clockFactor;
	
	/**
	 * coefficient of friction for steel wheels on steel rails
	 */
	final double MU = .003;
	
	/**
	 * gravitational constant
	 */
	final double G = -9.8;
	
	/**
	 * TrackModel object to get new track blocks
	 */
	protected TrackModel tm = new TrackModel();
	
	/**
	 * TrackBlock object to read in new track blocks to
	 */
	TrackBlock trackBlock = new TrackBlock(); //= tm.getBlock("Green", 102); //First block for Demo
	
	/**
	 * unique train info
	 */
	int trainID; //unique train ID
	String trainLine;
	
	/**
	 * TrainController associated with train model
	 */
	protected TrainController trainCon;
	
	/**
	 * GUI associated with train Model
	 */
	public TrainModelGUI ui;
	
	/**
	 * Info from trackBlock
	 */
	double speedLimit;
	double elevation;
	double grade; 
	double endOfBlock;
	int curBlockNum;
	int nextBlockNum;	
	
	/**
	 * info for velocity calculation 
	 */
	double power;
	long time1;
	long time2;
	long deltaTime;
	double velocity; 
	double currentPos;
	double tempPos;
	double accRate;
	double tempAcc;	
	double normalForce;
	double friction;
	double resistivePower;
	double mass;

	
	/* Look at TrainControllerUI for thread to run in background to update speed and authority
	 * 
	 * Not for System Prototype
	 * */
	
	/**
	 * true if underground
	 */
	boolean underground;
	
	/**
	 * true if entering new block
	 */
	boolean newBlock;
	
	/**
	 * variables for thread pause/resume
	 */
	boolean stop;
	boolean proceed = true;
		
	/**
	 * for calculating stopping distance
	 */
	double brakingDistance;
	double distanceLeftInBlock;

	
	/**
	 * Failure status variables
	 */
	boolean brakeFail = false;
	boolean signalFail = false;
	boolean engineFail = false;
	
	/**
	 * Passengers board/exit variables
	 */

	int passengerPassFail;
	int passengersLeaving;
	boolean passengersAccepted;
	Random rand = new Random();

	
	/**
	 * Constructor for testing purposes
	 */
	public TrainModel(String line, int id){

		trainCon = null;
		trainID = id;
		trainLine = line;
	
		if(trainLine.compareToIgnoreCase("Green") == 0)
		{
			nextBlockNum = 152;
			trackBlock = tm.getBlock(trainLine, nextBlockNum);
		}
		else
		{
			nextBlockNum = 77;
			trackBlock = tm.getBlock(trainLine, nextBlockNum);
		}
	
		
		currentPos=0;
		
		endOfBlock = trackBlock.blockLength * .3048; //convert to meters
		elevation = trackBlock.elevation;
		grade = trackBlock.blockGrade;
		speedLimit = trackBlock.speedLimit;
		
		
		System.out.println("End of Block = "+endOfBlock);
		
		rightDoorsOpen = false;
		leftDoorsOpen = false;
		lightsOn = false;		
		serviceBrakeOn = false;
		emergencyBrakeOn = false;
		crewCount = 1;
		passengerCount = 0;
		temperature = 70;

		
		power = 0.0;
		resistivePower=0.0;
		velocity = 0.0;		
		
		trackBlock.trainID = trainID;
		trackBlock.status=BlockStatus.OCCUPIED;

		tm.setBlock(trackBlock);
		
		
		start();
		
	}
	
	
	
	/**
	 * CONSTRUCTOR
	 * sets all boolean variables to false and all numerical variables to 0
	 * @param modelList - Trains class that contains all active trains
	 * @param tc - train controller associated with Train Model
	 * @param id - unique train id
	 * @param line - the line the train is on (green or red)
	 */
	public TrainModel(Trains modelList, TrainController tc, int id, String line, SystemClock sysClock) {	
			
		trainCon = tc;
		trainID = id;
		trainLine = line;
		clockFactor = sysClock;
		
		//curBlockNum = -1; //initialize train to the YARD (-1)
		
		if(trainLine.compareToIgnoreCase("Green") == 0)
		{
			curBlockNum = 152;
			trackBlock = tm.getBlock(trainLine, curBlockNum);
		}
		else
		{
			curBlockNum = 77;
			trackBlock = tm.getBlock(trainLine, curBlockNum);
		}
		
		currentPos = 0;
		
		nextBlockNum = trackBlock.nextBlock;
		
		endOfBlock = trackBlock.blockLength * .3048; //convert to meters
		elevation = trackBlock.elevation;
		grade = trackBlock.blockGrade;
		speedLimit = trackBlock.speedLimit;		
		
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
		resistivePower = 0.0;
		velocity = 0.0;		
		
		start();
		
	}
	
	/**
	 * adds (or subtracts if negative value) passengers to train
	 * @param numPass - the number of passengers to add (or subtract)
	 */
	public int addPassengers(int numPass){
		if(leftDoorsOpen || rightDoorsOpen){

			return changePassengerCount(numPass);
		}
		else{
			return -1000;
		}
	}

	/**
	 * Sets the status of the service brake
	 * @param sBrake - true if service brake is to be set on
	 */
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
	
	
	/**
	 * Sets the status of the emergency brake
	 * @param eBrake - true if the emergency brake is to be set on
	 */
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
	
	
	/**
	 * Sets the temperature of the train
	 * @param temp - the set point temperature of the train
	 */
	public void setTemperature (int temp){
		this.setTemp(temp);
		if(ui != null){
			ui.setTemp(temp);
		}
	}
	
	
	/**
	 * Opens and closes left doors
	 * @param lDoors - true if left doors open
	 */
	public void setLeftDoorsOpen(boolean lDoors){
		this.setLeftDoors(lDoors);
		if(ui != null){
			ui.lDoors(lDoors);
		}
	}
	
	
	/**
	 * opens and closes right doors
	 * @param rDoors - true if right doors open
	 */
	public void setRightDoorsOpen(boolean rDoors){
		this.setRightDoors(rDoors);
		if(ui != null){
			ui.rDoors(rDoors);
		}
	}
	
	/**
	 * sets lights on and off
	 * @param lights - true if lights on 
	 */
	public void changeLightsStatus(boolean lights){
		this.changeLights(lights);
		if(ui != null){
			ui.lights(lights);
		}
	}
	
	
	/**
	 * sets a brake failure
	 * @param bFail - true if brakes fail
	 */
	public void setBrakeFailure(boolean bFail){
		brakeFail = bFail;
		trainCon.signal(Signal.BRAKE_FAILURE);
	}
	
	
	/**
	 * sets engine failure
	 * @param eFail - true if engine failure
	 */
	public void setEngineFailure(boolean eFail){
		engineFail = eFail;
		trainCon.signal(Signal.ENGINE_FAILURE);
	}
	
	/**
	 * sets signal pickup failure
	 * @param sFail - true if signal pickup failure
	 */
	public void setSignalPickupFailure(boolean sFail){
		signalFail = sFail;
		trainCon.signal(Signal.SIGNAL_PICKUP_FAILURE);
	}
	
	/**
	 * pauses thread
	 */
	public void pause(){
		stop = true;
	}
	
	
	/**
	 * resumes thread
	 */
	public void resume(){
		stop = false;
	}
	
	
	/**
	 * Used to start a thread for the train model
	 */
	private Thread t;
	public void start () {
		      if (t == null) {
		         t = new Thread (this, "train");
		         t.start ();
		      }
		   }
	
	/**
	 * Main thread for velocity calculation
	 */
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
			
			
			//System.out.println("Top of thread");
			
			if(currentPos >= endOfBlock){
			//end of block reached by train	
				
				newBlock = true;
				
				//System.out.println("NewBlock just set to True");
				
				/*
				 *Update the block we're leaving 
				 */		
				if(trackBlock != null){
					trackBlock.status = BlockStatus.UNOCCUPIED; //set the block we are leaving to be unoccupied
					trackBlock.trainID = -1; //set the train ID -1					
					tm.setBlock(trackBlock);		
					
					//System.out.println("Set Track Block we are leaving....");
					
				/*
				 * Get the next block
				 */

					trackBlock = tm.getBlock(trainLine, nextBlockNum); //get the next trackBlock 
					
					//System.out.println("Just got the new block......");
					
					//System.out.println("Track Block Status should equal Unoccupied:     "+trackBlock.status);
					
					trackBlock.status = BlockStatus.OCCUPIED; //set the block to be occupied
					
					
					//System.out.println("Track Block Status should equal occupied:     "+trackBlock.status);

					
					trackBlock.trainID = trainID; //set the train ID to the train ID	
					
					// vvvvvvvvv SQL exception being thrown here vvvvvvvv
					tm.setBlock(trackBlock); //update the Track DB with new trackBlock info	
					
					passengersAccepted = false;
					
					//System.out.println("Set the new block with new info.");
				
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
				}
				
				
				if(trackBlock.infrastructure.contains("UNDERGROUND")){
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
					trainCon.passInfo(trackBlock.speed, trackBlock.authority, underground, trackBlock.infrastructure ,newBlock); //pass the train controller the new block info
				}
				
				newBlock = false;
			
				mass = emptyTrainMass + (personMass * passengerCount); //calculate the mass of the train plus load of passengers
				
				normalForce = 9.8 * mass;
				friction = MU * normalForce *-1;
				
				brakingDistance = (velocity * velocity) / (2 * serviceBrakeRate *-1); //calculate braking distance
				
	
				time1 = time2;
				time2 = System.currentTimeMillis()/1000;
				
				if(clockFactor == null){
					deltaTime =  (deltaTime + (time2 - time1) );

				}
				else{
					deltaTime =  clockFactor.clock * (deltaTime + (time2 - time1) );
				}
				
				//set power level to 0 if engine fails
				if(engineFail) power = 0;
				
				
				
				/*
				 * Acceleration calculation below
				 */
				
					if(power == 0 && velocity == 0 && !serviceBrakeOn && !emergencyBrakeOn){
						if(grade == 0){
							accRate = 0;
						}
						else{
							accRate = G * Math.cos(Math.atan(grade));
						}
					}
					else if(power == 0 && velocity != 0 && !serviceBrakeOn && !emergencyBrakeOn){
						if(grade == 0){
							accRate = Math.sqrt(Math.abs((resistivePower) / (2 * mass *deltaTime))) * -1; //multiply by -1 because resistance is in the opposite direction
						}
						else{
							tempAcc = Math.sqrt(Math.abs((resistivePower) / (2 * mass *deltaTime))) * -1; //multiply by -1 because resistance is in the opposite direction
							accRate = tempAcc + (G * Math.cos(Math.atan(grade)));
						}
					}
					else if(!serviceBrakeOn && !emergencyBrakeOn){
						
						if(grade == 0){
							if(power + resistivePower < 0){
								tempAcc = Math.sqrt( Math.abs((power + resistivePower) / (2 * mass *deltaTime)));
								accRate = tempAcc * -1;
							}
							else{
								accRate = Math.sqrt( Math.abs((power + resistivePower) / (2 * mass *deltaTime)));
							}
						}
						else{
							if(power + resistivePower < 0){
								tempAcc = Math.sqrt( Math.abs((power + resistivePower) / (2 * mass *deltaTime)));
								tempAcc = tempAcc * -1;
								accRate = tempAcc + (G * Math.cos(Math.atan(grade)));
							}
							else{
								tempAcc = Math.sqrt( Math.abs((power + resistivePower) / (2 * mass *deltaTime)));
								accRate = tempAcc + (G * Math.cos(Math.atan(grade)));
							}					
						}
					}//end acc calc

				
				velocity = velocity + (accRate*deltaTime);	
				resistivePower = friction * velocity;
				
				
				if(velocity<0){
					velocity =0;
				}
				
				if(trainCon != null){
					trainCon.setSpeedCurrent(velocity);
				}
				
				currentPos = currentPos + (velocity * deltaTime) + ( .5 * accRate * deltaTime * deltaTime);			
			
				distanceLeftInBlock = endOfBlock - currentPos;
			
				//System.out.println("\nCurrent Position = "+currentPos);
				//System.out.println("Distance left in block = "+distanceLeftInBlock+"\n");
				
				
				//Tell Train controller to brake before the station
				if( (trainCon != null)  &&  (distanceLeftInBlock <= brakingDistance)  &&  (tm.getBlock(trainLine, nextBlockNum).infrastructure.contains("station") ) ){
					trackBlock = tm.getBlock(trainLine, curBlockNum); //pull track block one more time before stopping for station
					trainCon.approachStation();
				}
				

				
				/*
				 *Only allow passengers to enter/exit if the train is stopped at a station and doors are open
				 */
				if(trackBlock.infrastructure.contains("station") && !passengersAccepted && velocity == 0 && (rightDoorsOpen || leftDoorsOpen)){
					
					passengerPassFail = addPassengers(trackBlock.numPass);
					
					if(passengerPassFail != 0  &&  passengerPassFail != -1000 ){
						trackBlock.numPass = passengerPassFail; //leave excess passengers at the station
					}
					else if(passengerPassFail != -1000){
						trackBlock.numPass = 0; //all passengers picked up from the station
					}
					/*
					 * if passengerPassFail == -1000 do nothing, no passengers added/subtracted
					 */
					
					passengersAccepted = true;
					
					
					//random number of passengers get off the train at the station
					passengersLeaving = rand.nextInt(passengerCount);
					trackBlock.numPass = trackBlock.numPass + passengersLeaving;
					tm.setBlock(trackBlock);
					
				}
								
				
				//Display info to UI			
				if(ui != null){
					ui.displayPower(power);
					ui.displayVelocity(velocity);
					ui.displayBlockInfo(curBlockNum, nextBlockNum, elevation, trainLine, speedLimit, temperature, crewCount, passengerCount);
				}
			
				
				//Sleep for a second
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
				setEmergencyBrake(true); //engage eBrake
				changeLightsStatus(true); //turn on lights
			}
	
		
			
	
	/**
	 * Sets the power of the train based on a power input
	 * Calculates the velocity given the power and sets velocity of train
	 * @param powerSetPoint - power input in watts
	 */
	public void setPower(double powerSetPoint){
			pause();
			if(powerSetPoint > maxPower){
				power = maxPower;
			}
			else if(powerSetPoint > 0){
				power = powerSetPoint;
			}
			else{
				power = 0;
			}
			if(ui != null){
				ui.displayPower(power);
			}
			resume();
		}
	
	
	/**
	 * Connects the train to the specified UI and initialize its display.
	 * @param tcui - the TrainControllerUI to connect to the train
	 */
	public void connectToUI(TrainModelGUI gui) {
		
		ui = gui;	
		
		ui.sBrake(serviceBrakeOn);
		ui.eBrake(emergencyBrakeOn);
		ui.lights(lightsOn);
		ui.rDoors(rightDoorsOpen);
		ui.lDoors(leftDoorsOpen);
		
		ui.displayBlockInfo(curBlockNum, nextBlockNum, elevation, trainLine, speedLimit, temperature, crewCount, passengerCount);
		ui.displayPower(power);
		ui.displayVelocity(velocity);
	}
	
	/**
	 * Disconnects the train from its UI.
	 */
	public void disconnectFromUI() {
		if(ui != null){
			ui = null;
		}
	}	
	
	/**
	 * Checks whether the train is connected to a UI.
	 * @return true if a UI is connected, false otherwise
	 */
	public synchronized boolean connectedToUI() {
	
		return !(ui == null);
		
	}
	
	/**
	 * Stops PowerCalculators, and disconnects from its train model and UI.
	 */
	public void delete() {

		pause();
		disconnectFromUI();		
	}
	
	
	
}//end of TrainModel class
