package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import ie.gmit.java2.parser.*;

public class Menu2 {
//	Member attributes/fields	
	private static Scanner console = new Scanner(System.in);
	private List<String> menuItemLabels = new ArrayList<String>();
	private String title;
	protected String optionSelected;
	
//	private Porta portaCipher;// = new Porta();
//	private Porta2 portaCipher2;
//	private Parser parser;
//	private String option;
	
	
	
	
//	Constructors
	public Menu2() {
//		console = new Scanner(System.in);
//		portaCipher = new Porta();
		portaCipher2 = new Porta2();
	}
	
	
	
	
//	Other methods
	public static String printMainMenu() {
		
		printTitle("Porta Cipher - Javier Mantilla G00329649");
		System.out.println("1. Select source feed");
		System.out.println("2. Set up encryption key");
		System.out.println("3. Encrypt");
		System.out.println("4. Decrypt");
		System.out.print("Press -1 to exit: ");
		
		System.out.println();
	
//		option = console.nextLine();
//		console.close();
		
//		return option;
		return console.nextLine();
		
	} // printMainMenu
	
	
	public static String printInputSource() {

		printTitle("Source feed");
		System.out.println("Please choose a source feed: ");
		System.out.println("1. File");
		System.out.println("2. URL");
		System.out.print("Press -1 to go back: ");
		System.out.println();
		
//			option = console.nextLine();
//			console.close();
		
//			return option;
		return console.nextLine();
			
	} // printInputSource
	
	
	public static void printFileSetup(ie.gmit.java2.parser.FileParser fileParser) { // Clean this

		try {
			System.out.print("Please type the pathname: ");
			fileParser.setFile( new File(console.nextLine()) );
			fileParser.setBr( new BufferedReader ( new InputStreamReader( new FileInputStream( fileParser.getFile() ) ) ) );
			System.out.println("File '" + fileParser.getFile().getName() + "' parsed and loaded into memory\n");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("There was a problem reading from the specified file\nPlease try again or use a different file.");
		}
		
	} // printURLSetup
	
	
	public static void printUrlSetup(ie.gmit.java2.parser.UrlParser urlParser) { // Clean this
		
		try {
			System.out.print("Please type an URL: ");
//			webSite = webSite.toLowerCase();
			
			urlParser.setSite( new URL( console.nextLine().toLowerCase() ) );
			
			if ( urlParser.getSite().toString().startsWith("https://") ) {
				urlParser.setSecSite( (HttpsURLConnection) urlParser.getSite().openConnection() );
				urlParser.setIs( urlParser.getSecSite().getInputStream() );
			}
			else
				urlParser.setIs( urlParser.getSite().openStream() );
			
			urlParser.setBr( new BufferedReader( new InputStreamReader( urlParser.getIs() ) ) );
			
			System.out.println("URL '" + urlParser.getSite().toString() + "' parsed and loaded into memory\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // printUrlSetup
	
	
	public static void printKeySetup(Porta2 portaCipher) {
		
		printTitle("Key Setup");
		System.out.print("Enter key: ");
		portaCipher.setKey(console.nextLine());
		
		System.out.println();
		
	} // keySetup
	
	
	private void encrypt() {
		// TODO Auto-generated method stu
		List<String> cypherText = new ArrayList<>();
		List<String> cypherText2 = new ArrayList<>();
		long startTime;
		
		do {
			printTitle("Encryption");
			System.out.println("1. From keyboard");
			System.out.println("2. From a parser loaded in memory");
			System.out.print("Press -1 to go back: ");
			
			option = console.nextLine();
			
			if( option.equals("1") ) {
				String[] stringArray;
				String fromKeyboard;
				System.out.print("Please enter the message to cypher: ");
				fromKeyboard = console.nextLine();
				
				stringArray = fromKeyboard.split("\\W+");
				cypherText.addAll(Arrays.asList(stringArray)); // Try using a 'Parser' object
				
				System.out.println("Encrypting from standard input");
				
				startTime = System.currentTimeMillis();
				for (String temp: parser.getFileContents()) {
					cypherText.add(portaCipher.encode(temp));
//					System.out.println(portaCipher.encode(temp));
				}
				
				System.out.println("Running time (ms): " + (System.currentTimeMillis() - startTime + "\n"));
				
				startTime = System.currentTimeMillis();
				for (String temp: parser.getFileContents()) {
					cypherText.add(portaCipher2.encode(temp));
//					System.out.println(portaCipher.encode(temp));
				}
				
				System.out.println("Running time (ms): " + (System.currentTimeMillis() - startTime + "\n"));				
				
			}
			else if ( option.equals("2") ) {
				if ( parser != null )
					System.out.println("Encrypting '" + parser.getFile().getName() + "'");
				else {
					System.out.println("Please select a file or URL to parse first.");
					return;
				}
					
				startTime = System.currentTimeMillis();
				for (String temp: parser.getFileContents()) {
					cypherText.add(portaCipher.encode(temp));
//					System.out.println(portaCipher.encode(temp));
				}
				
				System.out.println("Running time (ms): " + (System.currentTimeMillis() - startTime + "\n"));
				
				startTime = System.currentTimeMillis();
				for (String temp: parser.getFileContents()) {
					cypherText2.add(portaCipher2.encode(temp));
//					System.out.println(portaCipher.encode(temp));
				}
				
				System.out.println("Running time (ms): " + (System.currentTimeMillis() - startTime + "\n"));	
				
				printTitle("Output Cyphertext");
				System.out.println("1. To screen");
				System.out.println("2. To file");
				option = console.nextLine();
				
				if ( option.equals("1") ) {
					int letterCounter = 0;
					
					for(String temp: cypherText) {
						letterCounter += temp.length() + 1; // '+ 1' because we are adding a space character
						System.out.print(temp + " ");
						if ( letterCounter > 80 ) { // New line after 80 characters
							letterCounter = 0;
							System.out.println();
						}
					}
					System.out.println("\n");
				}
				else if ( option.equals("2") ) {
					System.out.println("Please enter the output file:");
					try {
						BufferedWriter bw = new BufferedWriter( new FileWriter(console.nextLine() ) );
						int letterCounter = 0;
						
						for(String temp: cypherText) {
							letterCounter += temp.length() + 1; // '+ 1' because we are adding a space character
							bw.write(temp + " ");
							if ( letterCounter > 80 ) { // New line after 80 characters
								letterCounter = 0;
								bw.write("\n");
							}
						}
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			else if ( option.equals("-1") ); // Just exit
			else {
				System.out.println("Please enter a valid option.");
			}
		} while ( !option.equals("-1") );
		
	} // encrypt()
	
	
	public static void printTitle(String title) {
		StringBuilder sb = new StringBuilder();
		
		System.out.println(title);
		
		for (int i = 0; i < title.length(); ++i)
			sb.append('-');
		
		System.out.println(sb.toString());
	} // printTitle()
	
} // Menu
