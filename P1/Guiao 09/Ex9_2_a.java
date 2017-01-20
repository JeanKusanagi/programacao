/*
 * Ex9_2_a.java
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

/* Exercício 6.1
 * Escreva um programa que leia uma sequência de N números inteiros, sendo o valor N pedido ao
 * utilizador antes do inicio da introdução dos números. O programa deve depois imprimir esses
 * números pela ordem inversa com que foram inseridos.
 */

import java.util.Scanner;
import java.io.*;

public class Ex9_2_a {

	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		//UI
		System.out.printf("Inversor de sequencias numericas\n--------\n");

		//Pede ao utilizador o nome do ficheiro  --> o enunciado refere para ser lido um ficheiro dados.txt mas o programa apresentado é mais versátil
		File in_file= getFile();

		//Valida o nome do ficheiro, ie pede um nome de ficheiro enquanto este não corresponde a um ficheiro válido
		while (!isFileValid(in_file)) {
			System.out.printf("\nPor favor introduza um nome de um ficheiro de texto valido (com extensao): ");
			in_file=getFile();
		}

		//Atribui o conteúdo do ficheiro à sequência
		int n=getFileSize(in_file);
		
		Scanner read_file=new Scanner(in_file);						
		int numeros[]= new int [n];							//Cria uma array de dimensão n
		int i=0; 											//Variável para contar quantos números inteiros estão no ficheiro

		while(read_file.hasNextInt()){
			numeros[i]=read_file.nextInt();
			i++;
		}

		//Impressão dos N números pela ordem inversa no ficheiro resultados.txt
		writeValues("resultados_9_2a.txt", numeros, "\nSequencia introduzida pela ordem inversa: ", i);

	}


	//Função que pede ao utilizador o nome do ficheiro
	public static File getFile() {
		System.out.printf("Por favor introduza o nome do ficheiro de texto (com extensao): ");
		String file_path=ler.nextLine();
		File tmp= new File (file_path);
		return tmp;
	}

	//Função booleana que devolve a validade do ficheiro 
	public static boolean isFileValid (File file) {
		if (!file.isFile()) {
			System.out.printf("\nERROR : %s is not a file", file);
			return false;
		}
		if (file.isDirectory()) {
			System.out.printf("\nError : %s is a directory", file);
			return false; 
		}
		if (!file.canRead()) {
			System.out.printf("\nERROR: %s is not readable", file);
			return false;
		}
		return true;
	}
	
	//Função que devolve o tamanho do ficheiro (neste programa correspondente ao número máximo de números a serem lidos)
	public static int getFileSize (File file) throws IOException {
		int n=(int) file.length();
		return n;
	}
	
	//Função que imprime os valores da array values pela ordem inversa num ficheiro
	public static void writeValues (String filename, int[] values, String message, int i) throws IOException {
		//filename: Nome do ficheiro. É fornecido à função. 
		//message: Texto inicial do ficheiro a ser gravado. É fornecido à função.  
		
		//Cria o ficheiro
		File out_file = new File (filename); 			
		PrintWriter out=new PrintWriter (out_file);
		
		//Imprime os valores da array values no ficheiro out_file
		out.printf("%s",message);
		for (int j=i-1; j>=0; j--) {
			out.print(values[j]);
			out.print(" ");
		}

		//Fecha o ficheiro
		out.close();
	}
}
