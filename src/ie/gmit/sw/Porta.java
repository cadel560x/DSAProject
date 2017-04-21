package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Porta {
//	Member attributes/fields
	private Map<Character, Map<Character, Character> > tableau;
	private List<String> processedText;
	private String key;
	
	
	
	
//	Constructors
	public Porta() {
		key = "PORTA";
		init();
	}

	
	public Porta(String key) {
		this.key = key.toUpperCase(); // To upper case. Maybe an instance is created using a lower case key
		init();
	}

	
	

//	Getters & setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Map<Character, Map<Character, Character>> getTableau() {
		return tableau;
	}


	public void setTableau(Map<Character, Map<Character, Character>> tableau) {
		this.tableau = tableau;
	}


	public List<String> getProcessedText() {
		return processedText;
	}


	public void setProcessedText(List<String> processedText) {
		this.processedText = processedText;
	}
	
	
	
	
//	Other methods
	public void init() {
		Map< Character, Character> tempEncondigTable;
		processedText = new ArrayList<>();
		tableau = new HashMap<>();
		
//		Loop from 'A' to 'Z' stepping every two characters, so we obtain 13 steps
//		that correspond with the 13 encoding tables
		for (char keyChar = 'A'; keyChar < '['; keyChar += 2) {
//			Instantiate an encoding table for a key pair
			tempEncondigTable = new HashMap<>();
//			And in each table map loop from 'A' to 'Z' mapping them according to the current key pair
			for (char character = 'A'; character < '['; character++) {
//				                map (      <K> , <V>       )
//				                     plainChar , encodedChar according to key pair
				tempEncondigTable.put(character, processChar(keyChar, character));
			} // 'tempEncondigTable' for loop
//			Now put the 'tempEncondigTable' in the 'tableau'
//			    map (      <K> , <V>       )
//			     keyPairChar1 , encodingTable
//		         keyPairChar2 , encodingTable
			tableau.put(keyChar, tempEncondigTable); // Adding first key in key-pair 
			tableau.put((char)(keyChar + 1), tempEncondigTable); // Adding second key in key-pair
			
		} // 'tableau' for loop

	} // init - Complexity O(n^2): a loop nested inside another loop
	
	
	private char processChar(char keyCharacter,char plainCharacter)
	{
//		'A' character is our point of reference, our zero.
//		'N' character is our midpoint with value of 13
//		'Z' character is our last value, 25
//		Put the key character into its pair, this is done doing integer division on the character
		int keyCharacterPosition=(keyCharacter-'A')/2;
//		We must know the amount the characters, the distance, between our plain character and our zero point 'A'		
		int plainCharacterPosition=plainCharacter-'A';
		
//		Porta cipher is a substitution cipher, it 'adds' to the plain character the value of the key pair that is between 0 to 12 
//		plus 'N' or 13 positions.
		
//		This cipher has the property that is one-to-one, that is for plain character 'A' the mapping is 'N' and for
//		plain character 'N' the mapping is 'A',  for plain character 'B' the mapping is 'O' and for
//		plain character 'O' the mapping is 'B' and so on until maping 'M' to 'Z' and 'Z' to 'M'
		
//		So we only need to establish 13 mappings and see if our plain character is above 'M' and if it is invert the
//		way of the mapping
		if (plainCharacter - 'M' > 0)
		{	
//			Our plain character is between 'N' and 'Z'
//			Invert the mapping, this is done subtracting the value of the key pair to the plain character, remember this
//			plain character is above the midpoint 'N' or is 'N', and then add 'A'. We are going backwards from the midpoint
//			to the origin.
//			'%13' is to guarantee that the returned values will be between 0 and 25			
			return (char)('A' + (plainCharacterPosition - keyCharacterPosition)%13);
		}
		else
		{	
//			Our plain character is between 'A' and 'M'
//			Add to the plain character the value of the key pair and then add 13 positions or 'N'
//			We are going forward from the midpoint to the end value
//			'%13' is to guarantee that the returned values will be between 0 and 25
			return (char)((plainCharacterPosition + keyCharacterPosition)%13 + 'N');
		}
		
	} // processChar	 - Complextiy O(1): No loops. Math operations only
	
	
	public void encode(String word) {
		StringBuilder sb = new StringBuilder();
		char codedChar;
		
		for (int i = 0; i < word.length(); i++) {
			if( Character.isLetter( word.charAt(i) ) ) {
//				'tableau.get( key.charAt( i % key.length() ) )' gets the respective encoding table according to the 'i' letter of the key.
//				'i % keyLength' avoids 'IndexOutOfBoundsException', no matter how big is 'i' - 'i' represents the length of the word to process -
//				'.get( word.charAt(i) )' gets the encoded letter for the letter of the word that is being processed
				codedChar = tableau.get( key.charAt( i % key.length() ) ).get( word.charAt(i) );
				sb.append(codedChar);
			}
			
//			Don't process numbers but add them into the end result
			sb.append( word.charAt(i) );
		}
		
//		Store processed strings
		processedText.add( sb.toString() );
		
	} // enconde(String word) - Complexity O(n): Loops through the letters of each word, is word length dependent

	
	public void decode(String cypherText) {
		encode(cypherText);
	} // decode - Complexity O(n): same as 'enconde(String word)'
	
	
	public void encode(List<String> rawContents) {
		
//		 Purge the 'processedText' array list from previous process
		 if ( ! processedText.isEmpty() ) {
			 processedText.clear();
		 }
		 
//		 Loop through the passed list of strings 'rawContents'
		 for(String plainTextWord: rawContents) {
//			 Call 'enconde(String word)'
			 encode( plainTextWord);
		 }
		
	} // encode(ArrayList<String> rawContents) - Complexity O(n^2): There is an implicit loop nesting. The outer loop is
//	traverses the list of strings from one string to the next string. The second loop is found when 'enconde(String word)'
//	that traverses each letter of every string in the passed list 'rawContents'
	
	
	
	public void decode(List<String> rawContents) {
		encode(rawContents);
	} // decode(ArrayList<String> rawContents) - Complexity O(n^2): same as 'encode(ArrayList<String> rawContents)'
	
	
	public void showProcessedText() {
		int characterCounter = 0;
		
//		We are going to give a nice output to 'stdout', lines 80 characters wide
//		Loop through the list that contains the processed text
		for(String processedWord: processedText ) {
//			Add the size of each word to the counter
			characterCounter += processedWord.length() + 1; // '+ 1' because we are adding a space character
			System.out.print(processedWord + " ");
//			If the counter gets to be bigger than 80, reset it and do a nice new line
			if ( characterCounter > 80 ) { // New line after 80 characters
				characterCounter = 0;
				System.out.println();
			}
		}
		
		System.out.println();
		
	} // showProcessedText - Complexity O(n): Loops through the list that contains the processed text, is dependent on the
//	number of strings stored in the list
	
	
} // Class Porta