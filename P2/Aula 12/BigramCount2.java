/**
 * BigramCount2
 * 
 * @author Pedro Teixeira
 * Copyright 2017, MIECT - DETI UA
 */
import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import pt.ua.p2utils.HashTable;

public class BigramCount2
{

	public static void main(String[] args) {
		HashTable<HashTable<Integer>> prevWordsCount = new HashTable<>(100000); // Usamos hash table!
		
		for (int a = 0; a < args.length; a++) { // Processa cada ficheiro
			File fin = new File(args[a]);
			analyseText(fin, prevWordsCount); // atualiza contagens de bigramas
		}
		
		printCounts(prevWordsCount);
		//mostFrequent(prevWords_count);
	}

	// Read input text file word by word and count occurences of bigrams
	static void analyseText(File fin, HashTable<HashTable<Integer>> prevWordsCount) {
		try {
			Scanner scf = new Scanner(fin);
			scf.useDelimiter("[^\\p{IsAlphabetic}]+");
			// ^ Isto serve para especificar que o separador de "tokens" no scanner
			// será qualquer sequência de 1 ou mais carateres não alfabéticos.
			// Assim, cada token devolvido por scf.next() é uma palavra no sentido
			// mais convencional: uma sequência composta apenas de letras!

			String prevWord = scf.next().toLowerCase(); // serve para guardar a palavra anterior

			while (scf.hasNext()) { // Processa cada palavra
				String currWord  = scf.next().toLowerCase();
				
				HashTable<Integer> currWordCount;
				
				// if prevWord already exits, updates its HashTable
				if (prevWordsCount.contains(prevWord)) {		
					currWordCount = prevWordsCount.get(prevWord);	// gets prevWord HashTable
					
					// updates prevWord HashTable 
					if (currWordCount.contains(currWord)) 
						currWordCount.set(currWord, currWordCount.get(currWord) + 1);
					else 
						currWordCount.set(currWord, 1);
					
				} else {
					currWordCount = new HashTable<Integer>(10000);
					currWordCount.set(currWord, 1);	
					prevWordsCount.set(prevWord, currWordCount);
				}
				
				prevWord = currWord;
		
			}
			scf.close();
		}
		catch (IOException e) {
			err.printf("ERROR: %s\n", e.getMessage());
			exit(1);
		}
	}

	// Print each key (bigram) and its count
	static void printCounts(HashTable<HashTable<Integer>> counts) {
		String[] keys = counts.keys();
		
		for (String key : keys) {
			HashTable<Integer> temp = counts.get(key);
			//System.out.println(key);
			//System.out.println(temp.toString() + "\n-----------\n");
			System.out.println(key + " -> " + temp);
		} 
	}

	// Find and print the key with most occurrences and its relative frequency. 
	/**
	static void mostFrequent(HashTable<HashTable<Integer>> counts) {
		String[] countKeys = counts.keys();
		System.out.println("Number of keys: " + countKeys.length);
		int max = 0;
		String maxKey = "";

		int sum = 0;

		for (String key : countKeys) {
			int num = counts.get(key));
			if (num > max) {
				max    = num;
				maxKey = key;
			}
			sum += num;
		}

		System.out.printf("Most frequent key: %s (%d/%d = %.2f%%)", maxKey, max, sum, ((max*100.0)/sum));
	}
	*/
}

