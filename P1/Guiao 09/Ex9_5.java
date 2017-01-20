

/*
 * Ex9_5.java
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
public class Ex9_5 {

	static Scanner ler=new Scanner (System.in);
	public static void main(String[] args) throws IOException {
		
		//Ficheiro a ser lido
		File in_file=getFile();
		Scanner in=new Scanner (in_file);
		
		//Ficheiro a ser criado (name)
		String name="result9_5.java";
		File out_file=new File(name);	
		PrintWriter out=new PrintWriter (out_file);
		
		while(in.hasNext()){
			String temp=in.next();
			if(temp.length()>2){
				if((temp.charAt(0)=='/') && (temp.charAt(1)=='/')){
					if(in.hasNextLine()) in.nextLine(); 				//Salta para a linha seguinte se detectar //
				}
				else out.println(temp);									//Qd deixa de detectar // imprime o texto do ficheiro in_file
			}
			else out.println(temp);
		}
		
		in.close();
		out.close();

	}
	
	//Função que obtem um ficheiro
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

}
