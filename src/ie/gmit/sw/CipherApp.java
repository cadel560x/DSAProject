package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.io.IOException;

import ie.gmit.java2.parser.*;
import ie.gmit.java2.menu.*;

public class CipherApp implements Runnable { // Use of interfaces. Method 'run()' implements the application logic
//	Member attributes/fields
//	Here I apply the 'composition' design concept
//	This console application HAS-A: 'Parser' object, a 'Porta' object and some 'Menu' objects
	private Parser parser;
	private Porta2 portaCipher;
	private MainMenu mainMenu;
	private InputFeedMenu inputFeedMenu;
	private KeySetupMenu keySetupMenu;
	private EncryptionMenu encryptionMenu;
	
	
	
	
//	Constructor
	public CipherApp() {
		
		mainMenu = new MainMenu();
		portaCipher = new Porta2();
		inputFeedMenu = new InputFeedMenu();
		keySetupMenu = new KeySetupMenu();
		encryptionMenu = new EncryptionMenu();
		
	}



	
//	Implementation of abstract methods
	@Override
	public void run() {
		
		do {
			mainMenu.print();
			
			switch( mainMenu.getOption() ) {
				case "1":
					selectSource();
					break;
				case "2":
					keySetup();
					break;
				case "3":
					encrypt();
					break;
				case "4":
					Menu2.printTitle("Decrypt");
					break;
				case "-1":
					break; // Just exit
				default:
					System.out.println("Invalid option. Please try again.\n");
			}
		} while( ! mainMenu.getOption().equals("-1") );
		
		System.out.println("Thank you for using my Porta cypher. Have a nice day.\nJavier Mantilla G00329649");
		
	} // run
	
	
	
	
//	Other methods	
	private void selectSource() {
		
		do {
			inputFeedMenu.print();
			
			switch( inputFeedMenu.getOption() ) {
			case "1":
				try {
					System.out.print("Please type the pathname: ");
					parser = new FileParser( Menu.getConsole().nextLine() );
					
					System.out.println("File '" + ( (FileParser)parser ).getFile().getName() + "' parsed and loaded into memory\n");
					
				} catch (FileNotFoundException e) {
					
					System.out.println("There was a problem reading from the specified file\nPlease try again or use a different file.");
					Menu.exceptionHandler(e);
					
				}
				
//				Nothing else to do here. Go to parent menu.
				inputFeedMenu.setOption("-1");
				break;
				
			case "2":
				try {
					System.out.print("Please type an URL: ");
					parser = new UrlParser( Menu.getConsole().nextLine().toLowerCase() );
					
					System.out.println("URL '" + ( (UrlParser)parser ).getSite().toString() + "' parsed and loaded into memory\n");
					
				} catch (IOException e) {
					
					System.out.println("There was a problem reading from the specified URL\nPlease try again or use a different URL.");
					Menu.exceptionHandler(e);
					
				}
				
//				Nothing else to do here. Go to parent menu.				
				inputFeedMenu.setOption("-1");
				break;
				
			case "-1":
				break; // Just exit
				
			default:
				System.out.println("Invalid option. Please try again.\n");
				
			}
			
		} while ( ! inputFeedMenu.getOption().equals("-1") );
		
	} // selectSource
	
	
	private void keySetup() {
		boolean sentinel;
		
//		Check if key is not empty and all uppercase
		do {
			keySetupMenu.print();
			sentinel = keySetupMenu.getOption().isEmpty();
			
			for ( int i = 0; i < keySetupMenu.getOption().length(); ++i )
				if ( ! Character.isUpperCase( keySetupMenu.getOption().charAt(i) ) ) {
					System.out.println("Invalid key. Please try again.\n");
					sentinel = true;
					break;
				}
			
		} while ( sentinel );
		
		portaCipher.setKey( keySetupMenu.getOption() );
		System.out.println("'" + portaCipher.getKey() + "' set up as key\n");
		
	} // keySetup
	
	
	private void encrypt() {
		
		do
		{
			encryptionMenu.print();
			
			switch( encryptionMenu.getOption() ) {
			case "1":
				try {
					System.out.print("Please enter the message to cypher: ");
					parser = new StdinParser();
					parser.getBr().readLine();
//					parser.parse();
					
				} catch (IOException e) {
//					
//					System.out.println("There was a problem reading from the specified URL\nPlease try again or use a different URL.");
					ie.gmit.java2.menu.Menu.exceptionHandler(e);
//					
				}
				
				break;
				
			case "2":
				break;
			}
		} while ( ! encryptionMenu.getOption().equals("-1") );
		
	} // encrypt
	
	
} // class CipherApp