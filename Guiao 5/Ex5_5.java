/*
 * Ex5_5.java
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
public class Ex5_5 {
	public static void main(String[] args) {
	
	int larg=getValue("a largura do retangulo");
	int alt=getValue("a altura do retangulo");

	//Impressão do retangulo

	//Primeira linha
	printNtimes(larg,"*");

	//Em cada uma das alt-2 colunas
	for (int j=(alt-2); j>0; j--)
	{

		//Fazer o primeiro elemento da linha 
		System.out.printf("\n*");

		//Fazer os restantes elementos da linha i=1 pois já existe um * no inicio da coluna
		for (int i=1; i<(larg-1); i++) 
		{System.out.print(" ");}

		//Terminar a linha e começar uma nova coluna
		System.out.printf("*");
	}


	//Última linha
	System.out.println(" ");
	printNtimes(larg,"*");

	}

	public static int getValue (String message)						//Introdução da largura e da altura
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("\nIntroduza %s: ",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
		n=ler.nextInt();}
		return n;
	}
	
	public static void printNtimes (int vezes, String message)		//Função para impressão de uma mensagem X vezes
	{
		for (int i=1; i<=vezes; i++)
		{
			System.out.printf("%s",message);
		}
	}
}
