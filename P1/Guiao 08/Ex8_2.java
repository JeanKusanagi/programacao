/*
 * Ex8_2.java
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
public class Ex8_2 {

	static Scanner ler=new Scanner (System.in);

	public static void main (String[] args) {
		//UI inicial
		System.out.printf("Criação de acrónimos\n------------------------\n");
		
		String frase=null;
		
		do {
			//Leitura da frase
			System.out.printf("\nNome de entrada -> ");
			frase=ler.nextLine();
			
			//Obtenção do acrónimo
			String acronym=getAcronym(frase);
			
			//Impressão do resultado
			System.out.printf("Acrónimo -> %s\n", acronym);
		} while (frase!=null);
		
	}

	//Função que devolve um acrónimo para um dado nome
	public static String getAcronym (String frase) {
		String ac= "";		//String com o acrónimo

		//Para cada carácter da frase
		for (int i=0; i<frase.length(); i++) {
			char tmp=frase.charAt(i);
			//Se for uma maíscula, o carácter é adicionado à String ac  
			if (Character.isUpperCase(tmp)) ac=ac+tmp;
		}
		return ac;
	}
}
