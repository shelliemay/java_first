/*
  　課題２
  　松尾　彩望
  　645131
*/
import java.io.*;
import java.util.ArrayList;
public class ParkingAnalyzer2{

    ArrayList<String> list = new ArrayList<>();
    Stats stats1 = new Stats();
    Stats stats2 = new Stats();

    void run(String[] args)throws IOException{
	for(Integer i = 0; i < args.length; i++){
	    this.ReadMethod(args[i]);
	}
	for(String line: list){
	    this.Ana(line);
	}
	this.print();
    }

 

    void Ana(String line)throws IOException{
	if(stats1.count == 0){
	    this.putin(line,stats1,6);
	}else{
	    this.split(line,stats1,6);
	}
	if(stats2.count == 0){
	    this.putin(line,stats2,11);
	}else{
	    this.split(line,stats2,11);
	}

    }


    void split(String line, Stats stats, Integer num) throws IOException{
	String[] data = line.split(",");
	if(!data[num].equals("")){
	    if(data[num].equals("無料")){//無料と表示されるもの処理
		stats.update(new Double(0));
	    }else{
		stats.update(new Double(data[num]));
	    }
	}
    }
    void ReadMethod(String arg) throws IOException{
	File file = new File(arg);
	BufferedReader in = new BufferedReader(new FileReader(file));
	String line;
	while((line = in.readLine()) != null){
	    list.add(line);
	}
	in.close();
    }

    void putin(String line, Stats stats,Integer num){
	String[] data = line.split(",");
	stats.Sum = new Double(data[num]);
	stats.Max = new Double(data[num]);
	stats.Min = new Double(data[num]);
	stats.count = 1;
    }

    void print(){
	System.out.printf("収容可能台数（自転車)  ");
	System.out.println(stats1.answer());
	System.out.printf("駐輪代（自転車）／日   ");
	System.out.println(stats2.answer());
    }
    Double Min(){
	Double Min;
	Min = stats2.Min;
	return Min;
    }
    
    public static void main(String[] args)throws IOException{
	ParkingAnalyzer2 parking2 = new ParkingAnalyzer2();
	parking2.run(args);
    }

}
