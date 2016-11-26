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

import java.io.*;
import java.util.Scanner;
public class Ex9_2_b {

	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) throws IOException  {

		//UI
		System.out.printf("Contador de sequencias numericas\n--------\n");

		//Lê o ficheiro dados.txt e obtém o seu tamanho DIM
		File in_file=new File("dados.txt");
		Scanner read_file=new Scanner(in_file);
		int DIM=getFileSize(in_file);

		//Atribui o conteúdo do ficheiro à sequência

		//Inicialização das variáveis
		int numeros[]=new int [DIM];
		int count_rep=0;					//Variavél que contém o número de vezes que num_pretendido for introduzido
		int i=0;							//Indice de cada elemento da array
		int num=0;

		//Lê numeros e coloca-os na matriz enquanto o número for positivo e o seu índice na matriz for <DIM	
		while ((read_file.hasNextInt()) && (num>=0) && (i<(DIM))) {		
			num=read_file.nextInt();
			numeros[i]=num;
			i++;
		} 

		//Contar o número de vezes que um determinado número, num_pretendido, aparece na sequência
		System.out.printf("\nDos numeros introduzidos, indique de qual pretende saber a sua frequência absoluta (ie numero de vezes que apareceu): ");
		int num_pretendido=ler.nextInt();
		for (int j=0; j<DIM; j++) {	
			if (numeros[j]==num_pretendido) count_rep++;
		}

		//Impressão dos resultados no ficheiro resultados.txt
		writeValues("resultados1.txt", count_rep, num_pretendido);

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
	public static void writeValues (String filename, int count, int num) throws IOException {
		//filename: Nome do ficheiro. É fornecido à função. 
		// message: Texto inicial do ficheiro a ser gravado. É fornecido à função.  

		//Cria o ficheiro
		File out_file = new File (filename); 			
		PrintWriter out=new PrintWriter (out_file);

		//Imprime os valores da array values no ficheiro out_file
		if (count==1) {out.printf("O numero %d foi introduzido 1 vez. ", num);}
		else {out.printf("O numero %d foi introduzido %d vezes. ", num, count);}

		//Fecha o ficheiro
		out.close();
	}
}
