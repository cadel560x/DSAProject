package ie.gmit.java2.menu;

import java.util.ArrayList;

public class EncryptionMenu extends Menu {
//	Constructors
	public EncryptionMenu() {
		
		setTitle("Encryption");
		
		menuItemLabels = new ArrayList<>();
		menuItemLabels.add("1. Encrypt from keyboard");
		menuItemLabels.add("2. Encrypt a parser that is already loaded in memory");
		menuItemLabels.add("Press -1 to go back: ");
		
	}
	
	
	
	
//	 Other methods
	public boolean showEncryptedParser() {
		
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
		
	} // showEncryptedParser
	

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
	
	
} // class EncryptionMenu
