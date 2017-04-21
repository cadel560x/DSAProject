Javier Mantilla
G00329649
2017

Data Structures and Algorithms Project

This application perform encryption/decryption using the Porta cipher, that is a substitution cipher type

Structure:
This application was built using Object Oriented concepts like:
Interfaces, like in class CipherApp that implements interface Runnable.
Inheritance using abstract classes, like abstract classes Menu and Parser.
Composition, like in class CipherApp.
Exception Handling, like in method 'exceptionHandler(Exception e)' in class Parser.
Packaging, there are three packages in this application: ie.gmit.sw, ie.gmit.java2.parser, ie.gmit.java2.menu
Static instance variables (like 'console' in Menu class ) and static methods (like 'getConsole()' in Menu class ).

Notes:
Complexity analysis is done in Porta class, that has methods dependents on data input. Analysis is written in the comments.
The complexity of other methods from other classes are dependent on which method from class Porta they call.

This application initialize a tableau before any encryption/decryption is performed, executing computations to obtain
the substitutions for each character according to each key pair. See init() method in Porta class.
After the tableau initialization is done, no more computations are performed. Substitution characters
are obtained getting values from 'Map' data structures.

Instructions:
The entry point for execution is at the public static void main(String[] args) method in the abstract class 'Runner'.
Execution should be performed from there.
A 'MainMenu' shows up as follows:

Porta Cipher - Javier Mantilla G00329649
----------------------------------------
1. Set up encryption key
2. Encrypt
3. Decrypt
Press -1 to exit:

Items are self explanatory. User can go back to the previous menu or exit pressing '-1'.
Either 'Encrypt' or 'Decrypt' items offer the options to process text from
a file, url or 'stdin'/keyboard:

Source feed
-----------
Please choose a source feed:  
1. File
2. URL
3. Standard input (keyboard)
Press -1 to go back: 

Features:
-This application computes the amount of time consumed in each cipher/decipher operations
-Porta class has an internal array list where it stores the processed input.
-Porta class has a method to output the result text to the 'stdout' in a format of 80 characters wide lines.
-This application can encrypt/decrypt from the 'stdin' or directly from the keyboard.
-This application can parse URLs, if no protocol is specified, http is assumed
 i.e. www.google.com is entered, it is interpreted as http://www.google.com
-This application uses 'Parser' class and 'Menu' class from the previous semester that are in packages 'ie.gmit.java2.parser' and
'ie.gmit.java2.menu' respectively.
-This application gives the option to display the processed text to the 'stdout' or screen.
-This application gives the option to save the processed text into a text file.