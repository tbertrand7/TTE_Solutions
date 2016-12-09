package trackModel;

public class TrackBlock {
	
	public enum BlockStatus {
		UNOCCUPIED, OCCUPIED, CLOSED, BROKENRAIL, CUTRAIL, BROKENCIRCUIT
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
	public BlockStatus status;
	public String occupied; //why is this here? <3 Matt
	public int trainID;
	public double speed;
	public int authority;
	public int nextBlock;
	
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
		status = BlockStatus.UNOCCUPIED;
		occupied = "";
		trainID=0;
		speed = 0;
		authority = 0;
		nextBlock = 0;
	}

	public String toString()
	{
		String rtnStr = "Block " + blockNumber;

		if (!infrastructure.equals("")) {
			String[] infr = infrastructure.split(";");

			for (int i = 0; i < infr.length; i++) {
				infr[i] = infr[i].trim();

				if (infr[i].equals("STATION")) {
					if (i + 1 < infr.length)
						rtnStr = (infr[i + 1].trim()) + " (" + blockNumber + ")";
				}
			}
		}
		return rtnStr;
	}
	
	//This is kind of ugly but it is 100% functional
	//There were issues with using the Object.equals so this was created
	public boolean equals(TrackBlock temp){
		
		return (this.arrowDirection.equals(temp.arrowDirection) && this.authority == temp.authority && this.blockGrade == temp.blockGrade && this.blockLength == temp.blockLength
				&& this.blockNumber == temp.blockNumber && this.cumualativeElevation == temp.cumualativeElevation && this.elevation == temp.elevation && this.infrastructure.equals(temp.infrastructure)
				&& this.line.equals(temp.line) && this.nextBlock == temp.nextBlock && this.numPass == temp.numPass && this.occupied.equals(temp.occupied)
				&& this.pk == temp.pk && this.section.equals(temp.section) && this.speed == temp.speed && this.speedLimit == temp.speedLimit && this.temp == temp.temp && this.trainID == temp.trainID
				&& this.status.toString().equals(temp.status.toString()) && this.switchBlock.id.equals(temp.switchBlock.id) && this.switchBlock.position.equals(temp.switchBlock.position));
		
	}
}
