/*
 * Ex9_1.java
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

public class Ex9_1 {

	static Scanner ler =new Scanner (System.in);

	public static void main(String[] args) throws IOException {

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

		//Imprime o conteúdo do ficheiro
		Scanner read_file=new Scanner(file_toRead);
		while(read_file.hasNextLine()) System.out.println(read_file.nextLine());
		read_file.close();
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

