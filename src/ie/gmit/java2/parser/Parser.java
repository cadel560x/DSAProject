package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 The use of an abstract class is due to provide inheritance to FileParser and URLParser. It provides an (ArrayList) and a (BufferedReader) to both child classes
 and implementation of the requested methods.
 */
public abstract class Parser {
//	Member attributes/fields
/*
	 (BufferedReader) 'br' is set to 'protected' access mode since it is mutated from the children classes 'FileParser' and 'URLParser' and not from anywhere else
	 (List<String>) 'contents' is the list where all sourced words will be stored for parsing
 */
	protected BufferedReader br;
	private List<String> contents = new ArrayList<String>();
	
	
	
	
//	Getters & setters
	public List<String> getContents() {
		return contents;
	}
	
	
	public BufferedReader getBr() {
		return br;
	}


	public void setBr(BufferedReader br) {
		this.br = br;
	}
	
	
	
	
//	Requested methods are implemented here as they performed the same manipulations on the instance variable (ArrayList<String>) 'contents' and (BufferedReader) 'br' 
	public int count() {
		return contents.size();
	}

	
	public boolean contains(String s) {
		return contents.contains(s);
	}

	
	public int getFirstIndex(String s) {
		return contents.indexOf(s);
	}

	
	public int getLastIndex(String s) {
		return contents.lastIndexOf(s);
	}

	
	public void delete(String s) {
		while (contains(s))
			delete(getFirstIndex(s));

	}

	
	public void delete(int index) {
		contents.remove(index);
	}

	
	public int countOcurrences(String s) {
		int counter;
		
		counter=0;
		for (String word: contents)
			if (s.equalsIgnoreCase(word))
				counter++;
		
		return counter;
		
	} // countOcurrences
	
	
	public int[] getAllIndeces(String s) {
	    ArrayList<Integer> indexList = new ArrayList<Integer>();
	    int[] indexes;
	    int i;
	    
	    i = 0;
	    for (String word: contents) {
	        if (s.equalsIgnoreCase(word))
	            indexList.add(i);
	        i++;
	    }
	    indexes = new int[indexList.size()];
	    
	    i = -1;
	    for(Integer n: indexList)
	    	indexes[++i] = n;
	    
	    return indexes;
	    
	} // getAllIndeces
	
	
	
	
//	Other methods
	public String get(int index) {
		return contents.get(index);
	}
	
	
	public void show() {
		System.out.println(contents);
	}

	
	public void showStdOut() {
		
		int characterCounter = 0;
		
		for(String processedWord: contents ) {
			characterCounter += processedWord.length() + 1; // '+ 1' because we are adding a space character
			System.out.print(processedWord + " ");
			if ( characterCounter > 80 ) { // New line after 80 characters
				characterCounter = 0;
				System.out.println();
			}
		}		
		
		System.out.println();
	}
	
	
	public float averageWordSize() {
		float sum = 0;
		float average;
		
		for(String word : contents)
			sum = sum + word.length();
		
		average = sum / count();
		
		return average;
		
	} // averageWordSize
	
	
	public String mostCommonWord() {
		String mostCommon = new String();
		int max = 0;
		int occurrences = 0;
		
		for (String word :  contents) {
			occurrences = countOcurrences(word);
			if (max < occurrences ) {
				mostCommon=word;
				max = occurrences;
			}
		}
		
		return mostCommon;
		
	} // mostCommonWord
	
	
	public void toUpper() {
		contents.replaceAll(String::toUpperCase);		
	} // toUpper
	
	
	public void toLower() {
		contents.replaceAll(String::toLowerCase);	
	} // toLower
	
	
	protected void exceptionHandler(Exception e) {
		
		System.err.println("\nPlease send an email to G00329649@gmit.ie with the following information: ");
		e.printStackTrace();
		
	} // exceptionHandler
	
	
	public void parse(BufferedReader br) {
		 String s = null; 
		 String [] str = null;
		 
//		 Purge the 'contents' array list from previous parsers
		 if ( ! contents.isEmpty() )
			 contents.clear();
		 
		 try {
				while( (s = br.readLine() ) != null )
				 {
/*					 
 					 Regex '\W+' is more accurate for parsing than '\s+'
					 '\s+' uses whitespaces (space, tabs, newlines) as delimiters
					 as for '\W+' uses anything that is NOT [a-zA-Z_0-9] as delimiters.
					 So for example, words with trailing punctuation marks ( foo. foo, foo! foo? ... ) are
					 better parsed. This is also useful for parsing HTML where words are surrounded
					 with tags < > or with quotation marks "" within HTML attributes.
*/
						str = s.split("\\W+");
//						Add to the list that keeps all the sourced words
						contents.addAll(Arrays.asList(str));	
				 }
				
				br.close();
		} catch (IOException e) {
			exceptionHandler(e);
		}
//		For some unclear reason some "" characters get in, let's delete them
		delete("");
		
	} // parse(BufferedReader br)
	
	
	public void parse() {
		parse(this.br);
	} // parse()
	
	
} // class Parser