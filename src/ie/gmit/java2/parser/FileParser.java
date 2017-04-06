package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import ie.gmit.java2.parser.Parser;

public class FileParser extends Parser {
//	 Fields
	private File file;
	
//	 Constructor
	public FileParser(String fileName) throws FileNotFoundException {
		this.file = new File(fileName);
//			(BufferedReader) 'br' is inherited from the parent abstract class 'Parser' with 'protected' access mode
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			parse(br);
			
	}

}
