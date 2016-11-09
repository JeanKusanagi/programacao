/*
 * Ex4_06.java
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

public class Ex4_06 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int larg=0, alt=0;          
		
		//Introdução da largura e da altura
		System.out.printf("Criacao de retangulos\n------------\nIntroduza a largura e a altura do retangulo: \n");
		larg=ler.nextInt();
		alt=ler.nextInt();
		
		//Validação do n
		while ((larg<0) || (alt<0)) 
		{
		System.out.printf("Tem de introduzir medidas validas. Introduza uma largura e uma altura validas:\n");
		larg=ler.nextInt();
		alt=ler.nextInt();
		}
		
		System.out.println(" ");
		//Impressão do retangulo
		
		//Em cada coluna
		for (int j=0; j<alt; j++)
		{
		//Fazer a linha
		for (int i=1; i<larg; i++) //i=1 pois já existe um * no inicio da coluna
		{System.out.print("*");}
		//E começar uma nova coluna
		System.out.println("*");
		}
		System.out.println(" ");

		}
	}


