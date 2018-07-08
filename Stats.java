/*
  課題２のstate.java
  松尾　彩望
  645131
*/public class Stats{
    Double Sum;
    Double Max;
    Double Min;

    Integer count=0;
    
    void update(Double value){

	if(Min > value){
	    Min = value;
	}
		if(Max < value){
	    Max = value;
	}
	Sum += value;
	count++;
    }
    Double average(){
	return Sum/count;
    }

    String answer(){
	if(count == 0){
	    return "max: N/A, min: N/A, total: N/A, average: N/A";
	}
	return String.format("max: %.0f min: %.0f, total: %.0f, average: %f",Max,Min,Sum,this.average());
    }
}
