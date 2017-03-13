package ie.gmit.sw;

import java.util.*;

public class Menu {
	
	private Scanner console = new Scanner(System.in);
	private Parser parser;
	private String option;
	
	public Menu() {
		
	}

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
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "-1":
					break;
				default:
					System.out.println("Invalid option. Please try again.\n");
			}
		} while(! option.equals("-1"));
		
		System.out.println("Thank you for using my Porta cypher. Have a nice day.\nJavier Mantilla G00329649");
		
		console.close();
	} // start
	
	public void printTitle(String title) {
		System.out.println(title);
		
		for (int i = 0; i < title.length(); ++i)
			System.out.print("-");
		
		System.out.println();
	} // printTitle
	
	private void selectSource() {
		String source;
		
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
				System.out.println("Please type the pathname: ");
				source = console.nextLine();
				parser = new Parser();
				parser.parse(source);
				break;
			case "2":
				System.out.println("Please type the URL: ");
				source = console.nextLine();
				parser = new Parser();
				parser.parse(source);			
				break;
			case "-1":
				break;
			default:
				System.out.println("Invalid option. Please try again.\n");
			}
		} while (! option.equals("-1"));
		
//		Empty the "-1" so it doesn't make us exit from the top menu too.
		console.next();
		
	} // selectSource
}
