/*
 * Ex2.java
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
 * 
 */

import java.util.Scanner;
public class Ex3_02 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		double num=1;
		double produto=1;
		
		System.out.print("Introduza uma lista de numeros (terminada com 0): \n");
		num=ler.nextDouble();	
		while (num!=0)
		 {	
			produto=produto*num;
			num=ler.nextDouble();
		  }
		
		System.out.printf("\nProduto= %f. \n", produto);
	}
}

