package trainController;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Set;

public class TrainControllerInstancesOld {
	
	//private HashMap<Integer, TrainControllerUIOld> UIList;	//maps instance IDs to trainControllerUI classes
	//private HashMap<Integer, PowerCalculatorOld> powerList; //maps instance IDs to PowerCalculator classes <-- this should be train IDs, not instance IDs
	private HashMap<Integer, TestPanelOld> testList; //maps instance IDs to TestPanel classes
	
	private int nextID; //this is NOT the next train ID, it's the next instance ID!!!!!
	
	public TrainControllerInstancesOld() {
		nextID = 0;
		//UIList = new HashMap<Integer, TrainControllerUIOld>();
		//powerList = new HashMap<Integer, PowerCalculatorOld>();
		testList = new HashMap<Integer, TestPanelOld>();
	}
	
	public synchronized double getPower(int id) {
		//if (powerList.containsKey(id))
		//	return powerList.get(id).getPower();
		//else
			return -1;
	}
	
	public synchronized void setSpeedLimit(int id, double limit) {
		//if (UIList.containsKey(id))
		//	UIList.get(id).setSpeedLimit(limit);
	}
	
	public synchronized void setSpeedCommand(int id, double command) {
		//if (UIList.containsKey(id))
		//	UIList.get(id).setSpeedCommand(command);
	}
	
	public synchronized void setSpeedCurrent(int id, double speed) {
		//if (UIList.containsKey(id))
		//	UIList.get(id).setSpeedCurrent(speed);
	}
	
	public synchronized double getSpeedCurrent(int id) {
		//if (UIList.containsKey(id))
		//	return UIList.get(id).getSpeedCurrent();
		//else
			return -1;
	}
	
	public synchronized boolean getSBrakeEngaged(int id) {
		//if (UIList.containsKey(id))
		//	return UIList.get(id).sBrakeEngaged();
		//else
			return false;
	}
	
	public synchronized boolean getEBrakeEngaged(int id) {
		//if (UIList.containsKey(id))
		//	return UIList.get(id).eBrakeEngaged();
		//else
			return false;
	}
	
	public synchronized void setInTunnel(int id, boolean dark) {
		//if (UIList.containsKey(id)) {
		//	if (dark) UIList.get(id).inTunnel();
		//	else UIList.get(id).leftTunnel();
		//}
	}
	
	public synchronized void setRightDoors(int id, boolean open) {
		//if (UIList.containsKey(id)) {
		//	UIList.get(id).setRightDoors(open);
		//}
	}
	
	public synchronized void setLeftDoors(int id, boolean open) {
		//if (UIList.containsKey(id)) {
		//	UIList.get(id).setLeftDoors(open);
		//}
	}
	
	public synchronized void setTemp(int id, int temp) {
		//if (UIList.containsKey(id)) {
		//	UIList.get(id).setTemp(temp);
		//}
	}
	
	public synchronized Set<Integer> getKeySet() {
		//return UIList.keySet();
		return null;
	}
	
	private void printKeySet() {
		//for (int key : UIList.keySet()) {
		//	System.out.println(key);
		//}
		System.out.println();
	}
	
	private void createNew(TestPanelOld tp) {//TrainControllerUIOld tcui, TestPanelOld tp) {
		//Put into list
		//UIList.put(nextID, tcui);
		testList.put(nextID, tp);
		
		//Update all test panel train instance lists
		for (int key : testList.keySet())
			testList.get(key).updateTrainInstances();
		
		//Create new power calculator
		//PowerCalculatorOld pc = new PowerCalculatorOld(tcui);
		//powerList.put(nextID, pc);
		
		tp.initialize();
		
		//pc.start();
		
		nextID++;
	}
	
	public void newUI() {
		TrainControllerInstancesOld instance = this;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//TrainControllerUIOld tcui = new TrainControllerUIOld(nextID, instance);
					TestPanelOld tp = new TestPanelOld(nextID, instance);
					
					instance.createNew(tp);//tcui, tp);
					
					tp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public synchronized void removeUI(int id) {
		//UIList.remove(id);
		
		//PowerCalculatorOld pc = powerList.get(id);
		//pc.stopRun();
		//powerList.remove(id);
	}

}
