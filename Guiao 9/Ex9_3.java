/*
 * Ex9_3.java
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
import java.io.*;
import java.util.Scanner;
public class Ex9_3 {

	static Scanner ler = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		//UI
		System.out.printf("Analise de uma sequencia de numeros inteiros\n---------------------------------------------------");

		//Chama o menu
		menu(); 	
	}

	//Imprime um menu e chama as funções
	public static void menu() throws IOException {						 

		/* Inicializa a array Seq
		 * DIM : Número predefinido de números a introduzir, que pode ser alterado se a sequência for lida de um ficheiro */
		int DIM=5; 
		int[]Seq=new int[DIM];
		
		//Variável que contém a opção escolhida
		int opcao=0;

		//Apresenta e lê as opções enquanto a opção escolhida não for a 10
		do {
			System.out.printf("\n\nOpcoes disponiveis");
			System.out.printf("\n1 - Ler a sequencia");
			System.out.printf("\n2 - Escrever a sequencia");
			System.out.printf("\n3 - Calcular o maximo da sequencia");
			System.out.printf("\n4 - Calcular o minimo da sequencia");
			System.out.printf("\n5 - Calcular a media da sequencia");
			System.out.printf("\n6 - Detetar se e uma sequencia so constituida por numeros pares");
			System.out.printf("\n7 - Ler uma sequencia de numeros de um ficheiro de texto");
			System.out.printf("\n8 - Adicionar numeros a sequencia existente");
			System.out.printf("\n9 - Escrever a sequencia num ficheiro de texto");
			System.out.printf("\n10 - Terminar o programa");

			System.out.printf("\nOpcao -> ");
			opcao=ler.nextInt();

			switch (opcao){
			case 1: 
				Seq=ReadSeq("Introduza uma sequencia de numeros inteiros positivos", DIM); 
				break;
			case 2: 
				PrintArray(Seq); 
				break;
			case 3: 
				System.out.printf("Valor maximo da sequencia: %d", getMax(Seq)); 
				break;
			case 4: 
				System.out.printf("Valor minimo da sequencia: %d", getMin(Seq)); 
				break;
			case 5: 
				System.out.printf("Media dos valores da sequencia: %f", getAverage(Seq)); 
				break;
			case 6: 
				if (Det_SeqPar(Seq)) System.out.printf("A sequencia e composta apenas por numeros pares.");
				else System.out.printf("A sequencia nao e composta apenas por numeros pares.");
				break;
			//Ex 9.3
			case 7: 
				Seq=ReadSeq_File();
				break;
			case 8:
				Seq=add(Seq);
				break;
			case 9: 
				PrintSeq_File(Seq,"resultados_9_3.txt"); 
				break;
			case 10: 
				System.exit(0); 
				break;
			default: 
				System.out.printf("Opcao invalida."); 
				return;
			}
		} while (opcao!=10);
	}
	//---------------------------------------------------------------------------------------
	//Função que lê valores inteiros positivos, com validação
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

	//Função que escreve valores para a array
	public static int[] ReadSeq (String message, int DIM) {				
		//Leitura dos números
		System.out.printf("%s (numero negativo termina, nao sendo possivel introduzir mais do que %d numeros): ", message, DIM);

		//Inicialização das variáveis
		int numeros[]=new int [DIM];
		int i=0;							//Indice de cada elemento da array
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

	//Função que imprime os valores da array	
	public static void PrintArray (int[] array) {	
		for (int j=0; j<array.length; j++) {
			int num=array[j];
			System.out.print(num);
			System.out.print(" ");
		} 
	}

	//Função que determina o valor mínimo da sequência
	public static int getMin(int[] array)
	{
		int min = array[0];
		for (int i = 1; i <array.length; i++){
			if ((array[i] < min) && (array[i] != 0)) {
				min = array[i];
			}
		}

		return min;
	}

	//Função que determina o valor máximo da sequência
	public static int getMax(int[] array)
	{
		int max = array[0];
		for (int i = 1; i <array.length; i++)
		{
			if ((array[i] > max) && (array[i] != 0)) {
				max = array[i];
			}
		}

		return max;
	}

	//Função que calcula a média
	public static double getAverage(int[] array)
	{
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

	//Função que verifica se a sequência é par
	public static boolean Det_SeqPar (int[] array)
	{
		boolean Seq_Par = true;
		for (int i = 0; i < array.length; i++) {
			if ((array[i] != 0) && ((array[i] % 2) != 0)) {
				Seq_Par = false;
			}
		}
		return Seq_Par;
	}

	//Ex 9.3 --------------------------------------------------------------------------------
	//Função que escreve valores de um ficheiro para a array
	public static int[] ReadSeq_File () throws IOException {	
		String path;
		File file;
		
		do{
			System.out.print("\nPor favor introduza o nome de um ficheiro de texto valido (com extensao): ");
			while(!ler.hasNext());
			path = ler.nextLine();
			file = new File(path);
		}while(!file.isFile() || !file.canRead());
		
		Scanner in = new Scanner (file);
		int array[] = new int[(int) file.length()];
		int i=0;
		
		while(in.hasNext()){
			String s = in.next();
			//out.print("\n" + s + " ");
			if (!in.hasNext()) break;
			array[i] = Integer.parseInt(s);
			i++;
		}
		
		in.close();
		return array;
	}

	public static int[] ReadSeq_File1() throws IOException {
		//Obtém o ficheiro a ler
		String filepath;
		File file;

		System.out.printf("Por favor introduza o nome de um ficheiro de texto valido (com extensao): ");
		do {
			filepath= ler.nextLine();
			file = new File(filepath);
		} while(!file.isFile());			//Validação
		Scanner in=new Scanner(file);		//Scanner para ler do ficheiro file
		
		//-----------------------------------------------------------------------------------------
		int j=0;							//Conta quantos números são inseridos
		int num=1; 							//Variável temporária com número a ser lido
		
		File tmp = new File ("tmp1.txt");
		PrintWriter out= new PrintWriter (tmp);

		while (num!=0) {
			String content=in.next();
			num=Integer.parseInt(content);
			if (num!=0) {		//Evita que seja apresentado o 0 final
				out.print(num);
				out.print(" ");
				j++;}
			in.next();
		}
		in.close();
		out.close();

		//Cria uma nova array a partir do ficheiro tmp1.txt
		Scanner in1 = new Scanner (tmp);
		int[] new_array=new int[j];  
		int i=0;
		while(in.hasNext()){
			String content=in1.next();
			num=Integer.parseInt(content);
			new_array[i]=num;
			i++;
		}
		in1.close();

		return new_array;
	}

	//Função que imprime os valores da array num ficheiro :: OK 
	public static void PrintSeq_File (int[] array, String filename) throws IOException {
		//Cria o ficheiro
		File out_file = new File (filename); 			
		PrintWriter out=new PrintWriter (out_file);

		//Imprime os valores da array numeros no ficheiro out_file
		out.printf("Sequencia introduzida: ");
		for (int j=0; j<array.length; j++) {
			int num=array[j];
			out.print(num);
			out.print(" ");
		}
		out.close();
	}

	//Função que adiciona números à sequência existente :: OK
	public static int[] add(int[] array) throws IOException{
		
		//Cria um ficheiro temporário tmp.txt e cria um Scanner para escrever neste
		File tmp = new File ("tmp.txt");
		PrintWriter out= new PrintWriter (tmp);

		//Adiciona os valores actuais da array a um novo ficheiro tmp.txt
		for(int i=0; i<array.length; i++){
			if(array[i]==0) break;
			out.println(array[i]);
		}

		//Adiciona números ao ficheiro dados.txt
		int j=array.length;				//Conta quantos números são inseridos
		int num=1; 				//Variável temporária com número a ser lido
		System.out.printf("\nIntroduza os numeros que deseja adicionar (0 termina): ");
		
		while (num!=0) {
			num=ler.nextInt();
			if (num!=0) {		//Evita que seja apresentado o 0 final
				out.print(num);
				out.print(" ");
				j++;}
		}
		out.close();
		
		//Actualiza a sequência, criando uma nova array (a antiga não pode ser utilizada)
		Scanner in = new Scanner (tmp);
		int[] new_array=new int[j];  
		int i=0;
		while(in.hasNext()){
			new_array[i]=in.nextInt();
			i++;
		}
		in.close();
		
		return new_array;
	}
}