
public class Manual extends Vehicle{
	//variables
	private int wheels;
	//constructor
	public Manual(char type, int arriveTime,int wheel){
		super(type,arriveTime);
		super.setDelay(wheel);
	}
	//Methods
	public int getWheels() {
		//System.out.println(wheels);
		return wheels;
	}
	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public String toString(){
		String toStr =  "Vehicle Type: "+super.vehicleType+
				"Arrive Time: "+super.arriveTime+
				"Delay: "+super.delay;
		return toStr;
	}

}
