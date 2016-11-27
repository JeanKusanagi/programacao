
/*
 * Ex9_4.java
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
public class Ex9_4 {

	static Scanner ler = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		
		//Inicialização das variáveis
		File file=getFile();						//Ficheiro a ser lido
		Scanner read_file= new Scanner (file);
		
		int count_notas[]=new int [21];				//Array com a frequência absoluta de cada nota de 0-20			
 	
		//Contar o número de vezes que a nota i aparece no ficheiro 
		for (int i=0; i<=20; i++) {														//Para todas as classificações i de 0 a 20	
			read_file= new Scanner (file);
			while (read_file.hasNextInt()) {											//Enquanto existirem valores inteiros no ficheiro para ler
				if (read_file.nextInt()==i) {
					count_notas[i]++;};													//Para todos os valores inteiros do ficheiro, se forem iguais à nota i, count_notas[i]++
				}	
		}
		read_file.close();
		
		//Impressão do resultado
		printHistogram(count_notas);
	}

	//Função que pede ao utilizador o nome do ficheiro : OK 
	public static File getFile () throws IOException { 
		System.out.printf("Por favor introduza o nome do ficheiro de texto (com extensao): ");
		String filename=ler.nextLine();
		File file= new File (filename);

		//Valida o nome do ficheiro, ie pede um nome de ficheiro enquanto este não corresponde a um ficheiro válido
		while ((!file.isFile()) || (file.isDirectory()) || (!file.canRead())) {
			System.out.printf("\nPor favor introduza um nome de um ficheiro de texto valido (com extensao): ");
			filename=ler.nextLine();
			file= new File (filename);
		}

		return file;
	} 

	public static void printHistogram (int[] count) {
		System.out.printf("\nHistograma de notas\n-------------------------------------------------------");
		for (int j=0; j<21; j++) {
			System.out.printf("\n%2d | ", j);
			for (int v=0; v<count[j]; v++) {System.out.print("*");}
		}
	}
}
