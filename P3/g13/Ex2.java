package g13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import g13.ex2.CountBigrams;

/**
 * Ex2
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */
public class Ex2 {

	public static void main(String[] args) throws FileNotFoundException {

		long initialTime = System.currentTimeMillis();

		// Read file
		/*
		System.out.print("File Path -> ");
		Scanner sc = new Scanner(System.in);
		String path = sc.next();
		 */

		String path = "Policarpo.txt";
		Scanner scFile = new Scanner(new File(path));

		//Scanner scFile = new Scanner("a Construa um programa que leia um ficheiro de texto e que conte todos os pares de palavras encontrados no ficheiro e o número de ocorrências de cada par.");

		CountBigrams c = new CountBigrams();
		String w1;
		do {
			w1 = scFile.next().replaceAll("\\t|,|\\.|:|'|‘|’|;|\\?|!|\\*|\\{|}|=|\\(|\\)|\\+|&|/|\\[|]|“|”|\"|'|-", "");
		} while (w1.length() < 3);
		//System.out.println(w1);

		while (scFile.hasNext()) {		
			String w2;
			do {
				w2 = scFile.next().replaceAll("\\t|,|\\.|:|'|‘|’|;|\\?|!|\\*|\\{|}|=|\\(|\\)|\\+|&|/|\\[|]|“|”|\"|'|-", "");
			} while (w2.length() < 3);

			//System.out.println(w2);
			//System.out.println("Chosen w1: " + w1 + " , w2: " + w2);
			if (w1.length() >= 3 && w2.length() >= 3) c.addBigram(w1, w2);

			w1 = w2;
		}

		System.out.println(c);
		scFile.close();
		System.out.println("Executed in " + ((System.currentTimeMillis() - initialTime)) + " milliseconds");

	}
}
