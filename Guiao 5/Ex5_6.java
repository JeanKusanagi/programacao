
/*
 * Ex5_6.java
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
public class Ex5_6 {

	public static void main (String[] args) {
		
		int N=getN();
		write_mtable (N);
		
	}

	public static int getN ()	//Função para a leitura de N com validação
	{
		Scanner ler = new Scanner(System.in);
		
		int num=0;          
		
		while ((num<=0) || (num>=100))
		{System.out.print("Introduza um numero inteiro N, 0<N<100. \n");
		num=ler.nextInt();}
		
		return num;
	}
	
	public static void write_mtable (int num)  //Função para a escrita da tabuada
	{	
		System.out.printf("\n-----------------\n");
		System.out.printf("|Tabuada dos  %2d| \n", num);
		for (int i=1; i<=10; i++) {System.out.printf("| %2d x %2d | %3d |\n", num, i, (num*i));}
		System.out.printf("-----------------\n\n");
	}
	
}

