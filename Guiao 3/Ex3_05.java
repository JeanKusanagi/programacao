/*
 * Ex5.java
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
 * 
 */

import java.util.Scanner;
public class Ex3_05 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		//Variáveis
		int num=0;          //Variável que contém o número a analisar
		//int primo=0;		//Variável que toma valor 1 sse o número é primo e 0 sse o número não é primo	
		
		//Introdução do número
		System.out.print("Introduza um numero inteiro positivo: \n");
		num=ler.nextInt();
		
		//Validação da entrada (num inteiro positivo)
		while (num<=0)
		 {
			System.out.print("Tem de introduzir um numero inteiro positivo. \n");
			num=ler.nextInt();
		}
		
		
		for (int i=2; i<num; i=i+1) 
			{
				if (num % i == 0) 
				{
					System.out.printf("\nNumero %d : NAO PRIMO\n", num);
					return; //?
				}
				else if (num % i != 0) {System.out.printf("\nNumero %d : PRIMO\n", num);}
			
			//System.out.printf("\nNumero %d : PRIMO\n", num);
		}
		}
	}
	

