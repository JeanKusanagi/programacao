/*
 * Ex4_04.java
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

public class Ex4_04 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int n=0;          
		double soma=0, razao=0;
		
		//Introdução do N
		System.out.printf("Calculo da soma dos primeiros N termos da serie de Leibnitz\n------------\nIntroduza um numero inteiro N: \n");
		n=ler.nextInt();
		
		//Validação do n
		while (n<1) 
		{System.out.printf("Tem de introduzir um n tal que N>0\n");
		n=ler.nextInt();}
		
		//Cálculo		
		for (int i=0; i<n; i++)
		{
		soma=soma+((Math.pow(-1,i))/(2*i+1));
						
		}
		
		//Comparação (razão) com o valor de pi/4
		razao=(soma/(Math.PI/4))*100;
		
		//Apresentação dos resultados
		System.out.printf("\nSoma dos primeiros %d termos da serie de Leibnitz=\n%1.15f\n", n, soma);
		System.out.printf("\nCorrespondente a %f%% do valor de pi/4.\n", razao);
		
		}
	}


