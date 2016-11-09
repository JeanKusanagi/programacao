import java.util.Scanner;

/*
 * Ex5_8.java
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
public class Ex5_8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		
		//Introdução das variáveis
		System.out.printf("Introduza o valor inicial de x:");
		double starting_x=ler.nextDouble();
		double ending_x=getEnd_X(starting_x, "Introduza o valor final de x:");
		
		//Impressão dos resultados
		System.out.printf("------------------------------------\n|     x     |  5x2+10x+3 |7x3+3x2+5x+2|\n");
		for (double i=starting_x; i<=ending_x; i++)
		{System.out.printf("|%10.3f | %10.3f | %10.3f | \n",i, poly3(0, 5, 10, 3, i), poly3(7, 3, 5, 2, i));
		}

	}
	
	public static double getEnd_X (double lim, String message)		//Lê o valor final de x
	{
		Scanner ler = new Scanner(System.in);
		double n=0;
		
		System.out.printf("\n%s ", message);
		n=ler.nextDouble();
		while (n<lim)
		{System.out.printf("\nTem de introduzir um valor maior que %f: ", lim);
		n=ler.nextDouble();
		}
		return n;
	}
	
	public static double poly3 (double a, double b, double c, double d, double x)
	{
		double polinomio;
		polinomio=a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
		return polinomio;
	}
	
}
