/*
 * Ex4_03.java
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
public class Ex4_03 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int m=0, factor=1;          

		//Introdução do M
		System.out.printf("Factorial de numeros\n------------\nIntroduza um numero inteiro M, 1<M<=10. \n");
		m=ler.nextInt();
		
		//Validação do M
		while ((m<=1) || (m>10)) 
		{System.out.printf("Tem de introduzir um M tal que 1<M<=10\n");
		m=ler.nextInt();}
		
		System.out.printf("Lista dos factorais dos numeros 1 a %d: \n", m);
		
		//Repetir M vezes o cálculo....  :: OK
		for (int i=1; i<=m; i++)
		{
			//...do factorial de i
			for (int d=1; d<=i; d++) 
				{factor=factor*d;}
			System.out.printf("%d!= %d\n", i, factor);
			factor=1; //É necessário repor a variável 
		}
		}
	}


