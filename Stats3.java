/*
  課題3のstate.java
  松尾　彩望
  645131
*/public class Stats3{
    Double Sum;
    Double Max;
    Double Min;
    Integer count=0;
    Double ComeMin;
    Double ComeMark;
    void update(Double value){
	if(Min > value){
	    ComeMin = Min;
	    Min = value;
	}
	if(Max < value){
	    Max = value;
	}
	Sum = Sum + value;
	count++;
    }
    
    Double ComeMark(Double value){
	if(ComeMin > Min){
	    ComeMark = value;
	}
	return ComeMark;
    }
    Double average(){
	return Sum/count;
    }

    String string(){
	if(count <=1){
	    return "max: N/A, min: N/A, total: N/A, average: N/A";
	}
	return String.format("max: %.0f min: %.0f, total: %.0f, average: %f",Max,Min,Sum,this.average());
    }
}
