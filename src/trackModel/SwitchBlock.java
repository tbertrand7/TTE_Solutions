package trackModel;

public class SwitchBlock {
	public String position;
	public String id;
	
	public SwitchBlock(){
		position = "";
		id = "";
	}
	
	public String getPosition(){
		return position;
	}
	
	public String getID(){
		return id;
	}
	
	public void setPosition(String newPosition){
		position = newPosition;
	}
}
