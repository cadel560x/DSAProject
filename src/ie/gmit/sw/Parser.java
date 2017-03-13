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
	
	private BufferedReader br = null;
	private List<String> fileContents = new ArrayList<String>();
	private File file = null;
	
	public Parser() {
		
	}
	
	

	public void parse() {
		this.parse(this.file);
		
	} // parse()

	public void parse(String pathname) {
		this.file = new File(pathname);
		this.parse(file);
		
	} // parse(String pathname)
	
	public void parse(File file) {
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 
			 String s = null; 
			 String [] str = null;
			 while((s = br.readLine()) != null )
			 {
					str = s.split("\\W+");
					// Add to a list that maintains all the words in the file
					fileContents.addAll(Arrays.asList(str));	
			 }
			 br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	} // parse(File file)
}
