/*
 * Ex3_11.java
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
public class Ex3_11 {
	public static void main(String[] args) {

		//Declaração e inicialização de variáveis
		int num=0, count_par=0;

		//Leitura inicial de um numero inteiro
		System.out.printf("Introduza uma lista de numeros reais terminada pelo valor 0: ");
		num=getIntPos("");

		//Leitura de uma lista de numeros 
		while (num!=0) 									//Executa o ciclo até aparecer um 0
		{
			if (num%2==0) {count_par++;}				//Se for par
			num=getIntPos("");
		}

		//Apresentação dos resultados
		if (count_par==0) {System.out.printf("\nA sequencia de numeros e exclusivamente constituida por numeros impares.\n");}
		if (count_par!=0) {System.out.printf("\nA sequencia de numeros nao e exclusivamente constituida por numeros impares.\n");}

		}

	//Função para leitura de um número inteiro	
	public static int getIntPos (String message)									
		{
			Scanner ler = new Scanner(System.in);
			System.out.printf("\n%s ",message);
			int n=ler.nextInt();
			while (n<0) 
			{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
			n=ler.nextInt();}
			return n;
		}
	}
