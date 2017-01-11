/*
 * Ex12_1.java
 * 
 * Copyright 2017 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.util.Scanner;
import java.io.*;

public class Ex12_1 {

	static Scanner read = new Scanner (System.in);
	
	public static void main(String[] args) throws IOException {
		
		//Array com valores
		Value[] values = new Value [31];
		
		//UI: Apresentar opções
		System.out.printf("Estação meteorológica: \n");
		System.out.printf("\n  1 - Ler ficheiro de dados");
		System.out.printf("\n  2 - Acrescentar medida");
		System.out.printf("\n  3 - Listar valores de temperatura e humidade");
		System.out.printf("\n  4 - Listar medidas ordenadas por valor de temperatura");
		System.out.printf("\n  5 - Listar medidas ordenadas por valor de humidade");
		System.out.printf("\n  6 - Calcular valores médios de temperatura e humidade");
		System.out.printf("\n  7 - Calcular valores máximos e mínimos de temperatura e humidade");
		System.out.printf("\n  8 - Calcular histograma das temperaturas e humidade");
		System.out.printf("\n  9 - Calcular histograma vertical das temperaturas");
		System.out.printf("\n 10 - Terminar o programa");
		System.out.printf("\n Antes de poder escolher as opções 2-9, é necessário ler um ficheiro ");
		
		//Ler opção (enquanto não for para terminar)
		int option=0;
		do {
			System.out.printf("\n\nOpção -> ");
			option=read.nextInt();
			switch (option) {
				case 1:
					values=readFile();
					break;
				case 2:
					values=addValue(values);
					break;
				case 3:
					printValues(values);
					break;
				case 4:
					printValues(orderValues(values,0));
					orderValues(values,2);
					break;
				case 5:
					printValues(orderValues(values,1));
					orderValues(values,2);
					break;
				case 6:
					getMean(values);
					break;
				case 7:
					getMax_Min(values);
					break;
				case 8:
					getHistogram(values);
					break;
				case 9:
					getVerticalHistogram(values);
				case 10:
					break;
				default:
					System.out.printf("\nOpção inválida.");
					break;
			}
			//Executar opções
		} while (option!=10);
		
		//Termina o programa
		System.out.printf("Programa terminado.");
		System.exit(0);
		
	}

	//Ler dados ficheiro
	public static Value[] readFile () throws IOException {
		//Pede ao utilizador o nome do ficheiro 
		System.out.printf("Por favor introduza o nome do ficheiro de texto (com extensão): ");
		String filename=read.nextLine();
		File file= new File (filename);

		//Valida o nome do ficheiro, ie pede um nome de ficheiro enquanto este não corresponde a um ficheiro válido
		while (!file.isFile() || file.isDirectory() || !file.canRead()) {
			filename=read.nextLine();
			file= new File (filename);
		}
		
		//Obtêm o número de valores no ficheiro
		int count=0;
		
		Scanner in_count = new Scanner (file);
		while(in_count.hasNextInt()) {
			int tmp=in_count.nextInt();
			int hum=in_count.nextInt();
			if (tmp>=-10 && tmp<=40 && hum>=0 && hum<=100) count++;		//Conta, com validação
			if (!in_count.hasNextLine()) break;
			in_count.nextLine();
		}
		in_count.close();
		
		//Impede uma array maior do que 31
		if (count>31) count=31;
		
		//Inicializa a array
		Value[] array= new Value[count];
		for (int i=0; i<array.length; i++) {
			array[i] = new Value(); 	
		}
		
		//Copia os valores do ficheiro para a array de tamanho count (0<=count<=31)
		Scanner in = new Scanner (file); 
		int i=0;
		while (in.hasNextLine()) {
			//Lê valores, com validação
			int tmp=in.nextInt();
			int hum=in.nextInt();
			if (tmp>=-10 && tmp<=40 && hum>=0 && hum<=100) {
				array[i].temp=tmp;		//Valor temperatura
				array[i].hum=hum;		//Valor humidade
				array[i].day = i+1;		//Valor dia (não é lido do ficheiro)
				i++;
			}
			//Evita erro (na última linha, não é possível ir para a linha seguinte)
			if (!in.hasNextLine()) break;
			in.nextLine();
			
		}
		
		in.close();
		
		System.out.printf("\nO ficheiro foi lido com sucesso.");
		return array;
	}
	
	//Acrescentar valor à array
	public static Value[] addValue (Value[] array) {
		System.out.printf("Adicionar medida:");
		
		if (array.length==31) {
			System.out.printf("\nNão é possível introduzir mais medidas.");
			return array;
		}
		
		else if (array.length!=31) {
			int temp, hum;
			
			do {
				System.out.printf("\n  Introduza o valor da temperatura: ");
				temp=read.nextInt();
			} while (temp<-10 || temp>40);			//Validação
			
			do {
				System.out.printf("\n  Introduza o valor da humidade: ");
				hum=read.nextInt();
			} while (hum<0 || hum>100);
			
			Value[] new_array= new Value[array.length+1];
			for (int i=0; i<array.length; i++) {
				new_array[i] = new Value();			//Inicializa a nova array
				new_array[i] = array[i];			//Copia os valores da array antiga
			}
			
			//O valor dado pelo utilizador ocupa a última posição da array
			new_array[array.length] = new Value();
			new_array[array.length].temp = temp;
			new_array[array.length].hum = hum;
			new_array[array.length].day = array.length+1;
			
			return new_array;
		}
		return array;
	}
	
	//Imprimir valores
	public static void printValues (Value[] array) {
		
		System.out.printf("\nValores de temperatura e humidade:");
		for (int i=0; i<array.length; i++) {
			System.out.printf("\n Dia %2d:  Temperatura: %d ºC |  Humidade: %d", array[i].day, array[i].temp, array[i].hum);
		}
	}
	
	//Ordenar valores (Ordenação por flutuação)
	public static Value[] orderValues (Value[] array, int op) {	
		Value tmp1, tmp2, tmp3;
		boolean switches1, switches2, switches3;
		
		//Ordena por valor de temperatura (op=0), por valor de humidade (op=1) ou por dia (por ordem de leitura, op=2)
		switch (op) {
			case 0:
				do {
					switches1 = false;
					for (int i=0; i<array.length-1; i++) {
						if (array[i].temp > array[i+1].temp) {
							tmp1=array[i];
							array[i]=array[i+1];
							array[i+1]=tmp1;
							switches1=true;
						}
					}
				} while (switches1);
				break;
			case 1:
				do {
					switches2 = false;
					for (int i=0; i<array.length-1; i++) {
						if (array[i].hum > array[i+1].hum) {
							tmp2=array[i];
							array[i]=array[i+1];
							array[i+1]=tmp2;
							switches2=true;
						}
					}
				} while (switches2);
				break;
			case 2:
				do {
					switches3 = false;
					for (int i=0; i<array.length-1; i++) {
						if (array[i].day > array[i+1].day) {
							tmp3=array[i];
							array[i]=array[i+1];
							array[i+1]=tmp3;
							switches3=true;
						}
					}
				} while (switches3);
				break;
		}
		
		return array;
		
	}
	
	//Calcular média
	public static void getMean (Value[] array) {
		int sum_temp=0;
		int sum_hum=0;
		
		for (int i=0; i<array.length; i++) {
			sum_temp=sum_temp+array[i].temp;
			sum_hum=sum_hum+array[i].hum;			
		}
		
		double mean_temp=sum_temp/array.length;
		double mean_hum=sum_hum/array.length;
		
		System.out.printf("Valores médios: Temperatura %s ºC | Humidade %s ", mean_temp, mean_hum);
	}

	//Calcular máximo e mínimo
	public static void getMax_Min (Value[] array) {
		//Ordenar array por temperatura
		orderValues(array,0);
		
		//A menor temperatura estará no primeiro elemento da array agora ordenada
		int temp_min=array[0].temp;
		int temp_min_day=array[0].day;

		//A maior temperatura estará no último elemento da array agora ordenada
		int temp_max=array[array.length-1].temp;
		int temp_max_day=array[array.length-1].day;
		
		//-------------------------------------------------------------
		//Ordenar array por humidade
		orderValues(array,1);
		int hum_min=array[0].hum;
		int hum_min_day=array[0].day;
		
		int hum_max=array[array.length-1].hum;
		int hum_max_day=array[array.length-1].day;
		
		//-------------------------------------------------------------
		//Impressão resultados
		System.out.printf("Valores mínimos: Temperatura %d ºC  (dia %d) | Humidade %s (dia %d)", temp_min, temp_min_day, hum_min, hum_min_day);
		System.out.printf("\nValores máximos: Temperatura %d ºC  (dia %d) | Humidade %s (dia %d)", temp_max, temp_max_day, hum_max, hum_max_day);
		
	}
	
	//Calcular histograma
	public static void getHistogram (Value[] array) {
		//UI
		System.out.print("\n\nHistograma da temperatura"); 
		System.out.print("\n---------------------------"); 
		
		//Imprime histograma
		for (int i=-10; i<=40; i++) {
			System.out.printf("\n%3d | ",i); 
			
			int count=countsValue(array,i,1);
			for (int j=0; j<count; j++) {
				System.out.printf("*"); 
			}
		}
		
		System.out.print("\n\nHistograma da humidade"); 
		System.out.print("\n---------------------------"); 
		for (int i=0; i<=100; i++) {
			System.out.printf("\n%3d | ",i); 
			int count=countsValue(array,i,2);
			for (int j=0; j<count; j++) {
				System.out.printf("*"); 
			}
		}
	}
	
	public static void getVerticalHistogram (Value[] array){
		int[]values=new int[50];
		int max=0;
		
		//Inicializar array dos counts
		for(int i=0;i<values.length;i++){
			values[i]=countsValue(array, i-10,1);
		}
		
		//Para determinar o count máximo (o número máximo de * a desenhar)
		for(int i=0;i<values.length;i++){
			if(values[i]>max) max=values[i];
		}
		
		System.out.println("Histograma das temperaturas");
		
		for(int i = max ; i > 0 ; i--){
			for(int j = 0 ; j < values.length ; j++){
				System.out.print("  ");
				if(values[j]>=i) System.out.print("*");
				else System.out.print(" ");
			}
			System.out.print("\n");
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("-10 -9 -8 -7 -6 -5 -4 -3  -2 -1 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40");
		
}
	
	//Conta número de vezes que um valor Value aparece nas temperaturas (op=1) ou nas humidades (op=2)
	public static int countsValue (Value[] array, int value, int op) {
		int count=0;
		 
		switch (op) {
			case 1:
				for (int i=0; i<array.length; i++) {
					if (array[i].temp==value) count++;
				}
				break;
			case 2: 
				for (int i=0; i<array.length; i++) {
					if (array[i].hum==value) count++;
				}
				break;
		}
		
		return count;
	}
	
}

class Value {
	int day;
	int temp;
	int hum;
}
