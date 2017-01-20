/*
 * Ex5_9.java
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

public class Ex5_9 {

	public static void main (String args[]) {

		int m=getM("Lista dos primos entre 1 e M\nIntroduza o valor M, positivo:");

		for (int g=1; g<=m; g++)
		{
			if (isprime(g)) {System.out.printf("\n%d\n", g);}
		}

	}

	public static int getM (String message)		//Função para obter um número inteiro positivo
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("\n%s ",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
		n=ler.nextInt();}
		return n;
	}

	public static boolean isprime (int n)		//Função para determinar se um número inteiro n é primo
	{
		for (int i=2; i<n; i=i+1)				//A função vai dividindo o número n por números inteiros i sucessivamente maiores até que i<n
		{
			if (n % i == 0) 					//Se for divisível, não é primo
			{
				return false;
			}
		}
		return true;							//Senão, por defeito, será primo
	}	
	
}
