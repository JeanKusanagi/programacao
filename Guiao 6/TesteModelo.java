/*
 * TesteModelo.java
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
public class TesteModelo {
	
	static Scanner ler = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Número máximo de notas a introduzir, ie a dimensão da array
		int DIM=10;
		
		//Inicialização das variáveis
		int notas[]=new int [DIM];
		notas=LerNotas(DIM);						

		//Alinea 2 || Listar notas
		System.out.printf("\nLista de notas\n-------------------------------------------------------");
		for (int i=0; i<DIM; i++) {
			System.out.printf("\nNota do(a) aluno(a) %d=%d", i+1, notas[i]);
		}
		
		int[] count_notas=new int[12];
		count_notas=CalcularHistograma(DIM,notas);
		ImprimirHistograma(count_notas);
		
		//Alinea 6 || Imprimir medias
		double media_total=CalcularMediaTotal(notas);
		double media_parcial=CalcularMediaParcial(notas);
		System.out.printf("\n\nLista de notas\n-------------------------------------------------------\nMedia com faltas= %f\nMedia sem faltas= %f", media_total, media_parcial);
	}
	
	//Lê um número inteiro entre lim_inf e lim_sup
	public static int getIntRange (String message, int lim_inf, int lim_sup) 
	{
		int n=ler.nextInt();
		while ((n<lim_inf) || (n>lim_sup)) {
			System.out.printf("\nPor favor introduza %s entre %d e %d: ", message, lim_inf, lim_sup);
			n=ler.nextInt();
		}
		return n;
	}
	
	//Alinea 1 || Leitura das notas :: Implementar como função --- permite rever return de arrays numa função
	public static int[] LerNotas (int DIM) {								 
		
		System.out.printf("Introduza as %d notas: ", DIM);
		int notas[]=new int [DIM];
		int num=0;	
		int i=0;							//Índice de cada elemento da array
		do 									//Lê numeros e coloca-os na matriz enquanto o seu índice na matriz for <DIM	
		{		
			num=getIntRange("uma nota", 0, 11);
			notas[i]=num;
			i++;
		} 
		while (i<(DIM));
		
		return notas;
	}
	
	//Alinea 3 || Contar o número de vezes que a nota j aparece na array
	public static int[] CalcularHistograma (int DIM, int[] notas) {   		 
		
		int count_notas[]=new int [12];
		for (int j=0; j<=11; j++) {																	//Para todas as classificações j de 0 a 20	
			for (int n=0; n<DIM; n++) {if (notas[n]==j) {count_notas[j]=count_notas[j]+1;}};		//Para todos os valores n armazenados na matriz notas, se a nota n for igual a j
		}
		return count_notas;
	}
	
	//Alinea 4 || Impressão do Histograma
	public static void ImprimirHistograma (int[] count_notas) {				 
		System.out.printf("\n\nHistograma de notas\n-------------------------------------------------------");
		for (int j=0; j<=10; j++) {
			System.out.printf("\n%2d | ", j);
			for (int v=0; v<count_notas[j]; v++) {System.out.print("*");}
				}
		System.out.printf("\nFaltas | ");
		for (int v=0; v<count_notas[11]; v++) {System.out.print("*");}
	}
	
	//Alinea 5.1 || Calcular média total (com faltas)
	public static double CalcularMediaTotal (int [] array){					 
		double somatorio=0;
		
		for (int j = 0; j <array.length ; j++) somatorio+=array[j];

		return somatorio/array.length;
	}
	
	//Alinea 5.2 || Calcular média (sem faltas)
	public static double CalcularMediaParcial (int [] array) {				 
		double somatorio=0, count=0;
		
		for (int j = 0; j <array.length ; j++) {
			if (array[j]!=11) {somatorio+=array[j]; count++;}
		}
		
		return somatorio/count;
		
	}
	
}

