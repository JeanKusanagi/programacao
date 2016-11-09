/*
 * Ex6_5.java
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
public class Ex6_5 {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);				

		//Número máximo de números a introduzir, ie a dimensão da array
		int DIM=getIntPos("Indique quantos numeros reais pretende ler: ");
		
		//Inicialização das variáveis
		double numeros[]=new double [DIM];
		int i=0;							//Índice de cada elemento da array
		double num=0;
		
		double soma=0;						//Variáveis para o cálculo da média
		double media=0; 					
		
		double variance[]=new double[DIM];  //Variáveis para o cálculo do desvio-padrão
		double sum_variance=0;
		double st_deviation=0;
		
		//Leitura dos números
		System.out.printf("Introduza uma sequencia de %d numeros reais: ", DIM);
		do 									//Lê numeros e coloca-os na matriz enquanto o número for positivo e o seu índice na matriz for <DIM	
		{		
			num=ler.nextDouble();
			numeros[i]=num;
			soma=soma+num;
			i++;
		} 
		while (i<(DIM)); 
		
		//Cálculo da média e do desvio-padrão (st_deviation=positive square root of (Sum of variances/DIM-1))
		media=soma/DIM;
		
		for (int j=0; j<DIM; j++) {
			variance[j]=Math.pow(numeros[j]-media, 2); 
			sum_variance=sum_variance+variance[j];
		}
		st_deviation=Math.sqrt((sum_variance/(DIM-1)));
				
		//Impressão do resultados
		System.out.printf("\nNa sequencia de %d numeros reais introduzida: \n - Media= %f\n - Desvio-padrao= %f\n - Valores acima da media:\n", DIM, media, st_deviation);
		
		//Apresentar valores acima da média
		for (int j=0; j<DIM; j++) {	
			if (numeros[j]>=media) {System.out.printf("    %f\n", numeros[j]);}
		}
	}

	public static int getIntPos (String message)
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
