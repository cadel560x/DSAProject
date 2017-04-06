package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

public class EncodeTable {
//	Member attributes/fields
	private Map< Character, Character > et;
	
	
	

//	Constructors
	public EncodeTable() {
		et = new HashMap<>();
	}




//	Other methods
	public Character get(Object key) {
		return et.get(key);
	} // get

	public Character put(Character key, Character value) {
		return et.put(key, value);
	} // put

} // EncodeTable
