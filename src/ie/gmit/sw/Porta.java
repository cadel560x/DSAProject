package ie.gmit.sw;

public class Porta {
//	Member attributes/fields
	private String key;
	

//	Constructors
	public Porta() {
		key = "PORTA";
	}


	public Porta(String key) {
		this.key = key.toUpperCase();
	}


//	Getters & setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
//		Watch out for those pranksters!
		if (key == null || key.equals("")) {
			System.out.println("The key can't be 'null' or empty!\nUsing 'PORTA' as key");
			this.key = "PORTA";
		} else
			this.key = key;
	}


//	Other methods
	public String enconde(String plainWord) {
		StringBuilder sb = new StringBuilder();
		char encryptedChar;
		
		for (int i = 0; i < plainWord.length(); i++) {
			encryptedChar = get_ct_char(key.charAt(i),  plainWord.toUpperCase().charAt(i));
			sb.append(encryptedChar);
		}
		
		return sb.toString();
	}

	
	private char get_ct_char(char kc,char pc)
	{
		int kc_pos=(kc-'A')/2;
		int pc_pos=pc-'A';
		if(pc-'M'>0)
		{
			
			return (char)('A'+(pc_pos-kc_pos)%13);
		}
		else
		{			
			return (char)((pc_pos+kc_pos)%13+'N');
		}
}
	
}
