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
//	private int keyLetterIndexAccumulator;
	
	
	
	
//	Constructors
	public Porta() {
		key = "PORTA";
		init();
	}

	
	public Porta(String key) {
		this.key = key.toUpperCase(); // check out if 'toUpperCase' uses loops, there is a chance to use a map...
		init();
	}

	
	

//	Getters & setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
//	public int getKeyLetterIndexAccumulator() {
//		return keyLetterIndexAccumulator;
//	}
//
//
//	public void setKeyLetterIndexAccumulator(int keyLetterIndexAccumulator) {
//		this.keyLetterIndexAccumulator = keyLetterIndexAccumulator;
//	}


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
		
		for (char keyChar = 'A'; keyChar < '['; keyChar += 2) {
			tempEncondigTable = new HashMap<>();
			for (char character = 'A'; character < '['; character++) {
				tempEncondigTable.put(character, processChar(keyChar, character));
			}
			
			tableau.put(keyChar, tempEncondigTable); // Adding first key in key-pair 
			tableau.put((char)(keyChar + 1), tempEncondigTable); // Adding second key in key-pair
			
		}

	} // init
	
	
	private char processChar(char keyCharacter,char plainCharacter) // Do comments on this method
	{
		int keyCharacterPosition=(keyCharacter-'A')/2;
		int plainCharacterPosition=plainCharacter-'A';
		if (plainCharacter - 'M' > 0)
		{
			
			return (char)('A' + (plainCharacterPosition - keyCharacterPosition)%13);
		}
		else
		{			
			return (char)((plainCharacterPosition + keyCharacterPosition)%13 + 'N');
		}
		
	} // processChar	
	
	
	public void encode(String word) {
		StringBuilder sb = new StringBuilder();
//		int keyLetterIndex = getKeyLetterIndexAccumulator();
		char codedChar;
		
		for (int i = 0; i < word.length(); i++) {
			if( Character.isLetter( word.charAt(i) ) ) {
//				'tableau.get( key.charAt( i % key.length() ) )' gets the respective map according to the letter of the key.
//				'i % keyLength' avoids 'IndexOutOfBoundsException', no matter how big is 'i' - 'i' represents the length of the word to process	 -
				
//				'.get( word.charAt(i) )' gets the corresponding letter for the letter of the word being processed
//				codedChar = tableau.get( key.charAt( ( keyLetterIndex + i ) % key.length() ) ).get( word.charAt(i) );
				codedChar = tableau.get( key.charAt( i % key.length() ) ).get( word.charAt(i) );
				sb.append(codedChar);
			}
		}
		
//		setKeyLetterIndexAccumulator( keyLetterIndex + word.length() );
		
//		Store processed strings
		processedText.add( sb.toString() );
		
	} // enconde(String word)

	
	public void decode(String cypherText) {
		encode(cypherText);
	} // decode
	
	
	public void encode(List<String> rawContents) {
		
//		 Purge the 'processedText' array list from previous process
		 if ( ! processedText.isEmpty() )
			 processedText.clear();
		 
//		 setKeyLetterIndexAccumulator(0);
		 
		 for(String plainTextWord: rawContents) {
			 encode( plainTextWord);
		 }
		
	} // encode(ArrayList<String> rawContents)
	
	
	public void decode(List<String> rawContents) {
		encode(rawContents);
	} // decode(ArrayList<String> rawContents)	
	
	
	public void showProcessedText() {
		int characterCounter = 0;
		
		for(String processedWord: processedText ) {
			characterCounter += processedWord.length() + 1; // '+ 1' because we are adding a space character
			System.out.print(processedWord + " ");
			if ( characterCounter > 80 ) { // New line after 80 characters
				characterCounter = 0;
				System.out.println();
			}
		}
		
		System.out.println();
		
	} // showProcessedText
	
	
} // Class Porta2
