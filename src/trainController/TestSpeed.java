package trainController;

public class TestSpeed extends Thread {
	
	private final TestPanel parent;
	private boolean proceed;
	
	//All of these variables will be stored in SI units
	private double P;
	private double v;
	private double F;
	private double M;
	private double a;
	private final double T; //sampling period, in s
	
	private final double sBrakeRate;
	private final double eBrakeRate;
	private boolean sBraking;
	private boolean eBraking;
	
	public TestSpeed(TestPanel tp) {
		parent = tp;
		v = parent.getCurrentSpeed();
		M = 50*1000; //train is ~50 metric tons = 1000 kg
		T = 1;
		
		sBrakeRate = 1.2;
		eBrakeRate = 2.73;
		sBraking = false;
		eBraking = false;
	}
	
	public void engageServiceBrake() {
		sBraking = true;
	}
	
	public void engageEmergencyBrake() {
		eBraking = true;
	}
	
	public void disengageBrakes() {
		sBraking = false;
		eBraking = false;
	}
	
	public void stopRun() {
		proceed = false;
	}
	
	public void run() {
		proceed = true;
		
		while (proceed) {
			long timestart = System.currentTimeMillis();
			
			double oldv = v;
			
			parent.checkBrakes();
			if (sBraking) { //service brake engaged
				v = v - sBrakeRate*T;
			} else if (eBraking) { //emergency brake engaged
				v = v - eBrakeRate*T;
			} else { //regular power calculations
				//Calculate new current velocity
				P = parent.getPower();
				
				if (P == 0) F = 0;
				else if (v == 0) F = P/1; //we're gonna pretend we're going ~2 mph
				else F = P/v;
				
				if (F == 0) a = 0;
				else a = F/M;
				
				v = v + a*T;
			}
			
			if (v < 0) v = 0;
			
			//The below statement is for synchronization if the test panel wants to manually set the current speed
			if (parent.getCurrentSpeed() != oldv) v = parent.getCurrentSpeed();
			else parent.setCurrentSpeed(v);
			
			//System.out.println("---SPEED---");
			//System.out.println("New v = "+v);
			//System.out.println("Power out = " + P + "\n");
			
			//Sleep for one second (not perfect - there's a bit of drift)
			try {
				sleep(1000 - (System.currentTimeMillis()-timestart));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
