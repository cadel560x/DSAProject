package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Porta2 {
//	Member attributes/fields
	private Map<Character, Map<Character, Character> > tableau;
//	private List< Map< Character, Character > > tableArray;
//	private static final int NUMBER_ENCONDING_TABLES = 13;
//	I explicity declare 13 encoding tables because I need their explicit references.
//	I use their references/names to fill up the 'tableau'
//	private Map< Character, Character > encodeTable1 = new HashMap<>();
//	private Map< Character, Character > encodeTable2 = new HashMap<>();
//	private Map< Character, Character > encodeTable3 = new HashMap<>();
//	private Map< Character, Character > encodeTable4 = new HashMap<>();
//	private Map< Character, Character > encodeTable5 = new HashMap<>();
//	private Map< Character, Character > encodeTable6 = new HashMap<>();
//	private Map< Character, Character > encodeTable7 = new HashMap<>();
//	private Map< Character, Character > encodeTable8 = new HashMap<>();
//	private Map< Character, Character > encodeTable9 = new HashMap<>();
//	private Map< Character, Character > encodeTable10 = new HashMap<>();
//	private Map< Character, Character > encodeTable11 = new HashMap<>();
//	private Map< Character, Character > encodeTable12 = new HashMap<>();
//	private Map< Character, Character > encodeTable13 = new HashMap<>();
	private String key;
	
	
	
	
//	Constructors
	public Porta2() {
		key = "PORTA";
		init();
	}

	public Porta2(String key) {
		this.key = key; // check out if 'toUpperCase' uses loops, there is a chance to use a map...
		init();
	}

	
	

//	Getters & setters
	public String getKey() {
		return key;
	} // to delete, not a good idea to get the key ;-)

	public void setKey(String key) {
//		Watch out for those pranksters that change the key to a invalid value
		if (key == null || key.equals("")) {
			System.out.println("The key can't be 'null' or empty!\nUsing 'PORTA' as key");
			this.key = "PORTA";
		} else
			this.key = key;
	}


	
	
//	Other methods
	public void init() {
		tableau = new HashMap<>();
		
//		List< Map< Character, Character > > tableArray = new ArrayList<>();
		Map< Character, Character> tempEncondigTable;
//		char keyChar;
//		char tempEncChar;
		
		for (char keyChar = 'A'; keyChar < '['; keyChar += 2) {
			tempEncondigTable = new HashMap<>();
//			tempChar = (char) ('A' + i);
//			keyChar = (char)('A' +  (i +2 ));
			for (char character = 'A'; character < '['; character++) {
//				tempEncChar = processChar(keyChar, character);
//				tempEncondigTable.put(character, tempEncChar);
				tempEncondigTable.put(character, processChar(keyChar, character));
			}
			
//			tableArray.add(tempEncondigTable);
			
			tableau.put(keyChar, tempEncondigTable); // Adding first key in key-pair 
			tableau.put((char)(keyChar + 1), tempEncondigTable); // Adding second key in key-pair
//			key = i 
			
		}

	}
	
	private char processChar(char keyCharacter,char plainCharacter) // Comment this method
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
	
	
	public String encode(String word) {
		StringBuilder sb = new StringBuilder();
		char codedChar;
		int keyLength = key.length();
		
		for (int i = 0; i < word.length(); i++) {
//			'i % keyLength' avoids 'IndexOutOfBoundsException', no matter the length of the word to process
			if( Character.isLetter(word.charAt(i))) {
				codedChar = tableau.get(key.charAt(i % keyLength)).get(word.toUpperCase().charAt(i));
				sb.append(codedChar);
			}
		}
		
		return sb.toString();
		
	} // enconde

	
	public String decode(String cypherText) {
		return encode(cypherText);
		
	} // decode
	
}
