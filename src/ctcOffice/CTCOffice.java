package ctcOffice;

public class CTCOffice
{
	public enum Mode {MAUNAL, AUTOMATIC};
	
	private int simulationSpeed;
	private Mode mode = Mode.MAUNAL; //1=manual 2=auto	
	
	public void setMode(Mode newMode)
	{
		mode = newMode;
	}
	
	/** Set new simulation speed */
	public void setSimulationSpeed(int newSpeed)
	{
		simulationSpeed = newSpeed;
	}
	
	/** Suggest speed to wayside controller */
	public void suggestSpeed(int newTrainSpeed)
	{
		//TODO: also need to send train info
	}
	
	/** Returns the current simulation speed */
	public int getSimulationSpeed()
	{
		return simulationSpeed;
	}
	
	/** Returns if system is in Manual or Auto mode */
	public Mode getMode()
	{
		return mode;
	}
}
