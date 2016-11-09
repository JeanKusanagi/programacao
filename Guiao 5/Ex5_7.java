
/*
 * Ex5_7.java
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
public class Ex5_7 {

	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);

		//Variáveis
		int num1=0, num2=0, num_maior=0, num_menor=0, r;         

		//Introdução dos números
		System.out.printf("Calculo do MDC de 2 numeros\nIntroduza 2 numeros inteiros: \n");
		num1=ler.nextInt();
		num2=ler.nextInt();

		//Cálculo do MDC

		//Se num1=num2, o MDC é o próprio número
		if (num1==num2) {System.out.printf("\nMDC entre %d e %d = %d\n", num1, num2, num1); }

		//Senão, aplicamos o algorito de Euclides
		else
		{
			//Determinação do maior e menor número
			if (num1>num2) {num_maior=num1; num_menor=num2; }
			if (num1<num2) {num_maior=num2; num_menor=num1; }
	
			while (num_menor !=0)
			{
			//int q=num_maior/num_menor;		//Quociente da divisão entre o número maior e o menor
			r=num_maior%num_menor;		//Resto da divisão entre o número maior e o menor
			num_maior=num_menor;		//O divisor passa a ser dividendo, ie agora divide-se o menor número...
			num_menor=r;				//...pelo resto da divisão anterior... e recomeça o ciclo até o num_menor=0
			}
			
			/* Algoritmo de Euclides por subtracção 
			mdc=num_maior; //Inicialização variável
			
			//Subtrair sucessivamente o menor número ao maior até que os dois números se tornem iguais
			while (mdc>num_menor)
			{
				mdc=mdc-num_menor;

			}
			*/
			//Apresentação do resultado
			System.out.printf("\nMDC entre %d e %d = %d\n", num1, num2, num_maior);
		}
	}
}