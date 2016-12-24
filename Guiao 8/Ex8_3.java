/*
 * Ex8_3.java
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
import java.util.Scanner; 
public class Ex8_3 {

	static Scanner ler=new Scanner (System.in);

	public static void main (String[] args) {
		//UI inicial
		System.out.printf("'Contador' de palavras\n------------------------\n");
		
		String frase=null;
		
		do {
			//Leitura da frase
			System.out.printf("\nFrase de entrada -> ");
			frase=ler.nextLine();
			
			//Obtenção do acrónimo
			int count=getWordCount(frase);
			
			//Impressão do resultado
			System.out.printf("Número de palavras na frase -> %d\n", count);
		} while (frase!=null);
		
	}

	//Função que devolve um acrónimo para um dado nome
	public static int getWordCount (String frase) {
		int count=0;
		boolean in_word=false; 	
		
		//Percorrer a String caracter a caracter
		for (int i=0; i<frase.length(); i++) {
			
			char tmp=frase.charAt(i);
			
			//Se estiver “dentro” de uma palavra e aparecer um espaço (ou \t ou \n), passa a estar “fora”
			if (in_word) {
				if ((tmp=='\t') || (tmp=='\n') || (tmp==' ')) 
					in_word=false; 
			}
			
			//Se estiver “fora” e aparecer outro caráter, passa a estar “dentro” e conta mais uma palavra.
			else if(!in_word) {
				if ((tmp!='\t') && (tmp!='\n') && (tmp!=' ')) {
					in_word=true; 
					count++;
				}	
			}
			
		}
		return count;
	}
}