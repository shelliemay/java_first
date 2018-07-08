/*
  ParkingAnalyzer4.java
  松尾彩望
  645131
*/

import java.io.*;
import java.util.ArrayList;
class ParkingAnalyzer4{
    Arguments4 ar = new Arguments4();
    ArrayList<String> list = new ArrayList<>();
    Stats4 stats1 = new Stats4();
    Stats4 stats2 = new Stats4();
    ArrayList<String> same = new ArrayList<>();
    ParkingAnalyzer2 name2 = new ParkingAnalyzer2();
    
    void run(String[] args)throws IOException{
	ar.parse(args);
	if(ar.help){//boolean
	    System.out.printf("Usage: java ParkingAnalyzer4[OPTIONS]<駐輪場ファイル...>\n");
	    System.out.printf("  -h,  --help:     　このメッセージを表示する。\n");
	    System.out.printf("  -s,  --station<駅>　最寄駅を指定する。\n");
	    System.out.printf("  -L,  --latlon<緯度：経度>：緯度経度を計算する\n");
	    return ;
	}else if(ar.station_help){
	    this.existStation(args,2);
	}else if(ar.latlon_help){
	    this.existStation(args,2);
	}else{
	    name2.run(args);
	    this.Nothing(args);
	}
    }

    void Nothing(String[] args)throws IOException{

	for(Integer i = 0; i < args.length; i++){
	    this.ReadMethod(args[i]);
	}
	Integer nownumber=0;
	double change = name2.Min();
	Integer min = (int)change;
	for(String line: list){
	    String[] data = line.split(",");
	    if(!data[11].equals("")&&!data[11].equals("無料")){
	    nownumber = Integer.valueOf(data[11]);
	    }
	    if(nownumber ==min&&!data[11].equals("無料")){
		System.out.print("*");
	    }
	    System.out.printf(" %s,%s,%s,%s,%s:%s\n",data[0],data[3],data[6],data[11],data[21],data[22]);
	}
    }
    void existStation(String[] args,Integer number)throws IOException{
	Integer sameCount =0;

	for(Integer i = number; i < args.length; i++){
	    this.ReadMethod(args[i]);
	}
	for(String line: list){	
	    String[] data = line.split(",");
	    if(data[3].equals(ar.station)){
		this.separate(same,line,sameCount);
		sameCount ++;
	    }
	}
	this.print(same);
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

    void split(String line, Stats4 stats, Integer num,Integer sameCount) throws IOException{
	String[] data = line.split(",");
	if(!data[num].equals("")){//無料だった
	    if(data[num].equals("無料")){
		stats.update(new Double(0));
	    }else{
		stats.update(new Double(data[num]));
		stats.ComeMark(new Double(sameCount));
	    }
	}
    }

    void putin(String line, Stats4 stats,Integer num,Integer comeCount){
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
	    if(x == 0||x==3||x==6||x==11){
		appear.add(data[x]);
	    }else if(x==21){
		appear.add(data[21]+":"+data[22]);
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
	for(x =0;x<5;x ++){
	    System.out.printf("%s",appear.get(x));
	    System.out.printf(",");
	}
	System.out.printf("\n");
    }



    public static void main(String[] args)throws IOException{
	ParkingAnalyzer4 parking4 = new ParkingAnalyzer4();
	parking4.run(args);
    }



}
