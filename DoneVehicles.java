import java.util.ArrayList;

public class DoneVehicles {
	private char type;
	private ArrayList<Vehicle> dv;
	//constructor
	public DoneVehicles(char type){
		this.type = type;
		dv = new ArrayList();
	}
	//getter and setter
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	//other methods
	public void add(Vehicle v){
		dv.add(v);
	}
	//statistic methods
	//public void MaxWaitTime
	public int MaxWaitTime(){
		int max = dv.get(0).getWaitTime();
		for(int i=1;i<dv.size();i++){
			//System.out.println(dv.get(i).getBoothTime());
			//System.out.println(dv.get(i).getWaitTime());
			if(max<dv.get(i).getWaitTime()){
				max = dv.get(i).getWaitTime();
			}
		}
		return max;
	}
	public double AveWaitTime(){
		double num = dv.size();
		double sum = 0;
		for(int i=0;i<num;i++){
			sum+=dv.get(i).getWaitTime();
			//System.out.println(dv.get(i).getWaitTime());
		}
		//System.out.println(dv.size());
		return sum/num;
	}
}
