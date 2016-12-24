/*
 * Ex8_4.java
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
public class Ex8_4 {
	
	static Scanner ler = new Scanner (System.in);
	
	public static void main(String[] args) {
		//UI inicial
		System.out.printf("Verificador de Matrículas\n------------------------\n");
				
		String frase=null;                
		
		do {
			//Leitura da frase
			System.out.printf("\nMatrícula a verificar -> ");
			frase=ler.nextLine();
			
			//Verificar se a matrícula é válida
			boolean match1=matchPattern(frase, "00-00-AA");                 
			boolean match2=matchPattern(frase, "00-AA-00");
			boolean match3=matchPattern(frase, "AA-00-00"); 
						
			//Impressão do resultado
			
			//A matrícula só é válida se corresponder a um dos padrões mas não a outro
			if((match1==true && match2==false && match3==false) || (match2==true && match1==false && match3==false) || (match3==true && match1==false && match2==false)){ 
				System.out.print("A matricula introduzida é válida em Portugal.\n"); 
			} 
			else{ 
				System.out.print("A matricula introduzida não é válida em Portugal.\n"); 
			}
		} while (frase!=null);
		
	}

	//Função que indique se uma dada String str corresponde a um dado padrão pattern.
	public static boolean matchPattern(String str, String pattern) {
		boolean match=true; 
		
		//Se a String introduzida for de dimensões diferentes do padrão, não concide com este
		if(pattern.length()!=str.length()) match=false; 
		
		for(int i=0; i<str.length(); i++){                        
			//Quando se tem uma letra maíscula na posição i do padrão
			if (pattern.charAt(i)=='A'){                                  
				//Se não se tiver uma letra maíuscula, não coincide com o padrão
				if(!Character.isUpperCase((str.charAt(i)))) match=false; 
			} 
			
			//Quando se tem um dígito na posição i do padrão
			else if (pattern.charAt(i)=='0'){ 
				//Se não se tiver um dígito, não coincide com o padrão
				if(!Character.isDigit((str.charAt(i)))) match=false; 
			} 
			
			//Quando se tem um - na posição i do padrão
			else if (pattern.charAt(i)=='-'){ 
				//Se não se tiver um -, não coincide com o padrão
				if(str.charAt(i)!='-') match=false; 
			} 
			
			else{ 
				match=false; 
				break; 
			} 
		
		} 
		return match;
	}
}
