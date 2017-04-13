package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdinParser extends Parser {
//	Constructor
	public StdinParser() {
		
		br = new BufferedReader( new InputStreamReader(System.in) );
//		parse(br);
		
	}
}
