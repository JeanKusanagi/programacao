/*
 * Ex10_3.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.io.*;
import java.util.Scanner;
public class Ex10_3 {

	static Scanner read = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		//UI
		System.out.printf("Analisador de pH\n---------------------------------------------------------------------------");

		//Chama o menu
		menu(); 	
	}

	//Imprime um menu e chama as funções
	public static void menu() throws IOException {						 
		//Inicializa a array
		double[] array=readArray_File();

		//Variável que contém a opção escolhida
		int opcao=0;

		System.out.printf("\nOpções disponíveis:");
		System.out.printf("\n1 - Ler valores de pH de um ficheiro");
		System.out.printf("\n2 - Escrever valores de pH no terminal");
		System.out.printf("\n3 - Calcular o pH médio das amostras");
		System.out.printf("\n4 - Calcular o número de amostras ácidas e básicas");
		System.out.printf("\n5 - Calcular o número de amostras de pH superior à média");
		System.out.printf("\n6 - Escrever valores de pH no terminal ordenados de modo crescente");
		System.out.printf("\n7 - Terminar o programa");

		//Apresenta e lê as opções enquanto a opção escolhida não for a 7
		do {
			System.out.printf("\n\nOpção -> ");
			opcao=read.nextInt();

			switch (opcao){
			case 1: 
				array=readArray_File();
				break;
			case 2: 
				printArray(array); 
				break;
			case 3: 
				System.out.printf("pH médio das amostras: %f", getAverage(array)); 
				break;
			case 4: 
				System.out.printf("Número de amostras ácidas: %d", countNumbersInRange(array, 7, 0));
				System.out.printf("\nNúmero de amostras básicas: %d", countNumbersInRange(array, 7, 1));
				break;
			case 5: 
				System.out.printf("Número de amostras superiores à média: %d", countNumbersInRange(array, getAverage(array), 0));
				break;
			case 6: 
				printArray(orderAscending(array));
				break;
			case 7: 
				System.exit(0); 
				break;
			default: 
				System.out.printf("Opção inválida."); 
				return;
			}
		} while (opcao!=7);
	}
	//---------------------------------------------------------------------------------------
	//Função que escreve valores de um ficheiro para uma array
	public static double[] readArray_File () throws IOException {	
		//Obter o ficheiro
		String path;
		File file;

		do{
			System.out.print("\nPor favor introduza o nome de um ficheiro de texto válido (com extensão): ");
			while(!read.hasNext());
			path = read.nextLine();
			file = new File(path);
		}while(!file.isFile() || !file.canRead());
		
		//Contar o número de amostras válidas gravadas no ficheiro
		Scanner in_count = new Scanner (file);
		int count=0;
		while(in_count.hasNext()) {
			double tmp=in_count.nextDouble();
			if (tmp>=0 && tmp<=14) count++;
		}
		
		in_count.close();
		
		//Copiar os valores válidos para uma array
		Scanner in = new Scanner (file);
		double array[] = new double [count];
		int i=0;

		while(in.hasNext()) {
			double tmp=in.nextDouble();
			if (tmp>=0 && tmp<=14) {
				array[i] = tmp;
				i++;
			}
		}
		
		in.close();
		return array;
	}

	//Função que imprime os valores da array	
	public static void printArray (double[] array) {	
		for (int j=0; j<array.length; j++) {
			double num=array[j];
			System.out.print(num);
			System.out.print(" ");
		} 
	}

	//Função que calcula a média
	public static double getAverage(double[] array) {
		double sum= 0;
		int count= 0;

		for (int i=0; i<array.length; i++) {
			if (array[i] != 0) {
				sum+=array[i];
				count++;
			}
		}

		return (sum/count);

	}

	//Função que conta quantos números de uma array são menores (k=0), maiores (k=1) ou iguais (k=2) a um dado número n
	public static int countNumbersInRange (double[] array, double n, int k)
	{
		int count=0;
		
		switch (k) {
			case 0: 
				for (int i=0; i<array.length; i++) {
					if (array[i]<n) count++;
					}
				break;
			case 1:
				for (int i=0; i<array.length; i++) {
					if (array[i]>n) count++;
					}
				break;
			case 2:
				for (int i=0; i<array.length; i++) {
					if (array[i]==n) count++;
					}
				break;
		}
			
		return count;
	}
	
	//Função que ordena a array por ordem crescente (ordenação sequencial)
	public static double[] orderAscending (double[] array) {
		double tmp;
		int i, j;

		for(i = 0 ; i < array.length - 1 ; i++){ 
			for(j = i + 1 ; j < array.length ; j++){ 
				if(array[i] > array[j]) 
				{
					tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
				}
			}
		}

		return array;
	}

}