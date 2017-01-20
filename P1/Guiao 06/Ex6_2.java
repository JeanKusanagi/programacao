/*
 * Ex9_2_b.java
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
public class Ex6_2 {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		int DIM=100;						//Número máximo de números a introduzir, ie a dimensão da array

		//Leitura dos números
		System.out.printf("Introduza uma sequencia de numeros inteiros positivos: \n<Numero negativo termina> \n<Nao e possivel introduzir mais do que %d numeros>\n\n", DIM);

		//Inicialização das variáveis
		int numeros[]=new int [DIM];
		int count_rep=0;					//Variavél que contém o número de vezes que num_pretendido for introduzido
		int i=0;							//Índice de cada elemento da array
		int num=0;

		do 									//Lê numeros e coloca-os na matriz enquanto o número for positivo e o seu índice na matriz for <DIM	
		{		
			num=ler.nextInt();
			numeros[i]=num;
			i++;
			//num=ler.nextInt();
		} 
		while ((num>=0) && (i<(DIM))); 

		//Contar o número de vezes que um determinado número, num_pretendido, aparece na sequência
		System.out.printf("\nDos numeros introduzidos, indique de qual pretende saber a sua frequência absoluta (ie numero de vezes que apareceu): ");
		int num_pretendido=ler.nextInt();
		for (int j=0; j<DIM; j++) {	
			if (numeros[j]==num_pretendido) count_rep++;
		}
		
		//Impressão do resultado
		if (count_rep==1) {System.out.printf("O numero %d foi introduzido 1 vez. ", num_pretendido);}
		else {System.out.printf("O numero %d foi introduzido %d vezes. ", num_pretendido, count_rep);}
	}

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
