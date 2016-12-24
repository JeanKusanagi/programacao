/*
 * Ex8_5.java
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
public class Ex8_5 {

	static Scanner ler = new Scanner (System.in);

	public static void main (String[] args) {
		//UI inicial
		System.out.printf("Conversor de números inteiros para outras bases\n------------------------\n");
		System.out.printf("<0 termina>\n");
		int num=0;                

		do {
			//Leitura do número
			System.out.printf("\nNúmero inteiro a converter -> ");
			num=ler.nextInt();
			
			//Leitura da base a converter para
			System.out.printf("Base a converter para --> ");
			int base=ler.nextInt();
			
			//Valida a base (base>=2 e <=10) 
			while (base<2 || base>10) { 
				System.out.print("\nBase inválida. Por favor introduza uma base entre 2 e 10: ");
				base = ler.nextInt(); 	
			}
			
			//Obtenção do número na nova base
			String new_num=numToBase (num,base);
			
			//Impressão do resultado
			System.out.printf("Número %d na base %d : %s\n", num, base, new_num); 

		} while (num!=0);

	}
	
	public static String numToBase (int n, int b) {
		String tmp="";
		String new_num="";
		
		while(n>=b){ 
			tmp+= n % b; 	//Adiciona o resto da divisão à String
			n=n/b; 			//O quociente passa a ser o dividendo
		} 
		
		//Adiciona o último resto (quando n<b)
		tmp+=n;
		
		//Obtém o novo número (todos os restos de divisão inteira por ordem decrescente)
		for(int j=tmp.length()-1; j>=0 ; j--){ 
			new_num += tmp.charAt(j); 
		} 
		
		return new_num;
	}
}
