package trackModel;

public class TrackBlock {
	public int pk;
	public String line;
	public String section;
	public int blockNumber;
	public double blockLength;
	public double blockGrade;
	public double speedLimit;
	public String infrastructure;
	public double elevation;
	public double cumalativeElevation;
	public String switchBlock;
	public int switchPosition;
	public String arrowDirection;
	public int numPass;
	public int temp;
	public String status;
	public String occupiedBy;
	
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
		cumalativeElevation = 0;;
		switchBlock = "";
		switchPosition = 0;
		arrowDirection = "";
		numPass = 0;
		temp = 0;
		status = "";
		occupiedBy = "";
	}
}
