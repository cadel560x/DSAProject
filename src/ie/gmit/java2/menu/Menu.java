package ie.gmit.java2.menu;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
//	Member attributes/fields
/*  
    Static fields
	(Scanner) 'console' is set 'static' in order to be the very same (Scanner) throughout all methods in all 'Menu' child objects
*/
	protected static Scanner console = new Scanner(System.in);
	
/*	
    Instance variables, each object have one of these fields
	'(List<String>) menuItemLabels' stores menu-item labels on each menu
	'menuItemLabels' has modifier 'protected' so each subclass of 'Menu' can have its own instance of '(List<String>) menuItemLabels'
	'title' is the menu title
	'option', stores user selection
*/
	protected List<String> menuItemLabels;
	private String title;
	private String option;
	
	
	
	
//	Getters & setters
	public String getTitle() {
		return title;
	}	
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	

	public List<String> getMenuItemLabels() {
		return menuItemLabels;
	}


	public void setMenuItemLabels(List<String> menuItemLabels) {
		this.menuItemLabels = menuItemLabels;
	}
	
	
	public static Scanner getConsole() {
		return console;
	}


	public static void setConsole(Scanner console) {
		Menu.console = console;
	}


	public String getOption() {
		return option;
	}
	
	
	public void setOption(String option) {
		this.option = option;
	}
	
	
	
	
//	Other methods
	public void print() {
		
		printTitle();
		for (String menuItem: menuItemLabels) {
//			I don't like a new line after a ': ', so I use 'System.out.print()'
			if ( menuItem.endsWith(": ")  )
				System.out.print(menuItem);
			else
//				Now it can have a new line!
				System.out.println(menuItem);
		}
		
		setOption(console.nextLine());
		System.out.println();
		
	} // print
	
	
	public void printTitle() {
		
		StringBuilder sb = new StringBuilder();
		
		System.out.println( getTitle() );
		
		for (int i = 0; i < title.length(); ++i)
			sb.append('-');
		
		System.out.println( sb.toString() );
		
	} // printTitle
	
	
	public static void exceptionHandler(Exception e) {
		
		System.err.println("\nPlease send an email to G00329649@gmit.ie with the following information: ");
		e.printStackTrace();
		
	} // exceptionHandler
	
	
} // class Menu