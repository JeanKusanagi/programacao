/*
 * Ex6_1.java
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
public class Ex6_1 {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		//Obtenção do valor de N
		System.out.printf("Especifique quantos numeros inteiros pretende introduzir: ");
		int n=getInt("","Tem de introduzir um numero inteiro POSITIVO e MAIOR QUE 1: ",1);

		//Leitura dos N números da sequência
		System.out.printf("Introduza os %d numeros da sua sequencia: ", n);

		int numeros[]= new int [n]; 	//Inicialização de uma array de n elementos
		for (int i=0; i<n; i++) {
			numeros[i]=ler.nextInt();
		}

		//Impressão dos N números pela ordem inversa
		System.out.printf("\nSequencia introduza pela ordem inversa: ");
		for (int j=(n-1); j>=0; j--) {
			System.out.print(numeros[j]);
			System.out.print(" ");
		}

	}

	public static int getInt (String message_in, String message_error, int lim)			//Obtenção do valor de N com validação
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message_in);
		int n=ler.nextInt();
		while (n<=lim)
		{System.out.printf("%s", message_error);
		n=ler.nextInt();}
		return n;
	}
}
