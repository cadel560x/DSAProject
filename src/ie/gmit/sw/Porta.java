package ie.gmit.sw;

public class Porta {
//	Member attributes/fields
	private String key;
	
	
	

//	Constructors
	public Porta() {
		key = "PORTA";
	}

	public Porta(String key) {
		this.key = key.toUpperCase(); // check out if 'toUpperCase' uses loops, there is a chance to use a map...
	}

	
	

//	Getters & setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
//		Watch out for those pranksters that change the key to a invalid value
		if (key == null || key.equals("")) {
			System.out.println("The key can't be 'null' or empty!\nUsing 'PORTA' as key");
			this.key = "PORTA";
		} else
			this.key = key;
	}


	
	
//	Other methods
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
		
		for (int i = 0; i < word.length(); i++) {
			codedChar = processChar(key.charAt(i),  word.toUpperCase().charAt(i));
			sb.append(codedChar);
		}
		
		return sb.toString();
		
	} // enconde

	
	public String decode(String cypherText) {
		return encode(cypherText);
		
	} // decode
	
}
