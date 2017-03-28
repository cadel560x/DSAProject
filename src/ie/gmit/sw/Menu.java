package ie.gmit.sw;

import java.util.*;

public class Menu {
//	Member attributes/fields	
	private Scanner console;// = new Scanner(System.in);
	private Porta portaCipher;// = new Porta();
	private Parser parser;
	private String option;
	
	
	
	
//	Constructors
	public Menu() {
		console = new Scanner(System.in);
		portaCipher = new Porta();
	}
	
	
	
	
//	Other methods
	public void start() {
		
		do {
			printTitle("Porta Cipher - Javier Mantilla G00329649");
			System.out.println("1. Select source feed");
			System.out.println("2. Set up encryption key");
			System.out.println("3. Encrypt");
			System.out.println("4. Decrypt");
			System.out.print("Press -1 to exit: ");

			option = console.nextLine();
			
			System.out.println();
			
			switch(option) {
				case "1":
					selectSource();
//					'selectSource' displays a submenu which sets 'option' as '-1' to finish,
//					but here, in the parent menu we don't want to exit yet. So 'option' is reset
					option = "";
					break;
				case "2":
					printTitle("Key setup");
					System.out.print("Enter key: ");
					portaCipher.setKey(console.nextLine().toUpperCase());
					System.out.println(); // Just some nice formatting
					break;
				case "3":
					printTitle("Encrypt");
					encrypt();
					break;
				case "4":
					printTitle("Decrypt");
					break;
				case "-1":
					break; // Just exit
				default:
					System.out.println("Invalid option. Please try again.\n");
			}
		} while(! option.equals("-1"));
		
		System.out.println("Thank you for using my Porta cypher. Have a nice day.\nJavier Mantilla G00329649");
		
		console.close();
	} // start()
	
	
	private void selectSource() {
//		String source;
		
		do {
			printTitle("Source feed");
			System.out.println("Please choose a source feed: ");
			System.out.println("1. File");
			System.out.println("2. URL");
			System.out.print("Press -1 to go back: ");
			
			option = console.nextLine();
			
			System.out.println();
			
			switch(option) {
			case "1":
				System.out.print("Please type the pathname: ");
//				source = console.nextLine();
				parser = new Parser(console.nextLine());
//				parser = new Parser(source);
				System.out.println("File '" + parser.getFile().getName() + "' parsed and loaded into memory\n");
//				Nothing else to do here. Go to parent menu.
				option = "-1";
				break;
			case "2":
				System.out.println("Please type the URL: ");
				//source = console.nextLine();
				parser = new Parser(console.nextLine());	
//				Nothing else to do here. Go to parent menu.				
				option = "-1";
				break;
			case "-1":
				break; // Just exit
			default:
				System.out.println("Invalid option. Please try again.\n");
			}
		} while (! option.equals("-1"));
		
//		Empty the "-1" so it doesn't make us exit from the top menu too.
//		option = "";
		
	} // selectSource()
	
	
	private void encrypt() {
		// TODO Auto-generated method stu
		List<String> cypherText = new ArrayList<>();
		long startTime;
		
		System.out.println("Encrypting '" + parser.getFile().getName() + "'");
		
		startTime = System.currentTimeMillis();
		for (String temp: parser.getFileContents()) {
			cypherText.add(portaCipher.encode(temp));
//			System.out.println(portaCipher.encode(temp));
		}
		
		System.out.println("Running time (ms): " + (System.currentTimeMillis() - startTime + "\n"));
		
		printTitle("Output Cyphertext");
		System.out.println("1. To screen");
		System.out.println("2. To file");
		option = console.nextLine();
		
		if (option.equals("1")) {
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
		
	} // encrypt()
	
	
	private void printTitle(String title) {
		StringBuilder sb = new StringBuilder();
		
		System.out.println(title);
		
		for (int i = 0; i < title.length(); ++i)
			sb.append('-');
		
		System.out.println(sb.toString());
	} // printTitle()
	
} // Menu
