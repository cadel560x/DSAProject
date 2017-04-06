package ie.gmit.sw;

import java.util.*;

import ie.gmit.java2.parser.*;

public class Runner {
//	Member attributes/fields
	private static Menu menu = new Menu();
	private static String option;
	
	
	
//	'main' entry point
	public static void main(String[] args) {
//		Porta2 porta2 = new Porta2();
//		porta2.init();
//		Scanner console = new Scanner(System.in);
		do {
			option = Menu.printMainMenu();
			
			switch(option) {
				case "1":
					selectSource();
//					'selectSource' displays a submenu which sets 'option' as '-1' to finish,
//					but here, in the parent menu we don't want to exit yet. So 'option' is reset
					option = "";
					break;
				case "2":
					Menu.printTitle("Key setup");
					System.out.print("Enter key: ");
					portaCipher.setKey(console.nextLine().toUpperCase());
					System.out.println(); // Just some nice formatting
					break;
				case "3":
					Menu.printTitle("Encrypt");
					encrypt();
					break;
				case "4":
					Menu.printTitle("Decrypt");
					break;
				case "-1":
					break; // Just exit
				default:
					System.out.println("Invalid option. Please try again.\n");
			}
		} while( !option.equals("-1"));
		
		System.out.println("Thank you for using my Porta cypher. Have a nice day.\nJavier Mantilla G00329649");
//		
//		
//		String plainWord = "HOLA";
//		Porta cipher = new Porta();
//		String cypherWord = cipher.encode(plainWord);
//		
////		Print out encrypted text
//		System.out.println(cypherWord);
////		Print out decrypted text
//		System.out.println(cipher.decode(cypherWord));
//		
//		Map<Character, Character> map1 = new HashMap<>();
////		Map<MapKey, Map<Character , Character >> m = new HashMap<>();
//		Map< Character, Map<Character , Character> > tableau = new HashMap<>();
//		
//		map1.put('a','n');
//		map1.put('b','o');
//		map1.put('c','p');
//		map1.put('d','q');
//		map1.put('e','r');
//		map1.put('f','s');
//		map1.put('g','t');
//		map1.put('h','u');
//		map1.put('i','v');
//		map1.put('j','w');
//		map1.put('k','x');
//		map1.put('l','y');
//		map1.put('m','z');
//		map1.put('n','a');
//		map1.put('o','b');
//		map1.put('p','c');
//		map1.put('q','d');
//		map1.put('r','e');
//		map1.put('s','f');
//		map1.put('t','g');
//		map1.put('u','h');
//		map1.put('v','i');
//		map1.put('w','j');
//		map1.put('x','k');
//		map1.put('y','l');
//		map1.put('z','m');
//		
//		
//		Character a = new Character('a');
//		Character b = new Character('b');
//		Character c = new Character('c');
//		Character d = new Character('d');
//		Character e = new Character('e');
//		Character f = new Character('f');
//		Character g = new Character('g');
//		Character h = new Character('h');
//		Character i = new Character('i');
//		Character j = new Character('j');
//		Character k = new Character('k');
//		Character l = new Character('l');
//		Character m = new Character('m');
//		Character n = new Character('n');
//		Character o = new Character('o');
//		Character p = new Character('p');
//		Character q = new Character('q');
//		Character r = new Character('r');
//		Character s = new Character('s');
//		Character t = new Character('t');
//		Character u = new Character('u');
//		Character v = new Character('v');
//		Character w = new Character('w');
//		Character x = new Character('x');
//		Character y = new Character('y');
//		Character z = new Character('z');
		

//		MapKey A = new MapKey(1);
//		MapKey B = new MapKey(1);
		
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(c.hashCode());
//		System.out.println(d.hashCode());
//		System.out.println(e.hashCode());
//		System.out.println(f.hashCode());
//		System.out.println(g.hashCode());
//		System.out.println(h.hashCode());
//		System.out.println(i.hashCode());
//		System.out.println(j.hashCode());
//		System.out.println(k.hashCode());
//		System.out.println(l.hashCode());
//		System.out.println(m.hashCode());
//		System.out.println(n.hashCode());
//		System.out.println(o.hashCode());
//		System.out.println(p.hashCode());
//		System.out.println(q.hashCode());
//		System.out.println(r.hashCode());
//		System.out.println(s.hashCode());
//		System.out.println(t.hashCode());
//		System.out.println(u.hashCode());
//		System.out.println(v.hashCode());
//		System.out.println(w.hashCode());
//		System.out.println(x.hashCode());
//		System.out.println(y.hashCode());
//		System.out.println(z.hashCode());
		
		
//		tableau.put(a, map1);
//		tableau.put(b, map1);
//		tableau.put(c, map2);
//		tableau.put(d, map2);
//		tableau.put(e, map3);
//		tableau.put(f, map3);
//		tableau.put(g, map4);
//		tableau.put(h, map4);
//		tableau.put(i, map5);
//		tableau.put(j, map5);
//		tableau.put(k, map6);
//		tableau.put(l, map6);
//		tableau.put(m, map7);
//		tableau.put(n, map7);
//		tableau.put(o, map8);
//		tableau.put(p, map8);
//		tableau.put(q, map9);
//		tableau.put(r, map9);
//		tableau.put(s, map10);
//		tableau.put(t, map10);
//		tableau.put(u, map11);
//		tableau.put(v, map11);
//		tableau.put(w, map12);
//		tableau.put(x, map12);
//		tableau.put(y, map13);
//		tableau.put(z, map13);

//		Character name = new Character('Z');
//		System.out.println(name.hashCode());
		
//		StringBuilder sb = new StringBuilder();
//		
//		
//		String plain = "HolaMundo";
//		String key ="abab";
//		Character plainChar;
//		Character keyChar;
//		Character enChar;
//		
//		String plain_lowerCase = plain.toLowerCase();
//		for(int index = 0; index < plain.length(); index++) {
//			
//			plainChar = new Character(plain_lowerCase.charAt(index));
//			keyChar = new Character(key.charAt(index));
////			System.out.println(keyChar);
//			
//			enChar = tableau.get(keyChar).get(plain_lowerCase.charAt(index));
//			
//			System.out.println("keyChar: " + keyChar + "\tplainChar: " + plainChar + "\tenChar: " + enChar);
//			
//
//			sb.append(enChar.charValue());
//		}
		
		
		
//		System.out.println(tableau.get(a).get('a'));
//		System.out.println(tableau.get(b).get('a'));
	} // main
	
	
	private static void selectSource() {
//		String source;
		
		do {
//			option = Menu.printInputSource();
			
			
			switch(Menu.printInputSource()) {
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
		} while ( !option.equals("-1"));
		
//		Empty the "-1" so it doesn't make us exit from the top menu too.
//		option = "";
		
	} // selectSource()
	
} // Runner
