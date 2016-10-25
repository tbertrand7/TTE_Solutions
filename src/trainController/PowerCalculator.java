package trainController;

public class PowerCalculator extends Thread {

	private final TrainControllerUI ui;
	
	private double Pcmd;
	private final double Kp = 30000;
	private final double Ki = 100;
	private double Ek;
	private double Ek1;
	private double Uk;
	private double Uk1;
	private final double T = 1; //assume the sampling period of the train model is 1 s
	
	private final double Pmax; //max power, calculated from info provided by train model
	
	private int annCounter; //counter for announcement changes
	
	private boolean proceed;
	
	public PowerCalculator(TrainControllerUI tcui) {
		ui = tcui;
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
	
	public synchronized double getPower() {
		return Pcmd;
	}
	
	public void stopRun() {
		proceed = false;
	}
	
	/**
	 * Calculates power output based on current speed and requested speed.
	 */
	public void run() {
		proceed = true;
		annCounter = 0;
		
		while (proceed) {
			long timestart = System.currentTimeMillis();
			
			annCounter++;
			if (annCounter == 5) { //change announcement every 5 secs
				ui.changeAnnouncement();
				annCounter = 0;
			}
			
			double Vreq, Vcur;
			
			if (Uk == -1000 || Uk1 == -1000) {
				Uk1 = Uk;
				Vreq = ui.getSpeedRequest(); Vcur = ui.getSpeedCurrent();
				Ek = Vreq - Vcur;
				if (Uk == 0) Uk = (T/2)*(Ek + Ek1);
				else Uk = 0;
				
				if (Ek > 0 || Vreq != 0) Pcmd = Pmax;
				else Pcmd = 0;
			} else {
				//Calc Ek
				Ek1 = Ek; //update with old value
				Vreq = ui.getSpeedRequest(); Vcur = ui.getSpeedCurrent();
				Ek = Vreq - Vcur;
				
				//Calc Uk
				Uk1 = Uk; //update with old value
				if (Pcmd >= Pmax)
					Uk = Uk1 + (T/2)*(Ek + Ek1);
				else
					Uk = Uk1;
				
				//Calc Pcmd
				Pcmd = Kp*Ek + Ki*Uk;
				if (Pcmd < 0 || Vreq == 0) Pcmd = 0;
				else if (Pcmd > Pmax) Pcmd = Pmax;
			}
			
			if (ui.getSpeedLimit() <= Vcur) Pcmd = 0; //must obey speed limit!
			
			ui.setPower();
			
			/*System.out.println("POWER---");
			System.out.println("Vcmd = " + Vreq + "\t\tVcur = "+Vcur);
			System.out.println("Ek = " + Ek + "\t\tEk1 = "+Ek1);
			System.out.println("Uk = " + Uk + "\t\tUk1 = "+Uk1);
			System.out.println("Power out = " + Pcmd + "\n");*/
			
			//Sleep for one second (not perfect - there's a bit of drift)
			try {
				sleep(1000 - (System.currentTimeMillis()-timestart));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
