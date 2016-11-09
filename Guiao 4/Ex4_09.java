/*
 * Ex4_09.java
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
public class Ex4_09 {

	public static void main(String[] args) {
		
		//Valores de entrada
		int n=getIntRange("Obtencao dos primeiros N numeros pares e sua soma\nIntroduza um numero inteiro positivo N (N<=1000): ",1000,0);
		int soma=0;
		
		//Impressão dos primeiros N números pares
		System.out.printf("\nPrimeiros %d numeros inteiros positivos:", n); 
		for (int i=1; i<=n*2; i++) {				//ie verificar entre n*2 números, quais são números pares
			if (i%2==0) {
				System.out.printf(" %d", i); 
				soma=soma+i;
			}
		}
		
		System.out.printf("\nSoma dos primeiros %d numeros inteiros positivos= %d", n, soma);
	}
	
	public static int getIntRange (String message, int lim1, int lim2)
	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;
		
		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}
		
		System.out.printf("%s", message);
		n=ler.nextInt();
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nTem de introduzir %s pertencente ao intervalo [%d, %d] ", message, lim_inferior, lim_superior);
		n=ler.nextInt();
		}
		return n;
	}
}
