/*
 * Ex9_6.java
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
public class Ex9_6 {

	static Scanner ler=new Scanner (System.in);
	public static void main(String[] args) throws IOException {
		
		//Ficheiro a ser lido (args[0])
		File in_file=new File (args[0]);
	
		//Ficheiro a ser criado (args[1])
		File out_file=new File(args[1]);	
		
		//Verificar que o ficheiro a copiar é um ficheiro comum; que pode ser lido
		if ((!in_file.isFile()) || (!in_file.canRead())) {
			System.out.print("Ficheiro a copiar invalido. Por favor introduza novamente: ");
			args[0]=ler.nextLine();
			in_file= new File (args[0]);
		}
		
		else if ((!in_file.isFile()) || (in_file.isDirectory()) || (!in_file.canRead())) {
			System.out.printf("Ficheiro a copiar nao pode ser lido. Por favor introduza novamente: ");
			args[0]=ler.nextLine();
			in_file= new File (args[0]);
		}
		
		//Verificar que o ficheiro a criar ainda não existe
		if ((out_file.isFile())) {
			System.out.printf("\nFicheiro de destino ja existe. Por favor introduza novamente: ");
			args[1]=ler.nextLine();
			out_file= new File (args[1]);
		}
		
		//Copia o ficheiro
		Scanner in=new Scanner (in_file);				
		PrintWriter out=new PrintWriter (out_file);
		while(in.hasNextLine()){
			out.println(in.nextLine());
		}
		
		in.close();
		out.close();

	}
}
