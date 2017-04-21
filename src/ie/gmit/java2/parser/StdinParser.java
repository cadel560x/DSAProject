package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StdinParser extends Parser {	
//	Constructor
	public StdinParser() {
		
		br = new BufferedReader( new InputStreamReader(System.in) );
		
	}




//	Overridden method 'parse' from parent class 'Parser'
	public void parse(BufferedReader br) {
		 String s = null; 
		 String [] str = null;		 
		 
		 try {
			 s = br.readLine();
			 str = s.split("\\W+");
			 getContents().addAll(Arrays.asList(str));
		 } catch (IOException e) {
			 exceptionHandler(e);
		 }
		 
	} // parse
	
	
} // class StdinParser
