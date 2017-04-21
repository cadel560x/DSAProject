package ie.gmit.java2.menu;

import java.util.ArrayList;

public class InputFeedMenu extends Menu {
//	 Constructor
	public InputFeedMenu() {
		
		setTitle("Source feed");
		
		menuItemLabels = new ArrayList<>();
		menuItemLabels.add("Please choose a source feed:  ");
		menuItemLabels.add("1. File");
		menuItemLabels.add("2. URL");
		menuItemLabels.add("Press -1 to go back: ");
		
	}

	
	
	
//	 Other methods
	public boolean showFeed() {
		
		do {
			System.out.print("Show parsed contents (y/n)? ");
			setOption( console.nextLine() );
			
			switch( getOption() ) {
			case "y":
			case "Y":
				return true;
			case "n":
			case "N":
				System.out.println();
				return false;
			default:
					System.out.println("Please enter 'y' or 'n'");
			}
		} while ( true) ;
		
	} // showFeed

	
} // class InputFeedMenu
