package ie.gmit.java2.menu;

import java.util.ArrayList;

public class DecryptionMenu extends Menu {
//	Constructors
	public DecryptionMenu() {
		
		setTitle("Decryption");
		
		menuItemLabels = new ArrayList<>();
		menuItemLabels.add("1. Decrypt from keyboard");
		menuItemLabels.add("2. Decrypt a parser that is already loaded in memory");
		menuItemLabels.add("Press -1 to go back: ");
		
	}
	
	
	
	
//	 Other methods
	public boolean showDecryptedParser() {
		
		do {
			System.out.print("Display processed contents (y/n)? ");
			setOption( console.nextLine() );
			
			switch( getOption() ) {
			case "y":
			case "Y":
				return true;
			case "n":
			case "N":
				return false;
			default:
					System.out.println("Please enter 'y' or 'n'");
			}
		} while ( true) ;
		
	} // showDecryptedParser
	

	public boolean saveToFile() {
		
		do {
			System.out.print("Save processed contents to a file (y/n)? ");
			setOption( console.nextLine() );
			
			switch( getOption() ) {
			case "y":
			case "Y":
				return true;
			case "n":
			case "N":
				return false;
			default:
					System.out.println("Please enter 'y' or 'n'");
			}
		} while ( true) ;
		
	} // saveToFile
	
	
} // class DecryptionMenu
