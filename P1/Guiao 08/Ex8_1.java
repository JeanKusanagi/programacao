/*
 * Ex8_1.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */

//import static java.lang.System.*;
import java.util.Scanner; 
public class Ex8_1 {
	
	static Scanner ler=new Scanner (System.in);
	
	public static void main (String[] args) {
		//UI inicial
		System.out.printf("Análise de uma frase");
		System.out.printf("\nFrase de entrada -> ");
		String frase=ler.nextLine();
		
		//Invocação dos métodos
		int count1=count_lowerCase(frase);
		int count2=count_upperCase(frase);
		int count3=count_numbers(frase);
		int count4=count_vowels(frase);
		int count5=count_consonants(frase);
		
		//Impressão do resultado
		System.out.printf("\nNúmero de caracteres minúsculos -> %d", count1);
		System.out.printf("\nNúmero de caracteres maiúsculos -> %d", count2);
		System.out.printf("\nNúmero de caracteres numéricos -> %d", count3);
		System.out.printf("\nNúmero de vogais -> %d", count4);
		System.out.printf("\nNúmero de consoantes -> %d", count5);
		
	}
	
	//Método que conta as letras minúsculas na frase
	public static int count_lowerCase (String frase) {
		int count = 0;
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			if (Character.isLowerCase(tmp)) count++;
		}
		return count;
	}
	
	//Método que conta as letras maísculas na frase
	public static int count_upperCase (String frase) {
		int count = 0;
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			if (Character.isUpperCase(tmp)) count++;
		}
		return count;
	}
	
	//Método que conta os números na frase
	public static int count_numbers (String frase) {
		int count = 0;
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			if (Character.isDigit(tmp)) count++;
		}
		return count;
	}

	//Método que conta o número de vogais na frase
	public static int count_vowels (String frase) {
		int count = 0;
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			if (isVowel(tmp)) count++;
		}
		return count;
	}
	
	//Método que conta o número de consoantes na frase
	public static int count_consonants (String frase) {
		int count = 0;
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			boolean vowel=isVowel(tmp);
			if (isConsonant(tmp, vowel)) count++;
		}
		return count;
	}
	
	//Método que verifica se um caracter é uma vogal ou não
	public static boolean isVowel (char c) {
		switch (c) {
		case 'a': return true; 
		case 'e': return true; 
		case 'i': return true; 
		case 'o': return true; 
		case 'u': return true; 
		case 'A': return true; 
		case 'E': return true; 
		case 'I': return true; 
		case 'O': return true; 
		case 'U': return true;
		}
		return false;
	}
	
	//Método que verifica se um caracter é uma consoante ou não (ie, é letra e não é vogal)
	//Outra hipótese: o número de consoantes é igual ao número de caracteres alfabéticos menos o número de vogais.
	public static boolean isConsonant (char c, boolean isVowel) {
		if (!Character.isDigit(c)) {
			if (!isVowel) return true;}
		return false;
	}
}
