/*
課題４
Arguments4.java
松尾彩望
645131
 */

import java.io.*;
import java.util.ArrayList;

class Arguments4{
    boolean help =false;
    boolean station_help = false;
    boolean latlon_help =false;
    String station = "";
    String latlon ="";
    ArrayList<String> argument = new ArrayList<>();
    void parse(String[] args){
	Integer i;
	for(i=0;i<args.length;i++){
	    if(args[i].equals("--help")||args[i].equals("-h")){
		help =true;
		    }else if(args[i].equals("-s")||args[i].equals("station")){
		i ++;
		station =args[i];
		station_help = true;
	    }else if(args[i].equals("-L")||args[i].equals("--latlon")){
		i ++;
		latlon=args[i];
		latlon_help = true;
	    }else{
		argument.add(args[i]);
	    }
	    
	}
    }
}
