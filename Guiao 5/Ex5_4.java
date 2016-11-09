
/*
 * Ex5_4.java
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
public class Ex5_4 {
	public static void main(String[] args) {

		System.out.printf("Calculo do fatorial dos numeros entre M e 1");
		int m=getM(0, "M");
		
		for (int g=m; g>=1; g--)
		{int valor=fact(g);
		System.out.printf("\n%d!=%d", g, valor);}

	}

	public static int getM (int lim, String message)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("\nIntroduza %s: ",message);
		int n=ler.nextInt();
		while (n<=lim) 
		{System.out.printf("\nTem de introduzir um numero inteiro maior que %d: ", lim);
		n=ler.nextInt();}
		return n;
	}

	public static int fact (int n)
	{
		int factorial=1;
		for (int i=1; i<=n; i++)
		{factorial=factorial*i;}
		return factorial;
	}

}