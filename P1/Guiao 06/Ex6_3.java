/*
 * Ex6_3.java
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
public class Ex6_3 {


	static Scanner ler = new Scanner(System.in);
	public static void main(String[] args) {

		//Número máximo de números a introduzir, ie a dimensão da array (parâmetro a alterar ou a pedir ao utilizador) 
		int DIM=5; 
		System.out.printf("\nAnalise de uma sequencia de numeros inteiros\n");

		//Antes de apresentar as opções, tem de ler uma sequência de números inteiros
		int[]Seq=new int[DIM];
		Seq=EscreverSeq("Por favor introduza uma sequencia de numeros inteiros antes de selecionar uma opcao", DIM);

		//Chama o menu
		menu(DIM, Seq); 	

		/* Permite introduzir mais do que uma opção
		 * System.out.printf("\nPretende seleccionar mais alguma opcao? \n<Sim/Nao>\n");
		 * String resposta=ler.next();
		 * while (resposta.equals("Sim")) {
		 * 	menu(DIM, Seq); 
		 * 	System.out.printf("\n\nPretende seleccionar mais alguma opcao? \n<Sim/Nao>\n"); 
		 * 	resposta=ler.next();
		 *  }
		 */
	}

	//Imprime um menu e chama as funções
	public static void menu(int DIM, int[] Seq) {						 

		//Apresenta e lê as opções enquanto a opção escolhida não for a 10
		int opcao=0;
		
		//do while para garantir que a array sofre update quando voltamos a lê-la
		do {
			System.out.printf("\nOpcoes disponiveis\n1 - Ler a sequencia\n2 - Escrever a sequencia\n3 - Calcular o maximo da sequencia\n4 - Calcular o minimo da sequencia\n5 - Calcular a media da sequencia\n6 - Detetar se e uma sequência so constituída por numeros pares\n10 - Terminar o programa\nOpcao -> ");
			opcao=ler.nextInt();

			//Executa as opções
			switch (opcao){
			case 2: {LerSeq(DIM, Seq); break;}
			case 1: {Seq=EscreverSeq("Introduza uma sequencia de numeros inteiros positivos", DIM); break;}
			case 3: {System.out.printf("Valor maximo da sequencia: %d", getMax(DIM,Seq)); break;}
			case 4: {System.out.printf("Valor minimo da sequencia: %d", getMin(DIM,Seq)); break;}
			case 5: {System.out.printf("Media dos valores da sequencia: %f", getAverage(DIM,Seq)); break;}
			case 6: {if (Det_SeqPar(DIM,Seq)) {System.out.printf("A sequencia e composta apenas por numeros pares.");}
			else {System.out.printf("A sequencia nao e composta apenas por numeros pares.");}
			break;}
			case 10: {System.exit(0);}
			default: {System.out.printf("Opcao invalida."); return;}
			}

		} while (opcao!=10);
	}

	//Ler valores inteiros positivos, com validação
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

	//Permite escrever valores para a array
	public static int[] EscreverSeq (String message, int DIM) {				
		//Leitura dos números
		System.out.printf("%s (numero negativo termina, nao sendo possivel introduzir mais do que %d numeros): ", message, DIM);

		//Inicialização das variáveis
		int numeros[]=new int [DIM];
		int i=0;							//Índice de cada elemento da array
		int num=0;

		//Lê numeros e coloca-os na matriz enquanto o número for positivo e o seu índice na matriz for <DIM	
		do 									
		{		
			num=ler.nextInt();
			numeros[i]=num;
			i++;
		} 
		while ((num>=0) && (i<(DIM))); 

		return numeros;
	}

	//Apresenta os valores da array	
	public static void LerSeq (int DIM, int numeros[]) {	
		for (int j=0; j<DIM; j++) {
			int num=numeros[j];
			System.out.print(num);
			System.out.print(" ");
		} 
	}

	//Obtém o valor mínimo da sequência
	public static int getMin(int DIM, int [] array)
	{
		int min = array[0];
		for (int i = 1; i <DIM; i++){
			if ((array[i] < min) && (array[i] != 0)) {
				min = array[i];
			}
		}

		return min;
	}

	//Obtém o valor máximo da sequência
	public static int getMax(int DIM, int[] array)
	{
		int max = array[0];
		for (int i = 1; i <DIM; i++)
		{
			if ((array[i] > max) && (array[i] != 0)) {
				max = array[i];
			}
		}

		return max;
	}

	//Cálculo da média
	public static double getAverage(int DIM, int[] array)
	{
		double sum= 0;
		int count= 0;

		for (int i=0; i<DIM; i++) {
			if (array[i] != 0) {
				sum+=array[i];
				count++;
			}
		}

		return (sum/count);

	}

	//Verificar se a sequência é par
	public static boolean Det_SeqPar (int DIM, int[] array)
	{
		boolean Seq_Par = true;
		for (int i = 0; i < DIM; i++) {
			if ((array[i] != 0) && ((array[i] % 2) != 0)) {
				Seq_Par = false;
			}
		}
		return Seq_Par;
	}
}