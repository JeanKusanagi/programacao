/*
 * Ex05_5.java
 * 
 * Copyright 2017 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ex05_5_b {

	public static void main(String[] args) {
		// Get file
		File in_file = null;

		// Get column number
		int col = 0;

		// through args
		if (args.length >= 2) {
			in_file = new File (args[0]);
			try{
				col = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) { // protect against argument that can't be parsable to integer values		
				System.err.printf("Usage: java [-ea] Ex05_5 <input file> <column number>");
				System.exit(1);
			}

		}

		// or console
		else {
			Scanner read = new Scanner (System.in);
			System.out.printf("\nInput file: ");
			String path = read.nextLine();
			in_file = new File (path);
			System.out.printf("\nColumn number: ");
			col = read.nextInt();
		}

		// Verifies file
		verifyFile(in_file);

		printCols(in_file, col);
	}

	/** Verifies if the given file exists, is or not a directory, is or not a file and can be read 
	 * @param f - File to be verified
	 */
	public static void verifyFile (File f){
		// must exist
		if (!f.exists()) {
			System.err.printf("ERROR: Input file %s does not exist", f.getName());
			System.exit(1);
		}

		// must be a normal file
		if (f.isDirectory()) {
			System.err.printf("ERROR: Input file %s is a directory", f.getName());
			System.exit(1);
		}

		if (!f.isFile()) {
			System.err.printf("ERROR: Input file %s is not a valid file", f.getName());
			System.exit(1);
		}

		// must be readable
		if (!f.canRead()) {
			System.err.printf("ERROR: Input file %s can't be read", f.getName());
			System.exit(1);
		}
	}

	/** 
	 * Prints the contents in the column col for all the lines of the given file
	 * @param f - File to be read
	 * @param col - Number of the column wanted 
	 */
	public static void printCols (File f, int col) {
		// Scanner
		Scanner in 	= null;
		try{
			in  = new Scanner (f).useDelimiter("[ \t\n]");
		} catch (FileNotFoundException e) {
			System.err.printf("Input file not accessible. Check if they exist and/or are readable.");
			System.exit(1);
		}
		
		try{
			// For each line
			while(in.hasNextLine()){  

				processLine(in.nextLine(), col);
				processLine(col, in.nextLine());
			}
		} catch (NoSuchElementException e) {
			System.err.printf("The input file doesn't have more lines");
			System.exit(1);
		}
	}

	private static void processLine(String line, int col){	
		Scanner sc2=new Scanner(line);


		//System.out.println("LINE read is " + line);

		int pos=1;
		String word;

		while (sc2.hasNext() && pos <=col) {
			word = sc2.next();

			//System.out.println("WORD is >" + word+"<");

			if (col==pos) {
				System.out.println(word); 
				//break;
			}
			pos++;

		}

		if (pos < col)
			System.out.println();
	}
	
	public static void processLine(int col, String line) {
		String[] words = line.split(" ", col+1);	//there's no need to split the whole string..
		
		if (words.length >= col) {			//if the said element exists, prints it
			System.out.println(words[col-1]);
		}
		else {								//else prints spaces
			System.out.println(" ");
		}
		
	}

}