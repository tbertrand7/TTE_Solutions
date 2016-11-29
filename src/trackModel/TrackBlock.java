package trackModel;

public class TrackBlock {
	
	public enum BlockStatus {
		UNOCCUPIED, OCCUPIED, CLOSED
	}
	public int pk;
	public String line;
	public String section;
	public int blockNumber;
	public double blockLength;
	public double blockGrade;
	public double speedLimit;
	public String infrastructure;
	public double elevation;
	public double cumualativeElevation;
	public SwitchBlock switchBlock;
	public String arrowDirection;
	public int numPass;
	public int temp;
	public String status;
	public String occupied;
	public int trainID;
	public double speed;
	public int authority;
	
	public TrackBlock(){
		pk = 0;
		line ="";
		section = "";
		blockNumber = 0;
		blockLength = 0;
		blockGrade = 0;
		speedLimit = 0;
		infrastructure = "";
		elevation = 0;
		cumualativeElevation = 0;
		switchBlock = new SwitchBlock();
		arrowDirection = "";
		numPass = 0;
		temp = 0;
		status = "";
		occupied = "";
		trainID=0;
		speed = 0;
		authority = 0;
	}
}
