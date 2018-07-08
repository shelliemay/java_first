/*
  発プロ最終課題1
  駐車場
  氏名：松尾　彩望
  学籍番号：645131
*/

import java.io.*;
import java.util.ArrayList;

public class ParkingAnalyzer1{
    ArrayList<Double> list = new ArrayList<Double>();
    void run(String[] args) throws IOException{
        
        Double ave=0.0;Double max=0.0;Double min =100.0;Double total = 0.0;

	if(args.length == 0){
	    System.out.printf(" max: N/A, min: N/A,total: N/A,average: N/A%n ");
	}
	else{
	    this.mathCount(args,ave,max,min,total);   
	}
	
    }

    void mathCount(String[] args,double ave,double max,double min,double total)throws IOException{
	Integer i = 0;Integer num = 6;Integer count = 0;
	Double falseMax= 0.0;Double  falseMin=1000.0; Double falseAveTotal = 0.0;
	for(i = 0;i <args.length;i ++){
	    list = this.get_List(new File(args[i]),num);
	    if(num == 6){
		falseMax = this.maxMany(list);
		falseMin = this.minMany(list);
		count =count + list.size();
		total += this.totalMany(list);
	    }
	    if(max<falseMax){
		max = falseMax;
	    }
	    if(min>falseMin&&falseMin!=0){
		min = falseMin;
	    }
	}
	ave = total/count;
	this.print(ave,max,min,total);
    }



    ArrayList<Double> get_List(File file,Integer num) throws IOException{
	ArrayList<Double> list = new ArrayList<>();
	BufferedReader in = new BufferedReader(new FileReader(file));
	String line;
	while((line = in.readLine()) != null){
	    //なにも書かれていなかったら
	    String[] items = line.split(",");
	    if(!items[num].equals("")){
		Double item = new Double(items[num]);
		list.add(item);
	    }
	    
	}
	in.close();
	return list;
    }
    Double aveMany(ArrayList<Double> list) throws IOException{
	Double sum = 0.0;
	for(Integer i = 0; i < list.size(); i++){
	    sum = sum + list.get(i);
	}
	Double ave = sum / list.size();
	return ave;
    }
    Double maxMany(ArrayList<Double> list) throws IOException{
	Double max = 0.0;
	for(Integer i = 0; i < list.size(); i++){
	    if(list.get(i) > max){
		max = list.get(i);
	    }
	}
	return max;
    }
    Double minMany(ArrayList<Double> list) throws IOException{
	Double min = list.get(0);
	for(Integer i = 0; i < list.size(); i++){
	    if(list.get(i) < min){
		min = list.get(i);
	    }
	}
	return min;
    }

    Double totalMany(ArrayList<Double> list)throws IOException{
	Double total = 0.0;
	for(Integer i = 0; i < list.size(); i++){
	    total = total + list.get(i);
	}
	return total;
	
    }
    void print(Double ave,Double max, Double min,Double total) throws IOException{
	System.out.printf(" max : %.0f min : %.0f total : %.0f average : %f %n", max,min,total,ave);
    }
    public static void main(String[] args) throws IOException{
	ParkingAnalyzer1 analyzer1 = new ParkingAnalyzer1();
	analyzer1.run(args);
    }
}
    
        
