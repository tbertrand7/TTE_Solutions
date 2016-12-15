package trainController;

import TTEHome.SystemClock;

public class PowerCalculator extends Thread {
	
	private TrainController controller;
	private PowerChecker pc;
	private int myID;
	
	private double Pcmd;
	private final double Kp = 30000;
	private final double Ki = 100;
	private double Ek;
	private double Ek1;
	private double Uk;
	private double Uk1;
	private final double T = 1; //assume the sampling period of the train model is 1 s
	
	//Not sure if this is needed?? From what I see, the power will never be calculated above the max...
	private final double Pmax; //max power, calculated from info provided by train model
	
	private boolean proceed;
	private boolean stop;
	
	private SystemClock sysClock;
	
	public PowerCalculator(PowerChecker powcheck, SystemClock sys, int id) {
		pc = powcheck;
		sysClock = sys;
		myID = id;
		
		Ek = 0;
		Ek1 = 0;
		Uk = -1000;
		Uk1 = -1000;
		Pcmd = 0;
		
		double M = 50*1000; //train is ~50 metric tons = 1000 kg
		double maxa = 0.5;
		double maxv = 70*((double)1000/(double)3600); //70 km/h * (1 h / 3600 s) * (1000 m / 1 km)
		Pmax = M*maxa*maxv;
	}
	
	/**
	 * Used before the controller this calculator is connected to is disposed.
	 */
	public void stopRun() {
		proceed = false;
	}
	
	/**
	 * Used when the train needs to temporarily stop, and no power calculation is needed.
	 */
	public void tempStop(boolean dontgo) {
		stop = dontgo;
	}
	
	/**
	 * Calculates power output based on current speed and requested speed.
	 */
	public void run() {
		proceed = true;
		
		while (proceed) {
			
			while (stop) {
				pc.setPower(myID, 0); //just in case
				try {
					sleep(1000/sysClock.clock); //busy waiting if train is temporarily stopped
				} catch (InterruptedException e1) {
					//don't need to do anything
				}
			}
			
			long timestart = System.currentTimeMillis();
			
			double Vreq, Vcur;
			
			if (Uk == -1000 || Uk1 == -1000) {
				Uk1 = Uk;
				Vreq = pc.parent.getSpeed(); Vcur = pc.parent.getSpeedCurrent();
				Ek = Vreq - Vcur;
				if (Uk == 0) Uk = (T/2)*(Ek + Ek1);
				else Uk = 0;
				
				if (Ek > 0 || Vreq != 0) Pcmd = Pmax;
				else Pcmd = 0;
			} else {
				//Calc Ek
				Ek1 = Ek; //update with old value
				Vreq = pc.parent.getSpeed(); Vcur = pc.parent.getSpeedCurrent();
				Ek = Vreq - Vcur;
				
				//Calc Uk
				Uk1 = Uk; //update with old value
				if (Pcmd >= Pmax)
					Uk = Uk1 + (T/2)*(Ek + Ek1);
				else
					Uk = Uk1;
				
				//Calc Pcmd
				Pcmd = Kp*Ek + Ki*Uk;
				if (Vreq == 0 && Vcur == 0) Pcmd = 0;
			}
			
			pc.setPower(myID, Pcmd);
			
			//Sleep for one second (not perfect - there's a bit of drift)
			try {
				long waittime = 1000 - ((System.currentTimeMillis()*sysClock.clock)-timestart);
				if (waittime > 0) sleep(waittime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		pc = null;
	}

}
