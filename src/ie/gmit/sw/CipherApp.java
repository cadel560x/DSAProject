package ie.gmit.sw;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import ie.gmit.java2.parser.*;
import ie.gmit.java2.menu.*;

public class CipherApp implements Runnable { // Use of interfaces. Method 'run()' implements the application logic
//	Member attributes/fields
//	Here I apply the 'composition' design concept
//	This console application HAS-A: 'Parser' object, a 'Porta' object and some 'Menu' objects
	private Parser parser;
	private Porta portaCipher;
	private MainMenu mainMenu;
	private InputFeedMenu inputFeedMenu;
	private KeySetupMenu keySetupMenu;
	private EncryptionMenu encryptionMenu;
	private DecryptionMenu decryptionMenu;
	
	
	
	
//	Constructor
	public CipherApp() {
		
		mainMenu = new MainMenu();
		portaCipher = new Porta();
		inputFeedMenu = new InputFeedMenu();
		keySetupMenu = new KeySetupMenu();
		encryptionMenu = new EncryptionMenu();
		decryptionMenu = new DecryptionMenu();
		
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
					decrypt();
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
				loadFileParser();
				
//				Nothing else to do here. Go to parent menu.
				inputFeedMenu.setOption("-1");
				break;
				
			case "2":
				loadUrlParser();
				
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
	
	
	private void loadFileParser() {
		
		try {
			System.out.print("Please type the pathname: ");
			parser = new FileParser( Menu.getConsole().nextLine() );
			parser.parse();
//			Converts all words to upper case
			parser.toUpper();
			
			System.out.println("File '" + ( (FileParser)parser ).getFile().getName() + "' parsed and loaded into memory\n");
			
		} catch (FileNotFoundException e) {
			
			System.out.println("There was a problem reading from the specified file\nPlease try again or use a different file.");
			Menu.exceptionHandler(e);
			
		}
		
		
		if ( inputFeedMenu.showFeed() ) {
			parser.showStdOut();
			System.out.println("\n");
		}
		
	} // loadFileParser
	
	
	private void loadUrlParser() {
		
		try {
			System.out.print("Please type an URL: ");
			parser = new UrlParser( Menu.getConsole().nextLine().toLowerCase() );
			parser.parse();
//			Converts all words to upper case					
			parser.toUpper();
			
			System.out.println("URL '" + ( (UrlParser)parser ).getSite().toString() + "' parsed and loaded into memory\n");
			
		} catch (IOException e) {
			
			System.out.println("There was a problem reading from the specified URL\nPlease try again or use a different URL.");
			Menu.exceptionHandler(e);
			
		}
		
		if ( inputFeedMenu.showFeed() ) {
			parser.showStdOut();
			System.out.println("\n");
		}
		
	} // loadUrlParser
	
	
	private void keySetup() {
		boolean sentinel;
		
//		Check if key is not empty and all uppercase
//		'keySetupMenu.getOption()' returns a 'String'
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
				encryptStdin();
				
//				Nothing else to do here. Go to parent menu.				
				encryptionMenu.setOption("-1");				
				break;
				
			case "2":
				encryptParser();				
				
//				Nothing else to do here. Go to parent menu.				
				encryptionMenu.setOption("-1");				
				break;
			}
		} while ( ! encryptionMenu.getOption().equals("-1") );
		
	} // encrypt
	
	
	private void encryptStdin() {
		long startTime;
		
		System.out.print("Please enter the message to process: ");
		StdinParser stdinParser = new StdinParser();
		stdinParser.parse();
//		Converts all words to upper case					
		stdinParser.toUpper();
		
		startTime = System.nanoTime();
		for ( String plainTextWord: stdinParser.getContents() )
			 portaCipher.encode(plainTextWord);
		
		System.out.println( "Running time (ns): " + (System.nanoTime() - startTime ) + " nanoseconds." );
		
		System.out.println("\nProcessed text:");
		portaCipher.showProcessedText();
		System.out.println();
		
	} // encryptStdin
	
	
	private void encryptParser() {
		long startTime;
		
		if ( parser != null ) {
			startTime = System.currentTimeMillis();
			
			portaCipher.encode( parser.getContents() );
			
			System.out.println( "Running time (ms): " + (System.currentTimeMillis() - startTime ) + " milliseconds.\n" );
			
			if ( encryptionMenu.showEncryptedParser() ) {
				portaCipher.showProcessedText();
				System.out.println();
			}
			
			if ( encryptionMenu.saveToFile() ) {
				outputToFile();
			}
		}
		else {
			System.out.println("No parser loaded in memory.\nPlease go back and select a source feed.");
		}
		
		System.out.println();
		
	} // encryptParser
	
	
	private void outputToFile() {
		int characterCounter = 0;
		String outputFile;
		BufferedWriter bw;
		
		System.out.print("Please enter the output file: ");
		outputFile = Menu.getConsole().nextLine();
		
		try {
			bw = new BufferedWriter( new FileWriter( outputFile ) );
			
			for( String temp: portaCipher.getProcessedText() ) {
				characterCounter += temp.length() + 1; // '+ 1' because we are adding a space character
				bw.write(temp + " ");
				if ( characterCounter > 80 ) { // New line after 80 characters
					characterCounter = 0;
					bw.newLine();
				}
			}
			bw.newLine();
			bw.close();
			
			System.out.println("Processed text saved in file " + outputFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	} // outputToFile
	
	
	private void decrypt() {
		
		do
		{
			decryptionMenu.print();
			
			switch( decryptionMenu.getOption() ) {
			case "1":
				encryptStdin();
				
//				Nothing else to do here. Go to parent menu.				
				decryptionMenu.setOption("-1");				
				break;
				
			case "2":
				encryptParser();				
				
//				Nothing else to do here. Go to parent menu.				
				decryptionMenu.setOption("-1");				
				break;
			}
		} while ( ! decryptionMenu.getOption().equals("-1") );
		
	} // decrypt
	
	
} // class CipherApp