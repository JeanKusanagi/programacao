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
import java.util.Scanner;
import java.io.*;

public class Ex9_2_a {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		//Pede ao utilizador o nome do ficheiro 
		System.out.printf("Leitor de texto\n--------\nPor favor introduza o nome do ficheiro de texto (com extensao): ");
		String filename=ler.nextLine();
		File file_toRead= new File (filename);

		//Valida o nome do ficheiro, ie pede um nome de ficheiro enquanto este não corresponde a um ficheiro válido
		while (!isFileValid(file_toRead)) {
			System.out.printf("\nPor favor introduza um nome de um ficheiro de texto valido (com extensao): ");
			filename=ler.nextLine();
			file_toRead= new File (filename);
		}

		//Atribui o conteúdo do ficheiro à sequência
		Scanner read_file=new Scanner(file_toRead);
		int n=(int) file_toRead.length();
		int numeros[]= new int [n];
		
		while(read_file.hasNextLine()) 
			{
			for (int i=0; i<n; i++) {
				numeros[i]=file_toRead.nextLine();
			}
			}
		

		//Leitura dos N números da sequência
		System.out.printf("Introduza os %d numeros da sua sequencia: ", n);

		 
		//Impressão dos N números pela ordem inversa
		System.out.printf("\nSequencia introduza pela ordem inversa: ");
		for (int j=(n-1); j>=0; j--) {
			System.out.print(numeros[j]);
			System.out.print(" ");
		}

	}

	public static int getInt (String message_in, String message_error, int lim)			//Obtenção do valor de N com validação
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message_in);
		int n=ler.nextInt();
		while (n<=lim) 
		{System.out.printf("%s", message_error);
		n=ler.nextInt();}
		return n;
	}


	//Função que devolve a validade do ficheiro 
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

}
