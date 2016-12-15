package trainController;

/**
 * The vital component of the TrainController.
 * @author anna
 *
 */
public class PowerChecker extends Thread {
	
	TrainController parent;
	
	PowerCalculator[] calculators;
	int numCalculators;
	
	double power[];
	
	public void setPower(int id, double pow) {
		
		power[id] = pow;
		
	}
	
	public PowerChecker(TrainController tc, int num) {
		
		parent = tc;
		calculators = new PowerCalculator[num];
		for (int i = 0; i < num; ++i) calculators[i] = new PowerCalculator(this, parent.parent.sysClock, i);
		numCalculators = num;
		
		power = new double[numCalculators];
		for (int i = 0; i < numCalculators; ++i) power[i] = 0;
		
	}
	
	public void tempStop(boolean stop) {
		
		for (int i = 0; i < numCalculators; ++i) calculators[i].tempStop(stop);
		
	}
	
	public void stopRun() {
		
		for (int i = 0; i < numCalculators; ++i) calculators[i].stopRun();
		
	}
	
	public void run() {
		
		for (int i = 0; i < numCalculators; ++i) calculators[i].start();
		
		while (true) {
			
			//Sleep for half a second (not perfect - there's a bit of drift)
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Calculate average
			double sum, avg;
			sum = 0;
			for (int i = 0; i < numCalculators; ++i) sum += power[i];
			avg = sum/numCalculators;
			
			parent.setPower(avg);
		}
		
	}

}
