/*
 * Ex4_01.java
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
public class Ex4_01 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int vezes=0;          
		String resposta;			
		
		System.out.print("Opcional: pretende introduzir o numero de vezes que quer repetir a mensagem? \nResponda Sim ou Nao.\n");
		resposta=ler.nextLine();
		
		if (resposta.equals("Sim")) 
		{System.out.print("Introduza o numero de vezes que quer repetir a mensagem.");
		vezes=ler.nextInt();
		}
		
		else if (resposta.equals("Nao")) 
		{vezes=10;}
		
		for (int i=1; i<=vezes; i++)
		{System.out.printf("\nP1 e fixe!\n\n");}
	
		}
		
}

