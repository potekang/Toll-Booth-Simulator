//CS116 Final Project group 6
//All the necessary test program is commented in the classes

import java.io.*;
import java.util.*;
public class Simulator {
	public static void main(String[]args)throws IOException{
		Scanner scan = new Scanner(System.in);
		//default file, will change it by user
		File ifile = new File("rushhour.txt");
		Scanner read = new Scanner(ifile);
		
		final int MAX_SECOND=8000;
		int  M_num=4;
		int  A_num=2;
		int drop = 0;
		ArrayList<Automatic> A_waitList = new ArrayList();
		ArrayList<Manual> M_waitList=new ArrayList();
		TollBoothLine [] line=new TollBoothLine[6];
		DoneVehicles M_done = new DoneVehicles('M');
		DoneVehicles A_done = new DoneVehicles('A');
				//input file and Toll Booth type
		boolean InputLoopLogic = false;
		do{
			try{
				System.out.print("Input File: ");
				String fileName = scan.nextLine();
				ifile = new File(fileName);
				read = new Scanner(ifile);
				InputLoopLogic = false;
			}catch(IOException ioe){
				System.out.println("Can not find the file!");
				System.out.println("You can try \"rushhour.txt\" or \"nonrushhour.txt\".");
				InputLoopLogic = true;
			}
		}while(InputLoopLogic);
		boolean InputNumLogic = false;
		
		do{
			try{
				System.out.print("Manual Toll Booths: ");
				 M_num = scan.nextInt();
				System.out.print("Automatic Toll Booths: ");
				 A_num = scan.nextInt();
				if(M_num<1||A_num<1||M_num+A_num!=6){
					InputNumLogic = true;
					System.out.println("Wrong Input!");
					System.out.println("The two natural numbers should equal to 6 when summed up.");
					
				}else{
					InputNumLogic = false;
				}
			}catch(InputMismatchException ims){
				System.out.println("Wrong Input!");
				String garbage = scan.nextLine();
				System.out.println("Please input two natural numbers which is equal to 6 when summed up.");
				InputNumLogic = true;
			}
			
		}while(InputNumLogic);
		
			//Initialize 6 TollBoothLine
		for(int i=0 ; i<M_num ; i++){
			line[i] = new TollBoothLine('M',M_done);
		}
		for(int i=M_num ; i<M_num+A_num ; i++){
			line[i] = new TollBoothLine('A',A_done);
		}
		
		
		//------------------------stimulation---------------------------------
		//s stand for second
		//read the first arrive time
		String lineRead = read.nextLine();
		String sub1 = lineRead.substring(0, lineRead.indexOf(","));
		String sub2 = lineRead.substring(lineRead.indexOf(",")+1, lineRead.length()-sub1.length()+1);
		int nextArriveTime = 1;
		char type = 'A';
		int wheel = 1;
		for(int s=0 ; s<MAX_SECOND ; s++){
			
			//add the vehicle in this second to the wait list
			try{
			while(nextArriveTime==s){
				type = sub2.charAt(0);
				//check the 'M' or 'A'
				switch(type){
				case 'M': 
					wheel = Integer.parseInt(sub2.substring(2));
					Manual temM = new Manual('M',nextArriveTime,wheel);
					M_waitList.add(temM);
					//System.out.println("M_size: "+M_waitList.size());
					break;
				case 'A':
					Automatic temA = new Automatic('A',nextArriveTime);
					A_waitList.add(temA);
					//System.out.println("A_size: "+A_waitList.size());
					break;
				default:
					break;
				}
				
			//start working on next line 
				lineRead = read.nextLine();
			//	System.out.println("lineRead: "+lineRead);
				sub1 = lineRead.substring(0, lineRead.indexOf(","));
			//	System.out.println("sub1: "+sub1);
				sub2 = lineRead.substring(lineRead.indexOf(",")+1);
			//	System.out.println("sub2: "+sub2);
				nextArriveTime = Integer.parseInt(sub1);
			//	System.out.println("arriveTime: "+arriveTime);
			}
		}catch(NoSuchElementException nse){
			//just for end of file
		}
		
			//if it's time leave the TollBoothLine/ set the BoothTime
			//calculate the TollBoothLine from
		for(int i=0;i<M_num+A_num;i++){
			//s is equal to booth time + delay
			try{
				if(s ==  (line[i].getVehicle(0).getCheckTime()+line[i].getVehicle(0).getDelay())  ){
				//	System.out.println(i);
				//	System.out.println("s:"+s);
					line[i].leave();
					try{
						line[i].getVehicle(0).setCheckTime(s);
					}catch(NullPointerException npe){
						
					}
				}
			}catch(NullPointerException npe){
			}
		}
			
			
		//check all the vehicle in the M&A waiting list and put it into shortest tollBoothLine
		for(int i=0;i<M_waitList.size();i++){
			TollBoothLine shortestM = shortestMLength(line, M_num);
			if(shortestM.add(M_waitList.get(0))){
					shortestM.getVehicle(0).setBoothTime(s);
					//System.out.println(shortestM.getVehicle(0).getBoothTime());
					if(shortestM.getCurrentPos()==1){
						shortestM.getVehicle(0).setCheckTime(s);;
					}
					try{
						M_waitList.remove(0);
					}catch(IndexOutOfBoundsException iob){
					}
			}else{
				M_waitList.remove(0);
				drop++;
			}
		}
		
		for(int i=0;i<A_waitList.size();i++){
			TollBoothLine shortestA = shortestALength(line, M_num);
		//	System.out.println("Shortest Length: "+ shortest.getCurrentPos());
			if(shortestA.add(A_waitList.get(0))){
				if(shortestA.getCurrentPos()==1){
					shortestA.getVehicle(0).setCheckTime(s);;
				}
				shortestA.getVehicle(0).setBoothTime(s);
				try{
					A_waitList.remove(0);
				}catch(IndexOutOfBoundsException iob){
					
				}
			}else{
				A_waitList.remove(0);
				drop++;
			}
		}
		//System.out.println(line[test].getCurrentPos());
		//System.out.println(line[test].getMaxLength());
		}	
		
		//Get statistic data
		System.out.println(drop+" vehicles dropped");
		for(int i=0;i<6;i++){
			switch(line[i].getType()){
				case 'M':
					System.out.println("Manual Line #"+(i+1)+" Maximum Length="+line[i].getMaxLength());
					break;
				case 'A':
					System.out.println("Automatic Line #"+(i+1)+" Maximum Length="+line[i].getMaxLength());
					break;
					
			}
		}
		System.out.println("Max Manual Wait = "+M_done.MaxWaitTime());
		System.out.println("Max Automatic Wait = "+A_done.MaxWaitTime());
		System.out.println("Avg Manual Wait = "+M_done.AveWaitTime());
		System.out.println("Avg Automatic Wait = "+A_done.AveWaitTime());
		
	}
	
	
	
	//-------------------------Get Shortest Length Method--------------------
	public static TollBoothLine shortestMLength(TollBoothLine []line,int M_num){
		TollBoothLine shortest = line[0];
		for(int i=0;i<M_num;i++){
			if(shortest.getCurrentPos()>line[i].getCurrentPos()){
				shortest = line[i];
			}
		}
		return shortest;
	}
	public static TollBoothLine shortestALength(TollBoothLine []line,int M_num){
		TollBoothLine shortest = line[M_num];
		for(int i=M_num;i<line.length;i++){
			if(shortest.getCurrentPos()>line[i].getCurrentPos()){
				shortest = line[i];
			}
		}
		return shortest;
	}
}

