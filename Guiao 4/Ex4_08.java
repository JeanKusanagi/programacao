/*
 * Ex4_08.java
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

import java.util.*; 
public class Ex4_08 {
	public static void main (String args[]) {

		Scanner ler= new Scanner(System.in); 

		//Obtenção do limite inferior e superior do intervalo [A,B]: a e b, respectivamente
		System.out.printf("Numeros impares num intervalo de numeros inteiros\nIntroduza o valor inteiro, limite inferior do intervalo (A): ");
		int a=ler.nextInt();
		int b=getIntLim_Min("Introduza o valor inteiro, limite superior do intervalo (B, B>A): ", a);

		//Imprimir os números impares entre A e B (números cujo resto da divisão inteira por 2 é diferente de 0, ie num%2!=0)
		System.out.printf("Numeros impares no intervalo [%d,%d]: \n", a,b);
		for (int i=a; i<=b; i++) {
			if (i%2!=0) System.out.println(i);
		}
	}	

	public static int getIntLim_Min (String message, int lim_min)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<lim_min) 
		{System.out.printf("\nTem de introduzir um numero inteiro maior do que %d: ", lim_min);
		n=ler.nextInt();}
		return n;
	}


}
