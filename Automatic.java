public class Automatic extends Vehicle{
	//variables
	//constructor
	public Automatic(char type, int arriveTime){
		super(type,arriveTime);
		super.setDelay(1);
	}
	//Methods
	public String toString(){
		String toStr=  "Vehicle Type: "+super.vehicleType+
				"Arrive Time: "+super.arriveTime+
				"Delay: "+super.delay;
		return toStr;
	}

}
