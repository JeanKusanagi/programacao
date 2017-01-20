/*
 * Ex8_6.java
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
public class Ex8_6 {
	
	static Scanner read = new Scanner (System.in);
	
	public static void main(String[] args) {
		
		//UI Inicial
		System.out.printf("Tradutor de Português para o dialecto do Alberto Alexandre\n------------------------");
		System.out.printf("\n<Frase vazia termina>");
		
		String frase=null;
		
		do {
			//Leitura da frase
			System.out.printf("\nFrase de entrada -> ");
			frase=read.nextLine();
			
			//Obtenção do acrónimo
			String new_frase=getTranslation(frase);
			
			//Impressão do resultado
			System.out.printf("Frase traduzida -> %s\n", new_frase);
		} while (frase!=null);

	}
	
	//Função para traduzir de português para o dialecto do Alberto
	public static String getTranslation (String str) {
		String new_str="";
		
		for (int i=0; i<str.length(); i++) {
			char tmp=str.charAt(i);
			if (tmp=='L') new_str=new_str+'U'; 				//Se o caracter é L --> U
			else if (tmp=='l') new_str=new_str+'u';			//Se o caracter é l --> u
			else if (tmp=='r' || tmp=='R') {} 				//Se o caracter é r/R --> ignorado
			else {new_str=new_str+tmp;}						//Restantes caracteres mantêm-se
		}
	
		return new_str;
	}

}
