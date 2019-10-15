public abstract class Vehicle
{
    // instance variables
	protected char vehicleType;
    protected int arriveTime;
    protected int boothTime;
    protected int checkTime;
    protected int delay;
    protected int waitTime;
    // default constructor
    public Vehicle(){
    	setVehicleType('A');
    	setArriveTime(1);
    }
    // non-default constructor
    public Vehicle(char type, int arrTime){
    	setVehicleType(type);
    	setArriveTime(arrTime);
    
    }
    
    // accessor & mutator methods
    public char getVehicleType() {
        return vehicleType;
    }
    
    public int getWaitTime() {
		return waitTime;
	}
	
	public int getArriveTime() {
        return arriveTime;
    }
    
    public int getBoothTime() {
        return boothTime;
    }
    
    public int getLeaveTime() {
        return checkTime;
    }
    
    public int getDelay() {
        return delay;
    }
    
    public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public void setVehicleType(char newVehicleType) {
        vehicleType = newVehicleType;
    }
    
    public void setArriveTime(int newArriveTime) {
        arriveTime = newArriveTime;
    }
    
    public void setBoothTime(int s) {
    	waitTime = s-arriveTime;
    //	System.out.println("waitTime "+waitTime);
    //	System.out.println("s"+s);
        boothTime = s;
    }
    
    public void setLeaveTime(int checkTime) {
    	this.checkTime = checkTime;
    }
    
    public void setDelay(int newDelay) {
        delay = newDelay;
    }  
    
    public abstract String toString();

}