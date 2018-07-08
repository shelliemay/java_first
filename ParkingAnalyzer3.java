/*
  松尾彩望
  645131
  ParkingAnalyzer3.java
*/


import java.io.*;
import java.util.ArrayList;
public class ParkingAnalyzer3{

    ArrayList<String> list = new ArrayList<>();
    Stats3 stats1 = new Stats3();
    Stats3 stats2 = new Stats3();
    ArrayList<String> same = new ArrayList<>();
   
    void run(String[] args)throws IOException{
	Integer sameCount =0;
	if(this.move(sameCount, args))return;
	
	for(Integer i = 1; i < args.length; i++){
	    this.ReadMethod(args[i]);
	}
	for(String line: list){
	    String[] data = line.split(",");
	    if(data[3].equals(args[0])){
		this.separate(same,line,sameCount);
		sameCount ++;
	    }
	}
	this.print(same);
    }
    
    boolean move(Integer sameCount, String[] args){
	if(sameCount.equals(args.length)){
	    System.out.printf("最寄駅が指定されてません\n");
	    return true;
	}
	return false;
    }
    
    void separate (ArrayList<String> same,String line,Integer sameCount)throws IOException{
	this.Ana(line,sameCount);
	same.add (line);
    }

    

    void ReadMethod(String arg)throws IOException{
	File file = new File(arg);
	BufferedReader in = new BufferedReader(new FileReader(file));
	String line;
	while((line = in.readLine()) != null){
	    list.add(line);
	}
	in.close();
    }
 

    void Ana(String line, Integer sameCount)throws IOException{
	if(stats1.count == 0){
	    this.putin(line,stats1,6,sameCount);
	}else{
	    this.split(line,stats1,6,sameCount);
	}
	if(stats2.count == 0){
	    this.putin(line,stats2,11,sameCount);
	}else{
	    this.split(line,stats2,11,sameCount);
	}
	
    }

    void split(String line, Stats3 stats, Integer num,Integer sameCount) throws IOException{
	String[] data = line.split(",");
	if(!data[num].equals("")){//無料だったら、０にする
	    if(data[num].equals("無料")){
		stats.update(new Double(0));
	    }else{
		stats.update(new Double(data[num]));
		stats.ComeMark(new Double(sameCount));
	    }
	}
    }

    void putin(String line, Stats3 stats,Integer num,Integer comeCount){
	String[] data = line.split(",");
	if(data[num].length() !=0){
	    stats.Sum = new Double(data[num]);
	    stats.Max = new Double(data[num]);
	    stats.Min = new Double(data[num]);
	    stats.ComeMark = new Double(comeCount);
	    stats.count = 1;
	}
    }

    void print(ArrayList<String> line){
	System.out.printf("収容可能台数（自転車) ");
	System.out.println(stats1.string());
	System.out.printf("駐輪代（自転車）／日 ");
	System.out.println(stats2.string());
	this.print2(line);
    }
    void print2(ArrayList<String> line){
	for(Integer i = 0;i<line.size();i ++){
	    
	    String[] data = line.get(i).split(",");
	    this.appearAdd(data,i);
	}
    }
    
    void appearAdd(String[] data,Integer i){
	ArrayList<String> appear = new ArrayList<>();
	for(Integer x =0;x<23;x ++){
	    if(x == 0||x==3||x==6||x==11||x==21||x==22){
		appear.add(data[x]);
	    }
	}
	double c = stats2.ComeMark;
	Integer a = (int)c;//doubleをint変換している
	if(i==a){
	    System.out.printf("*");
	}
	this.finish(appear);
    }
    
    void finish(ArrayList<String> appear){
	Integer x=0;
	for(x =0;x<6;x ++){
	    System.out.printf("%s",appear.get(x));
	    System.out.printf(",");
	}
	System.out.printf("\n");
    }



    public static void main(String[] args)throws IOException{
	ParkingAnalyzer3 parking3 = new ParkingAnalyzer3();
	parking3.run(args);
    }

}
