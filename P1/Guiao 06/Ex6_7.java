/*
 * Ex6_7.java
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
public class Ex6_7 {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		int DIM=8;																		//Número máximo de números a introduzir, ie a dimensão da array

		//UI: Mensagem de início 
		System.out.printf("Introduza uma sequencia de numeros inteiros positivos (numero negativo termina e nao e possivel introduzir mais do que %d numeros): \n", DIM);

		//Inicialização das variáveis
		int numeros[]=new int [DIM];
		int count=0;																	//Variável que contém o número de vezes que num_pretendido for introduzido
		int i=0;																		//Índice de cada elemento da array
		int num=0;

		//Lê numeros
		do 																				//Coloca os números na matriz enquanto este for positivo e o seu índice na matriz for <DIM	
		{		
			num=ler.nextInt();
			numeros[i]=num;
			i++;
		} 
		while ((num>=0) && (i<(DIM))); 

		//Contar o número de vezes que um determinado número aparece na sequência.
		for(int k=0; k<DIM; k++) {														//Ordena os valores da array.
			for(int j=0; j<(DIM-1); j++) {
				if(numeros[j]>numeros[j+1]) {
					int temp=numeros[j];
					numeros[j]=numeros[j+1];
					numeros[j+1]=temp;
				}
			}
		}

		//Partindo dos valore ordenados da array, calcula e apresenta o número de ocorrências.
		for(int k=0; k<DIM; k++) {							 
			for(int j=0; j<DIM; j++) {
				if(numeros[k]==numeros[j]) {count++;}
			}

			System.out.printf("%d ocorre %d vez(es);\n", numeros[k], count); 			//UI: Apresenta o resultado.
			if(count!=0)																//Evita duplicar uma mensagem com o número de ocorrências. 
			{
				k=k+count-1;															//Ao passar para o próximo elemento da array com um número diferente  
			}																			//(que estará na posição count-1 a seguir - a um dado número seguem-se, na array ordenada, count-1 numeros iguais).

			count=0; 																	//Reinicializa a variável para o próximo número.
		}
	}

	public static int getIntPos (String message)										//Lê um número inteiro positivo, com validação.
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("\n%s ",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");			//UI: Mensagem de erro para a validação
		n=ler.nextInt();}
		return n;
	}	
}
