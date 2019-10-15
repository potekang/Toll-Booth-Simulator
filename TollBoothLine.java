
public class TollBoothLine {
	final int MAX_TollBoothLength=25;
	private char type;
	private int currentPos;
	private int maxLength;
	Vehicle []v;
	DoneVehicles dv;
	//constructor
	public TollBoothLine(char typ,DoneVehicles dv){
		maxLength=0;
		type = typ;
		this.dv = dv;
		currentPos=0;
		v=new Vehicle[MAX_TollBoothLength];
	}
	//getter and setter
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public int getMaxLength() {
		return maxLength;
	}
	
	public int getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}
	/*public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	//no setMaxLength
	*/
	public Vehicle getVehicle(int num){
		return v[num];
	}
	//other method 
	public boolean isFull(){
		return (currentPos==MAX_TollBoothLength);
	}
	public void calMaxLength(){
		//System.out.println(maxLength);
		if(currentPos>maxLength){
			maxLength=currentPos;
		}
	}
	//need to be modified when finish doneVehicles-done
	//currentPos: the number of valid object
	public void leave(){
		if(currentPos>0){
			dv.add(v[0]);//add it to done vehicles
			for(int i = 0;i<currentPos-1;i++){
				v[i]=v[i+1];
			}
			currentPos--;
		}else {
		}
	}
	public boolean add(Vehicle newVehicles){
		if(isFull()){
			return false;
		}else{
			v[currentPos]=newVehicles;
			currentPos++;
			calMaxLength();
			return true;
		}
		
	}
	
	
}
