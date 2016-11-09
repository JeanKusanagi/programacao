/*
 * Ex6_4.java
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
public class Ex6_4 {

	public static void main(String[] args) {
		//Número máximo de notas a introduzir, ie a dimensão da array
		int DIM=getIntPos("Introduza o numero de notas que pretende processar: ");

		//Inicialização das variáveis
		int notas[]=new int [DIM];
		int count_notas[]=new int [21];

		//int count_rep=0;					//Variavél que contém o número de vezes que num_pretendido for introduzido
		int i=0;							//Índice de cada elemento da array
		int num=0;							

		//Leitura das notas :: OK
		System.out.printf("Introduza as %d notas: ", DIM);
		do 									//Lê numeros e coloca-os na matriz enquanto o seu índice na matriz for <DIM	
		{		
			num=getIntPos("");
			notas[i]=num;
			i++;
		} 
		while (i<(DIM)); 
		
		//Contar o número de vezes que a nota j aparece na array :: OK 
		for (int j=0; j<=20; j++) {																	//Para todas as classificações j de 0 a 20	
			for (int n=0; n<DIM; n++) {if (notas[n]==j) {count_notas[j]=count_notas[j]+1;}};		//Para todos os valores n armazenados na matriz notas, se a nota n for igual a j
		}

		//Impressão do resultado
		System.out.printf("\nHistograma de notas\n-------------------------------------------------------");
		for (int j=20; j>=0; j--) {
			System.out.printf("\n%2d | ", j);
			for (int v=0; v<count_notas[j]; v++) {System.out.print("*");}
	}
	}
	
public static int getIntPos (String message)	//Lê um número inteiro positivo	
{
	Scanner ler = new Scanner(System.in);
	System.out.printf("%s",message);
	int n=ler.nextInt();
	while (n<0) 
	{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
	n=ler.nextInt();}
	return n;
}	
}
