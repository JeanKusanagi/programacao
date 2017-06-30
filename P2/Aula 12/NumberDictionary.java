import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import p2utils.HashTable;
/**
 * NumberDictionary <p>
 * Numbers are double so this program works with any real number. 
 * To have a working version of the program that was asked, just replace: <p>
 * double -> int <p>
 * Double.parseDouble -> Integer.parseInt <p>
 * Double -> Integer <p>
 * result = 0.0 -> result = 0 <p>
 * aux = 0.0    -> aux = 0 <p>
 * 
 * Copyright 2017, MIECT - DETI UA
 * @author Pedro Teixeira
 * 
 */

public class NumberDictionary {

	// Fields
	/** {@code HashTable} dictionary with a univocal correspondence between the number spelling (the key) and the number (the element).
	 */
	HashTable<Double> translator;

	/**
	 * Constructor.
	 * Uses the file "@filepath" to create a number dictionary.
	 * @param filePath
	 */
	NumberDictionary(String filePath) {
		translator = new HashTable<>(35);

		// Get info from file
		File inFile = new File(filePath);
		if (!inFile.exists() || !inFile.canRead()) {
			System.err.println("Error: " + inFile.getPath() + "Number file is not valid.");
			System.exit(1);
		}

		// Use info from file to create a dictionary
		try {
			Scanner scFile = new Scanner(inFile);
			while(scFile.hasNextLine()) {
				// Read line
				String line = scFile.nextLine();
				String[] lineContents = line.split(" - ");

				// key is the text (eg "eight"), element is the number (eg 8)
				try{
					translator.set(lineContents[1], Double.parseDouble(lineContents[0]));		
				} catch (NumberFormatException e) {
					System.err.println("Error: " + lineContents[0] + " is not a valid number. It will be ignored.");
				}
			}
			scFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("I/O Error while reading number file");
			System.exit(2);
		}

	}

	/**
	 * TODO DOUBLE VERSION: NOT FINISHED
	 * Translates word by word a real number, from its word representation, to its decimal representation.
	 */
	public void getSuperSmartTranslation() {
		// Read list of numbers from scanner
		Scanner scConsole = new Scanner(System.in);
		System.out.printf("Insert a number: \n-> ");
		while (scConsole.hasNextLine()) {
			
			String   rawKeys  = scConsole.nextLine();
			String[] keys     = rawKeys.split("-| ");			// treats "num1-num2" situation

			double result = 0;
			double aux    = 0;									// to accumulate multiplications until a new addition
			boolean negative = false;

			// Translate
			for (String str : keys) {

				if (translator.contains(str)) {
					double num = translator.get(str);
					System.out.println("num=" + num + " aux=" + aux + " result=" + result);
					//System.out.println("num, according to dictionary, is = "+num);
					//if (aux == 0) aux = num;
					//if (num >= 1.0) {
						if (num < aux && aux >= 1.0 && num >= 1.00) {
							result += aux;
							aux     = num; 
						}
						// else multiply
						
					//} else {
						//System.err.println("DETECTED DECIMAL NUMBER");
						else if (num > aux && aux < 1.0) {
							result += aux;
							aux     = num; 
						}
						
						// else multiply
						else {
							// in case the number starts by ten/hundred/thousand (since num*0 = 0)
							if (aux == 0) aux = num;
							else aux *= num;
						}
					//}

				}

				// Detects if it's a negative number
				else if (str.toLowerCase().equals("minus")) {
					negative = true;
				}

				/* Detects if String str is not valid. str is not valid if
				 * it's not a number (ie it's not in the dictionary translator) 
				 * and it's not "and/AND" or no input) */
				else if (!str.toLowerCase().equals("and") && !str.equals("")) {
					System.err.print("\nNumber text " + str + " does not exist in table");
					System.exit(3);
				}
			}

			// OUTPUT
			result += aux;
			if (negative) result *= -1;
			//System.out.println(rawKeys + " -> " + result + " ");
			System.out.printf("%s -> %.4f\n\n", rawKeys, result);
			System.out.printf("Insert a number: \n-> ");
		}

	}
}
