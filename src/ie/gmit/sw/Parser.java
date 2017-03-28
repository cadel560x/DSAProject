package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
//	Member attributes/fields
	List<String> fileContents;// = new ArrayList<String>();
	private File file;
	
	
	
	
//	Constructors	
	public Parser() {
		fileContents = new ArrayList<String>();
		file = null;
	}
	
	public Parser(String pathname) {
		fileContents = new ArrayList<String>();
		this.file = new File(pathname);
		this.parse(file);		
	}
	
	
	
	
//	Getters & setters
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public List<String> getFileContents() {
		return fileContents;
	}
	
	
	
	
//	Other methods
	public void parse(File file) {
//		BufferedReader br;// = null;
//		String [] str;// = null;
//		String s;// = null; 
		try {
			 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String line; 
			 String [] stringArray;
			 
			 
			 do
			 {
				 line = br.readLine();
				 stringArray = line.split("\\W+");
//				 Add to a list that maintains all the words in the file
				 fileContents.addAll(Arrays.asList(stringArray));
			 } while( line != null );
			 
			 br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	} // parse(File file)

	public void parse(String pathname) {
		this.file = new File(pathname);
		this.parse(file);
		
	} // parse(String pathname)	
	
	public void parse() {
		this.parse(this.file);
		
	} // parse()

}