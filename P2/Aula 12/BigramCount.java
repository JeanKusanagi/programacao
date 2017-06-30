import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import p2utils.HashTable;

public class BigramCount
{

	public static void main(String[] args) {
		HashTable<Integer> counts = new HashTable<>(100000); // Usamos hash table!

		for (int a = 0; a < args.length; a++) { // Processa cada ficheiro
			File fin = new File(args[a]);
			analyseText(fin, counts); // atualiza contagens de bigramas
		}

		printCounts(counts);
		mostFrequent(counts);
	}

	// Read input text file word by word and count occurences of bigrams
	static void analyseText(File fin, HashTable<Integer> counts) {
		try {
			Scanner scf = new Scanner(fin);
			scf.useDelimiter("[^\\p{IsAlphabetic}]+");
			// ^ Isto serve para especificar que o separador de "tokens" no scanner
			// será qualquer sequência de 1 ou mais carateres não alfabéticos.
			// Assim, cada token devolvido por scf.next() é uma palavra no sentido
			// mais convencional: uma sequência composta apenas de letras!

			String prevWord = null; // serve para guardar a palavra anterior

			while (scf.hasNext()) { // Processa cada palavra
				// palavra atual: é lida do scanner e normalizada:
				
				/* for text hello how are you
				 * bigram 1: hello how
				 * bigram 2: how are 
				 * ...
				 * bigram n second word is bigram n+1 first word
				 * ie word overlapping between consecutive bigrams occurs */
				String currWord  = scf.next().toLowerCase();			
				String word = prevWord + " " + currWord;		// create key
				prevWord = currWord;							// update previous word
				
				// update hash table
				if (counts.contains(word)) {
					counts.set(word, counts.get(word) + 1);
				}
				else {
					counts.set(word, 1);
				}
			}
			scf.close();
		}
		catch (IOException e) {
			err.printf("ERROR: %s\n", e.getMessage());
			exit(1);
		}
	}

	// TODO Test
	// Print each key (bigram) and its count
	static void printCounts(HashTable<Integer> counts) {
		String[] keys = counts.keys();
		
		for (String key : keys) {
			System.out.println(key + " -> " + counts.get(key));
		} 
	}

	// TODO Test
	// Find and print the key with most occurrences
	// and its relative frequency. 
	static void mostFrequent(HashTable<Integer> counts) {
		String[] countKeys = counts.keys();
		System.out.println("Number of keys: " + countKeys.length);
		int max = 0;
		String maxKey = "";

		int sum = 0;

		for (String key : countKeys) {
			int num = counts.get(key);
			if (num > max) {
				max    = num;
				maxKey = key;
			}
			sum += num;
		}

		System.out.printf("Most frequent key: %s (%d/%d = %.2f%%)", maxKey, max, sum, ((max*100.0)/sum));
	}
}
