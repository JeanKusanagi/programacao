
/*
 * Ex8_7.java
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
public class Ex8_7 {

	static Scanner read = new Scanner (System.in);

	public static void main(String[] args) {
		//UI inicial
		System.out.printf("Capitalize Sentence\n------------------------\n");

		String frase=null;

		do {
			//Leitura da frase
			System.out.printf("\nInput Sentence -> ");
			frase=read.nextLine();

			//Obtenção do acrónimo
			String acronym=capitalizeStr(frase);

			//Impressão do resultado
			System.out.printf("Output Sentence -> %s\n", acronym);
		} while (frase!=null);

	}
	
	//Função que coloca em maiúsculas a primeira letra de cada palavra de uma string (baseado no ex 8.3)
	public static String capitalizeStr (String str) {

		String new_str="";
		boolean in_word = false; 	

		//Percorrer a String caracter a caracter
		for (int i=0; i<str.length(); i++) {

			char tmp=str.charAt(i);

			//Se estiver “dentro” de uma palavra e aparecer um espaço (ou \t ou \n), passa a estar “fora”
			if (in_word) {
				if ((tmp=='\t') || (tmp=='\n') || (tmp==' ')) 
					in_word=false;
				new_str=new_str+tmp;
			}

			//Se estiver “fora” e aparecer outro caráter, passa a estar “dentro” de uma nova palavra
			else if(!in_word) {
				if ((tmp!='\t') && (tmp!='\n') && (tmp!=' ')) {
					in_word=true; 
					new_str=new_str+Character.toUpperCase(tmp);				//A letra passa a ser maíscula
				}	
			}

		}
		return new_str;
	}

}
